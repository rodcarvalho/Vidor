package com.pi.vidor;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pi.vidor.screens.Menu;
import com.pi.vidor.screens.ScreenManager;

//A classe Main é o nosso Game Loop e implementa os métodos requeridos da classe Game

public class Main extends Game {
    private static final int WIDTH = 640;
    private static final int HEIGHT = 480;
    private static final float PPM = 100;
    private static final String TITLE = "THE LEGEND OF VIDOR";
    
    //filters
    private static final short VIDOR_FILTER = 2;
    private static final short ITEM_FILTER = 4;
    private static final short DESTROYED_FILTER = 8;
    private static final short DEFAULT_FILTER = 1;
        
    private SpriteBatch batch; //A classe SpriteBatch permite armazenar todos os sprites do jogo em um único 'container'
    private ScreenManager sm;

    @Override
    public void create () {
        batch = new SpriteBatch();
        sm = new ScreenManager();
        sm.push(new Menu(this));
        setScreen(sm.peek());
    }

    @Override
    public void render () {
        super.render(); //delega a função de renderizar para a 'screen' ativa atualmente
    }
    
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

    public static short getVIDOR_FILTER() {
        return VIDOR_FILTER;
    }

    public static short getITEM_FILTER() {
        return ITEM_FILTER;
    }

    public static short getDESTROYED_FILTER() {
        return DESTROYED_FILTER;
    }

    public static short getDEFAULT_FILTER() {
        return DEFAULT_FILTER;
    }

    public static String getTITLE() {
        return TITLE;
    }

    public ScreenManager getSm() {
        return sm;
    }
    
}
