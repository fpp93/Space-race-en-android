package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.SpaceRace;
import com.mygdx.game.helpers.AssetManager;
import com.mygdx.game.helpers.InputHandler;
import com.mygdx.game.objects.Asteroid;
import com.mygdx.game.objects.Button;
import com.mygdx.game.objects.Misil;
import com.mygdx.game.objects.ScrollHandler;
import com.mygdx.game.objects.Spacecraft;
import com.mygdx.game.utils.Settings;



import java.util.ArrayList;

public class GameScreen implements Screen {
    SpaceRace game;

    private Stage stage;
    private Spacecraft spacecraft;
    private Misil misil;
    private ScrollHandler scrollHandler;
    //representacion de figuras geometricas
    private ShapeRenderer shapeRenderer;
    //elemento que permite dibujar sprites en pantalla
    private Batch batch;
    private boolean gameOver = false;
    private int explosionTime = 0;
    private GlyphLayout textLayout;
    private GlyphLayout textCont;
    private Button boton;
    private int NumMisil = 0;
    private ArrayList<Asteroid> asteroids;
    private int conteo = 0;


    public GameScreen(SpaceRace game){
        this.game=game;

       stage = new Stage();
       spacecraft = new Spacecraft(Settings.SPACECRAFT_STARTX,
                Settings.SPACECRAFT_STARTY , Settings.SPACECRAFT_WIDTH ,
                Settings.SPACECRAFT_HEIGHT) ;
       scrollHandler = new ScrollHandler();
        // Creamos el ShapeRenderer
        shapeRenderer = new ShapeRenderer ( ) ;
        // Creamos la cámara de las dimensiones del juego
        OrthographicCamera camera = new OrthographicCamera ( Settings.
                GAME_WIDTH , Settings. GAME_HEIGHT ) ;
        // Poniendo el parámetro a true configuramos la cámara porque
        // use el sistema de coordenadas Y-Down
        camera. setToOrtho ( true ) ;
        // Creamos el acceso visual con las mismas dimensiones que la cámara
        StretchViewport viewport = new StretchViewport( Settings. GAME_WIDTH ,
                Settings. GAME_HEIGHT , camera ) ;
        // Creamos el stage y asignamos el Viewport
        stage = new Stage ( viewport ) ;
        batch = stage. getBatch ( ) ;
        // Creamos la nave y el resto de objetos
        spacecraft = new Spacecraft ( Settings. SPACECRAFT_STARTX , Settings.
                SPACECRAFT_STARTY , Settings. SPACECRAFT_WIDTH , Settings. SPACECRAFT_HEIGHT ) ;
        scrollHandler = new ScrollHandler ( ) ;
        //asigno los asteroides a un arraylist
        asteroids=scrollHandler.getAsteroids();
        //misil = new Misil();
        boton = new Button(Settings.BOTON_STARTX,Settings.BOTON_STARTY,Settings.BOTON_WIDTH,Settings.BOTON_HEIGHT);
        // Añadimos los actores en el stage
        stage.addActor(scrollHandler) ;
        stage.addActor(spacecraft) ;
        stage.addActor(boton);
        boton.setName("boton");
        spacecraft.setName("spacecraft");
        // Iniciamos el GlyphLayout
        textLayout = new GlyphLayout();
        textCont = new GlyphLayout();
        textLayout.setText(AssetManager.font, "GameOver");

        AssetManager. music . play ( );
        // Asignamos como gestor de entrada la clase InputHandler
        Gdx. input .setInputProcessor ((InputProcessor) new InputHandler( this ));
   }

   public void lanzar_misil(){
       if(NumMisil<3) {
           misil = new Misil(Settings.MISIL_STARTX, spacecraft.getPosition().y + Settings.SPACECRAFT_HEIGHT / 2, Settings.MISIL_WIDTH, Settings.MISIL_HEIGHT, Settings.MISIL_SPEED);
           stage.addActor(misil);
           misil.setName("misil");
           NumMisil++;
       }
   }

    public ScrollHandler getScrollHandler() {
        return scrollHandler;
    }

    public void setScrollHandler(ScrollHandler scrollHandler) {
        this.scrollHandler = scrollHandler;
    }

    public Spacecraft getSpacecraft() {
        return spacecraft;
    }

