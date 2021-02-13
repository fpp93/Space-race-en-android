package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.mygdx.game.helpers.AssetManager;
import com.mygdx.game.utils.Methods;
import com.mygdx.game.utils.Scrollable;
import com.mygdx.game.utils.Settings;

import java.util.Random;

public class Asteroid extends Scrollable {
    private float runtime;
    private Circle collisionCircle;
    private int VidasAsteroide =3;

    public Asteroid ( float x, float y, float width, float height, float velocity,int VidasAsteroide) {
        super ( x, y, width, height, velocity) ;
        //variable aleatoria para los fotogramas a visualizar
        runtime=Methods.randomFloat(0,1);
        // Creamos el círculo
        collisionCircle = new Circle ( ) ;
        this.VidasAsteroide=VidasAsteroide;
    }

    @Override
    public void act ( float delta ) {
        super . act ( delta ) ;
        runtime += delta ;
        // Actualizamos el círculo de colisiones (punto central del asteroide y del radio).
        collisionCircle. set ( position. x + width / 2.0f, position. y + width / 2.0f, width / 2.0f ) ;
    }

    public void reset ( float newX ) {
        super . reset ( newX ) ;
        // Obtenemos un número aleatorio entre MIN y MAX
        float newSize = Methods. randomFloat ( Settings. MIN_ASTEROID , Settings.
                MAX_ASTEROID ) ;
        // Modificaremos la altura y la anchura según el aleatorio anterior
        width = height = 34 * newSize ;
        // La posición será un valor aleatorio entre 0 y la altura de la aplicación menos la altura del asteroide
        position. y = new Random( ) . nextInt ( Settings. GAME_HEIGHT - ( int
                ) height ) ;
    }
    public float getRunTime ( ) {
        return runtime ;
    }

    public int getVidasAsteroide() {
        return VidasAsteroide;
    }

    public void setVidasAsteroide(int vidasAsteroide) {
        VidasAsteroide = vidasAsteroide;
    }

    @Override
    public void draw (Batch batch, float parentAlpha ) {
        super . draw ( batch, parentAlpha ) ;
        //cada vez que empieze, se dibuja un fotograma diferente
        batch. draw ( AssetManager. asteroidAnim . getKeyFrame ( runtime ) ,
                position. x , position. y , width, height ) ;
    }
    // Devuelve true si hay colisión
    public boolean collides ( Spacecraft nave ) {
        if ( position. x <= nave.getX ( ) + nave. getWidth ( ) ) {
    // Comprobamos si han colisionado siempre que el asteroide se encuentre a la misma altura que el Spacecraft
            return ( Intersector. overlaps ( collisionCircle, nave.
                    getCollisionRect ( ) ) ) ;
        }
        return false ;
    }
   /* public boolean collidesMisil ( Misil misil ) {
        if ( position. x >= misil.getPosition().x ) {
            //  si han colisionado siempre que el asteroide se encuentre a la misma altura que el Spacecraft
            return ( Intersector. overlaps ( collisionCircle, misil.
                    getCollisionLine ( ) ) ) ;
        }
        return false ;
    }*/
   public boolean collidesMisil ( Misil misil ) {
       // Comprobamos las colisiones entre cada asteroide y el misil
       if(misil!=null) {
           if (Intersector.overlaps(collisionCircle, misil.getCollisionLine())) {

               Gdx.app.log("VIDAS ANTES", "" + this.getVidasAsteroide());
               this.setVidasAsteroide(this.getVidasAsteroide() - 1);
               Gdx.app.log("VIDAS DESPUES", "" + this.getVidasAsteroide());
               return true;
           }
           return false;
       }
        return false;

   }
}
