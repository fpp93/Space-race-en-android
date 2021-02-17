package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.SpaceRace;
import com.mygdx.game.helpers.AssetManager;
import com.mygdx.game.helpers.InputHandler;
import com.mygdx.game.objects.Background;
import com.mygdx.game.objects.Spacecraft;
import com.mygdx.game.utils.Settings;

public class MainScreen implements Screen {
    SpaceRace game;


    private Stage stage;
    private GlyphLayout textLayout;
    private Batch batch;
    private Spacecraft spacecraft;
    private Background bg, bg_back;

    public MainScreen(){

    }
    public MainScreen(SpaceRace game) {
        this.game = game;
        stage = new Stage();
        OrthographicCamera camera = new OrthographicCamera ( Settings.
                GAME_WIDTH , Settings. GAME_HEIGHT ) ;

        camera. setToOrtho ( true ) ;
        // Creamos el acceso visual con las mismas dimensiones que la cámara
        StretchViewport viewport = new StretchViewport( Settings. GAME_WIDTH ,
                Settings. GAME_HEIGHT , camera ) ;

        stage = new Stage ( viewport ) ;
        batch = stage. getBatch ( ) ;
        spacecraft = new Spacecraft( Settings. SPACECRAFT_STARTX , Settings.
                SPACECRAFT_STARTY , Settings. SPACECRAFT_WIDTH , Settings. SPACECRAFT_HEIGHT ) ;
        bg = new Background(0, 0, Settings.GAME_WIDTH * 2, Settings.GAME_HEIGHT, Settings.BG_SPEED);
        stage.addActor(spacecraft);
        stage.addActor(bg);
        textLayout = new GlyphLayout();
        textLayout.setText(AssetManager.font, "SpaceRace");
        AssetManager. music . play ( );
        // Asignamos como gestor de entrada la clase InputHandler
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Spacecraft getSpacecraft() {
        return spacecraft;
    }

    public void setSpacecraft(Spacecraft spacecraft) {
        this.spacecraft = spacecraft;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.SPACE) {
                    game.setScreen(new GameScreen(game));
                }
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        batch.begin();
        AssetManager.font.draw(batch, textLayout, Settings.GAME_WIDTH/2f - textLayout.width/2, Settings.GAME_HEIGHT/2f - textLayout.height/2);
        batch. end ( );
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {

    }
}
