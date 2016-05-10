package com.pi.vidor;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pi.vidor.screens.Menu;

//A classe Main é o nosso Game Loop e implementa os métodos requeridos da classe Game

public class Main extends Game {
    private static final int WIDTH = 640;
    private static final int HEIGHT = 480;
    private static final float PPM = 100;
    
    private static final String TITLE = "THE LEGEND OF VIDOR";
    
    //A classe SpriteBatch permite armazenar todos os sprites do jogo em um único 'container'
    private SpriteBatch batch;

    public SpriteBatch getBatch() {
        return batch;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static float getPPM() {
        return PPM;
    }
    

    @Override
    public void create () {
            batch = new SpriteBatch();
            setScreen(new Menu(this));
    }

    @Override
    public void render () {
        //delega a função de renderizar para a 'screen' ativa atualmente
            super.render();
    }
}
