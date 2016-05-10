package com.pi.vidor.Hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pi.vidor.Main;

/**
 *
 * @author Francisco
 */


public class Hud implements Disposable {
    //A classe Stage permite criar um 'container' para organizar os rótulos do HUD
    //Uma nova viewport será criada especificamente para conter o HUD
    public Stage stage;
    private Viewport viewport;
    
    private Integer world_timer;
    private float time_count;
    private Integer score;
    
    //variáveis do tipo Label para armazenamento dos rótulos
    Label countdown_label;
    Label score_label;
    Label time_label;
    Label level_label;
    Label world_label;
    Label vidor_label;
    
    public Hud(SpriteBatch sb) {
        world_timer = 300;
        time_count = 0;
        score = 0;
        
        viewport = new FitViewport(Main.getWIDTH(), Main.getHEIGHT(), new OrthographicCamera());
        stage = new Stage(viewport, sb);
        
        //A classe Table permitirá organizar de fato os rótulos
        Table table = new Table();
        //Adiciona uma tabela no topo da tela
        table.top();
        //Equipara o tamanho da tabela com a do 'container' da classe Stage
        table.setFillParent(true);
        
        //Os parametros do rótulo são: String, Versão Gráfica da Fonte Padrão e Cor
        countdown_label = new Label(String.format("%03d", world_timer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        score_label = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        time_label = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        level_label = new Label("1-1", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        world_label = new Label("WORLD", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        vidor_label = new Label("VIDOR", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        
        //adicionando os rótulos na tabela, expandindo ao tamanho X da janela
        //se tivermos mais de um elemento na tabela, estes dividem igualmente o espaço
        table.add(vidor_label).expandX().padTop(10);
        table.add(world_label).expandX().padTop(10);
        table.add(time_label).expandX().padTop(10);
        
        //cria uma nova linha na tabela
        table.row();
        
        table.add(score_label).expandX();
        table.add(level_label).expandX();
        table.add(countdown_label).expandX();
        
        //adiciona a tabela ao 'container' do tipo Stage
        stage.addActor(table);
        
    }

    @Override
    public void dispose() {
        stage.dispose();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
