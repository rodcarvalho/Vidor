
package com.pi.vidor.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pi.vidor.Main;

/**
 *
 * @author Francisco
 */
public class Menu implements Screen {
    private enum State {MAIN, START, EXIT};
    private final Main game;
    private final OrthographicCamera menucam;
    private final Viewport menuport;
    private final Texture texture;
    private TextureRegion[] regions;
    
    public Menu(Main game) {
        this.game = game;
        menucam = new OrthographicCamera();
        menuport = new FitViewport(Main.getWIDTH() / Main.getPPM(), Main.getHEIGHT() / Main.getPPM(), menucam);
        menucam.position.set(menuport.getWorldWidth() / 2, menuport.getWorldHeight() / 2, 0);
        texture = new Texture("menu.jpg");
        
    }

    public void handleInput (float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            game.setScreen(new PlayScreen(game));
            dispose();
        }
    }
    
    public void update (float delta) {
        handleInput(delta);
        menucam.update();
        game.getBatch().setProjectionMatrix(menucam.combined);
    }
    
    @Override
    public void show() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(float delta) {
        update(delta);
        
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        game.getBatch().setProjectionMatrix(menucam.combined);
        
        game.getBatch().begin();
        game.getBatch().draw(texture, 0, 0, Main.getWIDTH() / Main.getPPM(), Main.getHEIGHT() / Main.getPPM());
        game.getBatch().end();
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void resize(int width, int height) {
        menuport.update(width, height);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void pause() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void resume() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void hide() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dispose() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
