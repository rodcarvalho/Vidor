package com.pi.vidor.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.pi.vidor.Hud.Hud;
import com.pi.vidor.Main;
import com.pi.vidor.Tools.Box2DWorld;
import com.pi.vidor.Tools.WorldContactListener;
import com.pi.vidor.ai.Graph;
import com.pi.vidor.sprites.Vidor;
import com.pi.vidor.sprites.Wolf;

/**
 *
 * @author Francisco
 */

//A tela de jogo implementa a interface Screen e todos os seus métodos requeridos
public class PlayScreen extends AbstractScreen {
    
    private Vidor player;
    private Wolf wolf;
    private TextureAtlas atlas;
    private Hud hud;
    
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer map_renderer;
    private MapProperties properties;
    private int mapWidth;
    private int mapHeight;
    
    private World world;
    private Box2DDebugRenderer b2dr; //Nos dá a representação gráfica dos objetos do mundo box2D
    private Graph graph;

    public PlayScreen(Main game) {
        super(game);
        
        atlas = new TextureAtlas("vidor_and_more.pack");
        hud = new Hud(getGame().getBatch()); //cria o HUD do nosso jogo com as informações devidas
        maploader = new TmxMapLoader();
        map = maploader.load("the_hyrule.tmx");
        map_renderer = new OrthogonalTiledMapRenderer(map, 1 / Main.getPPM());
        world = new World(new Vector2(0, 0), true);
        b2dr = new Box2DDebugRenderer();
        new Box2DWorld(this);
        player = new Vidor(this);
        wolf = new Wolf(this, 0 / Main.getPPM(), 0 / Main.getPPM());
        world.setContactListener(new WorldContactListener());
        
        b2dr.SHAPE_STATIC.set(1,0,0,1);
        b2dr.SHAPE_NOT_AWAKE.set(1, 0, 0, 1);
        b2dr.SHAPE_AWAKE.set(1, 1, 1, 1);
        
        properties = map.getProperties();
        mapWidth = properties.get("width", Integer.class);
        mapHeight = properties.get("height", Integer.class);
        
        graph = new Graph(this);
    }

    @Override
    public void handleInput(float delta) {
        //Controlar o jogador
        if (Gdx.input.isKeyPressed(Input.Keys.UP) && player.getB2body().getLinearVelocity().y <= 2) {
            player.getB2body().applyLinearImpulse(new Vector2(0, 0.15f), player.getB2body().getWorldCenter(), true);
            player.getB2body().setLinearVelocity(0, player.getB2body().getLinearVelocity().y);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && player.getB2body().getLinearVelocity().y >= -2) {
            player.getB2body().applyLinearImpulse(new Vector2(0, -0.15f), player.getB2body().getWorldCenter(), true);
            player.getB2body().setLinearVelocity(0, player.getB2body().getLinearVelocity().y);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.getB2body().getLinearVelocity().x <= 2) {
            player.getB2body().applyLinearImpulse(new Vector2(0.15f, 0), player.getB2body().getWorldCenter(), true);
            player.getB2body().setLinearVelocity(player.getB2body().getLinearVelocity().x, 0);
            //System.out.println("posicao em x: " + player.getB2body().getPosition().x);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.getB2body().getLinearVelocity().x >= -2) {
            player.getB2body().applyLinearImpulse(new Vector2(-0.15f, 0), player.getB2body().getWorldCenter(), true);
            player.getB2body().setLinearVelocity(player.getB2body().getLinearVelocity().x, 0);
        }
        
        //
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
            getGame().setScreen(getGame().getSm().setback());
    }

    @Override
    public void update(float delta) {
        handleInput(delta);
        
        world.step(1/60f, 6, 2);
        
        player.update(delta);
        wolf.update(delta);
        hud.update(delta);
       
        // Room Transition
        // Go Right
        if(player.getB2body().getPosition().x > Main.getWIDTH() / Main.getPPM()) {
            getGamecam().position.x = ((Main.getWIDTH() / Main.getPPM()) / 2) * 3;
        }
        // Go Left
        if(player.getB2body().getPosition().x < Main.getWIDTH() / Main.getPPM()) {
            getGamecam().position.x = (Main.getWIDTH() / Main.getPPM()) / 2;
        }
        // Go Up
        if(player.getB2body().getPosition().y > Main.getHEIGHT() / Main.getPPM()) {
            getGamecam().position.y = ((Main.getHEIGHT() / Main.getPPM()) / 2) * 3;
        }
        //Go Higher!
        if(player.getB2body().getPosition().y > (Main.getHEIGHT() / Main.getPPM()) * 2) {
            getGamecam().position.y = ((Main.getHEIGHT() / Main.getPPM()) / 2) * 5;
        }
        // Go Down
        if(player.getB2body().getPosition().y < Main.getHEIGHT() / Main.getPPM()) {
            getGamecam().position.y = (Main.getHEIGHT() / Main.getPPM()) / 2;
        }
        
        getGamecam().update();
        map_renderer.setView(getGamecam());
        getGame().getBatch().setProjectionMatrix(getGamecam().combined);
        
    }
    
    @Override
    public void render(float delta) {
        super.render(delta);
        map_renderer.render(); //renderiza o mapa do jogo
        b2dr.render(world, getGamecam().combined); //renderiza as formas do mundo Box2D
        
        super.getGame().getBatch().begin(); //Abre o 'container' ou 'batch' de sprites, desenha a textura passada no argumento nas coordenadas informadas e, por fim, fecha o 'batch'
        player.draw(getGame().getBatch());
        wolf.draw(getGame().getBatch());
        super.getGame().getBatch().end();
        
        getGame().getBatch().setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
        
    }
    
    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
 
    @Override
    public void dispose() {
        map.dispose();
        map_renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
        getGame().getBatch().dispose();
    }
    
    public TextureAtlas getAtlas() {
        return atlas;
    }

    public World getWorld() {
        return world;
    }

    public TiledMap getMap() {
        return map;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }
    
    public Array<RectangleMapObject> getWalls() {
        return map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class);
    }
    
    public Array<RectangleMapObject> getObstacles() {
        return map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class);
    }
    
    public Array<RectangleMapObject> getItems() {
        return map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class);
    }
    
}
