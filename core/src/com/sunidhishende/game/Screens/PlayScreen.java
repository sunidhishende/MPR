package com.sunidhishende.game.Screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sunidhishende.game.MprGame;
import com.sunidhishende.game.Scenes.Hud;
import com.sunidhishende.game.Sprites.Character;
import com.sunidhishende.game.Sprites.mcqbrick;
import com.sunidhishende.game.Tools.B2WorldCreator;
import com.sunidhishende.game.Tools.WorldContactListener;

public class PlayScreen implements Screen{
    static final float STEP_TIME = 1f/60f;
    public static int count=0;
    private TextureAtlas atlas;



    private MprGame game;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;



    //Tile map variables
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //Box2d variables
    private World world;
    private Box2DDebugRenderer b2dr;

    private Character character;
    private mcqbrick mcqbrick;



    public PlayScreen(MprGame game){

        atlas= new TextureAtlas("mpr.pack");
        this.game=game;

        gamecam= new OrthographicCamera();
        gamePort= new FitViewport(MprGame.V_WIDTH/MprGame.PPM,MprGame.V_HEIGHT/MprGame.PPM,gamecam);
        hud= new Hud(game.batch);
        maploader= new TmxMapLoader();
        map= maploader.load("level1h.tmx");
        renderer= new OrthogonalTiledMapRenderer(map,1/MprGame.PPM);
        gamecam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2,0);
        world= new World(new Vector2(0,-10), true);
        b2dr= new Box2DDebugRenderer();
        new B2WorldCreator(world, map);
        character= new Character(world, this);
        world.setContactListener(new WorldContactListener());



    }

    public TextureAtlas getAtlas()
    {
        return atlas;

    }

    public void handleInput(float dt){
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)&& character.b2body.getLinearVelocity().y==0)
        {
           character.jump();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && character.b2body.getLinearVelocity().x<= 2){
            character.b2body.applyLinearImpulse(new Vector2(0.08f, 0), character.b2body.getWorldCenter(), true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && character.b2body.getLinearVelocity().x>= -2){
            character.b2body.applyLinearImpulse(new Vector2(-0.08f, 0), character.b2body.getWorldCenter(), true);
        }

    }

    public void update(float dt){
        handleInput(dt);

        //If world.step is done directly
        // If your game loop is being called more than 60 times a second it will go fast;
        // less than 60 times a second and it'll be slow.
        // The number of times it gets called per second will depend on the speed of the underlying hardware,
        // so this method will end up in different behavior on different devices.
        float accumulator = 0;
        float delta = Gdx.graphics.getDeltaTime();
        accumulator += Math.min(delta, 0.25f);
        while (accumulator >= STEP_TIME) {
                accumulator -= STEP_TIME;
                world.step(STEP_TIME, 6, 2);
            }
        character.update(dt);
        gamecam.position.x= character.b2body.getPosition().x;
        gamecam.update();
        renderer.setView(gamecam);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //render game map
        renderer.render();

        //renderer our Box2DDebugLines
        //b2dr.render(world, gamecam.combined);

        //render what hud camera sees
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        character.draw(game.batch);
        game.batch.end();
        hud.stage.draw();

        if (mcqbrick.ishit==1)
        {
            game.setScreen(new McqScreen(game));
        }

        if(gameOver())
        {
            game.setScreen(new GameOverScreen(game));
            dispose();
        }



    }

    public boolean gameOver(){
        if (character.isDead())
        {
            return true;
        }
        return false;
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();



    }
}
