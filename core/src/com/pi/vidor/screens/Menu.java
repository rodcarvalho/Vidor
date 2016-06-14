
package com.pi.vidor.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.pi.vidor.Main;

/**
 *
 * @author Francisco
 */
public class Menu extends AbstractScreen {
    
    private Texture texture;

    public Menu(Main game) {
        super(game);
        
        this.texture = new Texture("menu.jpg");
    }

    @Override
    public void handleInput(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            //getGame().getSm().push(new PlayScreen(getGame()));
            getGame().setScreen(getGame().getSm().set(new PlayScreen(getGame())));
            dispose();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            //getGame().getSm().push(new Settings(getGame()));
            getGame().setScreen(getGame().getSm().set(new Settings(getGame())));
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

    public Texture getTexture() {
        return texture;
    }
    
}
