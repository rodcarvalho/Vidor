
package com.pi.vidor.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pi.vidor.Main;

/**
 *
 * @author Francisco
 */
public abstract class AbstractScreen implements Screen {
    
    private Main game;
    private OrthographicCamera gamecam;
    private Viewport viewport;
    private ScreenManager sm;

    public AbstractScreen(Main game) {
        this.game = game;
        this.sm = new ScreenManager();
        this.gamecam = new OrthographicCamera();
        this.viewport = new FitViewport(Main.getWIDTH() / Main.getPPM(), Main.getHEIGHT() / Main.getPPM(), gamecam);
        gamecam.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
        this.sm = new ScreenManager();
    }
    
    public abstract void handleInput(float delta);
    public abstract void update(float delta);

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        update(delta);
        
        Gdx.gl.glClearColor(0, 0, 0, 1); //A cor (preto) e a chamada para limpar a tela
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        gamecam.update();
        game.getBatch().setProjectionMatrix(gamecam.combined);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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
    }

    public Main getGame() {
        return game;
    }

    public OrthographicCamera getGamecam() {
        return gamecam;
    }

    public ScreenManager getSm() {
        return sm;
    }

    public Viewport getViewport() {
        return viewport;
    }
    
}
