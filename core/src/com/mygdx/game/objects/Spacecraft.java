package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.helpers.AssetManager;
import com.mygdx.game.utils.Settings;

public class Spacecraft extends Actor {

    public static final int SPACECRAFT_STRAIGHT=0;
    public static final  int SPACECRAFT_UP=1;
    public static final int SPACECRAFT_DOWN=2;
    private Vector2 position;
    private float width,height;
    private int direction;
    private Rectangle collisionRect;


    public Spacecraft(float x, float y, float width, float height){

        this.height=height;
        this.width=width;
        position=new Vector2(x,y);
        direction = SPACECRAFT_STRAIGHT;
        // Creamos el rectángulo de colisiones
        collisionRect = new Rectangle( );

    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    @Override
    public float getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public float getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    //cambiamos la direccion del spacecraft
    public void goUp(){
        direction=SPACECRAFT_UP;
    }
    public void goDown(){
        direction=SPACECRAFT_DOWN;
    }
    public void goStraight(){
        direction=SPACECRAFT_STRAIGHT;
    }

    //delta es la diferencia de tiempo de llamadas a render
    public void act ( float delta ) {
    // Movemos el Spacecraft dependiendo de la dirección controlando que no salga de la pantalla
        switch ( direction ) {
            case SPACECRAFT_UP :
                //cuando la nave va hacia arriba, controlamos que no se salga de la pantalla con el eje y menor a 0
                if (this.position.y - Settings.SPACECRAFT_VELOCITY * delta >= 0) {
                    this.position.y -= Settings.SPACECRAFT_VELOCITY * delta;
                }
                break;
            case SPACECRAFT_DOWN :
                //aqui tenmos en cuenta la altura de la nave, ya que el eje de referencia esta arriba y no en la base de la nave.
                if (this.position.y + height + Settings.SPACECRAFT_VELOCITY * delta
                        <= Settings.GAME_HEIGHT ){
                    this . position . y += Settings. SPACECRAFT_VELOCITY * delta ;
                }
                break;
            case SPACECRAFT_STRAIGHT:
                break;
        }
        collisionRect. set( position. x , position. y + 3 , width, 10 ) ;
    }
    @Override
    public void draw ( Batch batch, float parentAlpha ) {
        super . draw ( batch, parentAlpha ) ;
        batch. draw ( getSpacecraftTexture ( ) , position. x , position. y ,
                width, height ) ;
    }

    // Obtenemos el TextureRegion dependiendo de la posición del Spacecraft
    public TextureRegion getSpacecraftTexture ( ) {
        switch ( direction ) {
            case SPACECRAFT_UP :
                return AssetManager.spacecraftUp ;
            case SPACECRAFT_DOWN :
                return AssetManager.spacecraftDown ;
            default :
                return AssetManager.spacecraft ;
        }
    }
    public Rectangle getCollisionRect ( ) {
        return collisionRect ;
    }

}
