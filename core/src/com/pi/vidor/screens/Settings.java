
package com.pi.vidor.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.pi.vidor.Main;

/**
 *
 * @author Francisco
 */
public class Settings extends AbstractScreen {
    
    private Texture texture;

    public Settings(Main game) {
        super(game);
        this.texture = new Texture("menu1.jpg");
    }

    @Override
    public void handleInput(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            getGame().setScreen(getGame().getSm().setback());
            dispose();
        }
    }

    @Override
    public void update(float delta) {
        handleInput(delta);
        super.getGame().getBatch().setProjectionMatrix(super.getGamecam().combined);
    }
    
    @Override
    public void render(float delta) {
        super.render(delta);
        
        super.getGame().getBatch().begin();
        super.getGame().getBatch().draw(texture, 0, 0, Main.getWIDTH() / Main.getPPM(), Main.getHEIGHT() / Main.getPPM());
        super.getGame().getBatch().end();
    }
    
}
