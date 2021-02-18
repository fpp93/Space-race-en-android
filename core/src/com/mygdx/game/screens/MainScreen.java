package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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
    private Batch batch;
    private Image spacecraft;
    //private Background bg, bg_back;
    private TextureRegion background;
    private Texture sheet;
    Image bg;
    Container<Label> container;
    public MainScreen(){

    }
    public MainScreen(SpaceRace game) {
        sheet = new Texture( Gdx. files . internal ( "sheet.png" ) ) ;
        sheet. setFilter ( Texture. TextureFilter . Nearest , Texture.
                TextureFilter . Nearest ) ;
        this.game = game;
        OrthographicCamera camera = new OrthographicCamera ( Settings.
                GAME_WIDTH , Settings. GAME_HEIGHT ) ;

        camera. setToOrtho ( true ) ;
        // Creamos el acceso visual con las mismas dimensiones que la cámara
        StretchViewport viewport = new StretchViewport( Settings. GAME_WIDTH ,
                Settings. GAME_HEIGHT , camera ) ;

        stage = new Stage ( viewport ) ;
        batch = stage. getBatch();
        background = new TextureRegion ( sheet, 0 , 177 , 480 , 135 ) ;
        background. flip ( false , true ) ;
        bg = new Image(background);
        stage.addActor(bg);
        Label.LabelStyle textstyle = new Label.LabelStyle ( AssetManager.font ,null ) ;
        Label textLbl = new Label ( "SpaceRace" , textstyle ) ;
        textLbl.setName ("LBL") ;
        textLbl.setPosition ( Settings. GAME_WIDTH / 2 - textLbl. getWidth ( ) / 2 , Settings. GAME_HEIGHT / 2 - textLbl. getHeight ( ) / 2 ) ;
        container = new Container<>(textLbl);
        container.setTransform(true);
        container.center();
        container.setPosition(Settings. GAME_WIDTH / 2,Settings. GAME_HEIGHT / 2);
        container.addAction ( Actions.repeat ( RepeatAction.FOREVER,Actions.
                sequence (Actions.scaleTo(1.5f,1.5f,0.5f), Actions.scaleTo(1f,1f,0.5f) )) ) ;
        stage.addActor(container);
        spacecraft = new Image(AssetManager.spacecraft);
        float y = Settings.GAME_HEIGHT / 2 + textLbl.getHeight();
        spacecraft.addAction(Actions.repeat(RepeatAction.FOREVER, Actions.sequence(Actions.moveTo(0 - spacecraft.getWidth(), y), Actions.moveTo(Settings.GAME_WIDTH, y, 5))));

        stage.addActor(spacecraft);


        stage.addActor(spacecraft);
        AssetManager. music . play ( );
        // Asignamos como gestor de entrada la clase InputHandler
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
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
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();

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

    }

    @Override
    public void dispose() {

    }
}