    public void setSpacecraft(Spacecraft spacecraft) {
        this.spacecraft = spacecraft;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    @Override
    public void show() {

        if(gameOver==true){
            Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.SPACE) {
                    game.setScreen(new MainScreen(game));
                }
                return true;
            }
        });
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       stage.act(delta);
       stage.draw();
        textCont.setText(AssetManager.font,"Meteoritos Totales : "+ conteo);
       batch.begin();
       AssetManager.font.draw(batch, textCont, 0 , 0);
       batch.end();
        if (!gameOver) {
            if (scrollHandler. collides ( spacecraft ) ) {
        // Si ha habido colisión: Reproducimos la explosión
                AssetManager. explosionSound.play ( ) ;
                stage. getRoot ( ) . findActor( "spacecraft" ).remove( ) ;
                gameOver = true ;
            }
        } else {
            batch. begin ( ) ;
// Si ha habido colisión: reproducimos la explosión
            batch. draw ( AssetManager. explosionAnim . getKeyFrame (
                    explosionTime, false ) , ( spacecraft.getPosition ( ).x + spacecraft.getWidth ( ) / 2
                    ) - 32 , spacecraft.getPosition ( ).y + spacecraft.getHeight ( ) / 2 - 32 , 64 ,
                    64 );
            boton.setTouchable(Touchable.disabled);
            AssetManager.font.draw(batch, textLayout, Settings.GAME_WIDTH/2f - textLayout.width/2, Settings.GAME_HEIGHT/2f - textLayout.height/2);
            batch. end ( );
            explosionTime += delta ;
            if(Gdx.input.justTouched()){
                reseteo();
            }

        }
        if(stage.getRoot().findActor("misil")!=null){
            if(misil.isLeftOfScreen()==true) {
                stage.getRoot().findActor("misil").remove();
                NumMisil = 0;
            }
        }
        for(int i=0;i<asteroids.size();i++){
            Asteroid asteroide = asteroids.get(i);
            if(stage.getRoot().findActor("misil")!=null) {
                if (asteroide.collidesMisil((Misil)stage.getRoot().findActor("misil"))) {
                    stage.getRoot().findActor("misil").remove();
                    NumMisil=0;
                    if (asteroide.getVidasAsteroide() ==0) {
                        AssetManager.explosionSound.play();
                        batch.begin();
                        // Si ha habido colisión: reproducimos la explosión

                        batch.draw(AssetManager.explosionAnim.getKeyFrame(
                                explosionTime, false), (asteroide.getX() + spacecraft.getWidth() / 2
                                ) - 32, asteroide.getY() + spacecraft.getHeight() / 2 - 32, 64,
                                64);
                        batch.end();
                        explosionTime += delta;
                        if (i == 0) {
                            asteroide.reset(Settings.GAME_WIDTH+Settings.ASTEROID_GAP);
                        } else {
                            asteroide.reset(Settings.GAME_WIDTH+Settings.ASTEROID_GAP);
                        }
                        conteo++;

                    }
                }
            }
        }

       //drawElements();
    }

    public void reseteo(){
        scrollHandler.resetAsteroides();
        spacecraft.setX(Settings.SPACECRAFT_STARTX);
        spacecraft.setY(Settings.SPACECRAFT_STARTY);
        stage.addActor(spacecraft);
        boton.setTouchable(Touchable.enabled);
        explosionTime=0;
        gameOver=false;
        conteo=0;
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

    private void drawElements ( ) {
        /* 1 */
        // Pintamos el fondo de negro para evitar el "flickering"
        //Gdx. gl20.glClearColor( 0.0f, 0.0f, 0.0f, 1.0f ) ;
        //Gdx. gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        /* 2 */
        // Recogemos las propiedades del Batch del Stage
        shapeRenderer. setProjectionMatrix ( batch. getProjectionMatrix ( ) ) ;
        // inicializar el shaperenderer
        shapeRenderer. begin ( ShapeRenderer. ShapeType . Filled ) ;
        /* 3 */
        // Definimos el color (verde)
        shapeRenderer. setColor ( new Color( 0 , 1 , 0 , 1 ) ) ;
        // Pintamos la nave
        //shapeRenderer. rect ( spacecraft. getX ( ) , spacecraft. getY ( ) , spacecraft. getWidth ( ) , spacecraft. getHeight ( ) ) ;
        //shapeRenderer.rect(misil.getX(),misil.getY(),misil.getWidth(),misil.getHeight());
        /* 4 */
        // Recogemos todos los Asteroid
        ArrayList< Asteroid > asteroids = scrollHandler. getAsteroids ( ) ;
        Asteroid asteroide ;
        for ( int y = 0 ; y < asteroids. size ( ) ; y ++ ) {
            asteroide = asteroids. get ( y ) ;
            switch ( y ) {
                case 0 :
                    shapeRenderer. setColor ( 1 , 0 , 0 , 1 ) ;
                    break ;
                case 1 :
                    shapeRenderer. setColor ( 0 , 0 , 1 , 1 ) ;
                    break ;
                case 2 :
                    shapeRenderer. setColor ( 1 , 1 , 0 , 1 ) ;
                    break ;
                default :
                    shapeRenderer. setColor ( 1 , 1 , 1 , 1 ) ;
                    break ;
            }
            //shapeRenderer. circle ( asteroide. getX ( ) + asteroide. getWidth () / 2 , asteroide. getY ( ) + asteroide. getWidth ( ) / 2 , asteroide. getWidth( ) / 2 ) ;
        }
        /* 5 */
        shapeRenderer. end ( ) ;
    }

        }

