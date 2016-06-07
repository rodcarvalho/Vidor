package com.pi.vidor.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pi.vidor.Hud.Hud;
import com.pi.vidor.Main;
import com.pi.vidor.Tools.Box2DWorld;
import com.pi.vidor.Tools.WorldContactListener;
import com.pi.vidor.ai.Graph;
import com.pi.vidor.sprites.Vidor;

/**
 *
 * @author Francisco
 */

//A tela de jogo implementa a interface Screen e todos os seus métodos requeridos
public class PlayScreen implements Screen {
    private Main game;
    private TextureAtlas atlas;
    private Vidor player;
    //private Wolf wolf;
    
    //câmera do jogo e o tratador da resolução
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    
    private Hud hud;
    
    //Variáveis do Tiled Map
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer map_renderer;
    private MapProperties properties;
    private int mapWidth;
    private int mapHeight;
    
    //Variáveis para o Box2D
    private World world;
    private Box2DDebugRenderer b2dr; //Nos dá a representação gráfica dos objetos do mundo box2D
    
    //
    private Graph graph;
    
    
    public PlayScreen(Main game) {
        //
        atlas = new TextureAtlas("vidor_and_more.pack");
        
        this.game = game;
        
        //cria a câmera que seguirá o personagem pelo jogo
        gamecam = new OrthographicCamera();
        
        //cria uma viewport do tipo FitViewport para manter a real resolução independente do tamanho da janela
        gamePort = new FitViewport(Main.getWIDTH() / Main.getPPM(), Main.getHEIGHT() / Main.getPPM(), gamecam);
        
        //cria o HUD do nosso jogo com as informações devidas
        hud = new Hud(game.getBatch());
        
        
        maploader = new TmxMapLoader();
        map = maploader.load("the_hyrule.tmx");
        map_renderer = new OrthogonalTiledMapRenderer(map, 1 / Main.getPPM());
        
        //Configura a câmera do jogo inicialmente centralizada
//        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
        
        world = new World(new Vector2(0, 0), true);
        b2dr = new Box2DDebugRenderer();
        
        new Box2DWorld(this);
        
        //
        player = new Vidor(this);
        
        //
        //wolf = new Wolf(this, 0 / Main.getPPM(), 0 / Main.getPPM());
        
        world.setContactListener(new WorldContactListener());
        
        b2dr.SHAPE_STATIC.set(1,0,0,1);
        b2dr.SHAPE_NOT_AWAKE.set(1, 0, 0, 1);
        b2dr.SHAPE_AWAKE.set(1, 1, 1, 1);
        
        //
        properties = map.getProperties();
        mapWidth = properties.get("width", Integer.class);
        mapHeight = properties.get("height", Integer.class);
        
        graph = new Graph(this);
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
    
    @Override
    public void show() {
    }
    
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
            
    }
    
    public void update(float delta) {
        //atualiza constantemente para checar inputs
        handleInput(delta);
        
        //
        world.step(1/60f, 6, 2);
        
        //
        player.update(delta);
        //wolf.update(delta);
        hud.update(delta);
       
        // Room Transition
        // Go Right
        if(player.getB2body().getPosition().x > Main.getWIDTH() / Main.getPPM()) {
            gamecam.position.x = ((Main.getWIDTH() / Main.getPPM()) / 2) * 3;
        }
        // Go Left
        if(player.getB2body().getPosition().x < Main.getWIDTH() / Main.getPPM()) {
            gamecam.position.x = (Main.getWIDTH() / Main.getPPM()) / 2;
        }
        // Go Up
        if(player.getB2body().getPosition().y > Main.getHEIGHT() / Main.getPPM()) {
            gamecam.position.y = ((Main.getHEIGHT() / Main.getPPM()) / 2) * 3;
        }
        //Go Higher!
        if(player.getB2body().getPosition().y > (Main.getHEIGHT() / Main.getPPM()) * 2) {
            gamecam.position.y = ((Main.getHEIGHT() / Main.getPPM()) / 2) * 5;
        }
        // Go Down
        if(player.getB2body().getPosition().y < Main.getHEIGHT() / Main.getPPM()) {
            gamecam.position.y = (Main.getHEIGHT() / Main.getPPM()) / 2;
        }
        
        //atualiza constantemente a câmera do jogo
        gamecam.update();
        //apenas renderizará o que a câmera do jogo pode ver
        map_renderer.setView(gamecam);
        //
        game.getBatch().setProjectionMatrix(gamecam.combined);
    }

    @Override
    public void render(float delta) {
        update(delta);
        
        //A cor (preto) e a chamada para limpar a tela
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        //renderiza o mapa do jogo
        map_renderer.render();
        
        //renderiza as formas do mundo Box2D
//        b2dr.render(world, gamecam.combined);
        
        //Abre o 'container' ou 'batch' de sprites, desenha a textura passada no argumento nas coordenadas informadas e, por fim, fecha o 'batch'
        game.getBatch().setProjectionMatrix(gamecam.combined);
        game.getBatch().begin();
        player.draw(game.getBatch());
        //wolf.draw(game.getBatch());
        game.getBatch().end();
        
        //reconhece onde a câmera do jogo está e renderiza apenas o que pode ser visto por ela
        game.getBatch().setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
        
        
    }

    //Quando modificamos o tamanho da janela, o método resize reajusta a 'ViewPort' para a resolução real
    @Override
    public void resize(int width, int heigth) {
        gamePort.update(width, heigth);
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
        map_renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
        game.getBatch().dispose();
    }
    
}
