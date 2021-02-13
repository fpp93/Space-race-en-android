package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.mygdx.game.utils.Methods;
import com.mygdx.game.utils.Settings;

import java.util.ArrayList;
import java.util.Random;

public class ScrollHandler extends Group {

    //fondo de pantalla
    Background bg, bg_back;



    //asteroides
    int numAsteroids;
    ArrayList<Asteroid> asteroids;

    //objeto random
    Random r;

    public ArrayList<Asteroid> getAsteroids() {
        return asteroids;
    }

    public ScrollHandler() {
        // Creamos los dos fondos
        bg = new Background(0, 0, Settings.GAME_WIDTH * 2, Settings.GAME_HEIGHT, Settings.BG_SPEED);
        bg_back = new Background(bg.getTailX(), 0, Settings.GAME_WIDTH * 2, Settings.GAME_HEIGHT, Settings.BG_SPEED);
        // Añadimos los fondos (actores) al grupo
        addActor(bg);
        addActor(bg_back);

        // Creamos el objeto random
        r = new Random();
        // Empezamos con 3 asteroides
        numAsteroids = 3;
        // Instanciamos el ArrayList
        asteroids = new ArrayList<Asteroid>();
        // Definimos un tamaño aleatorio entre el mínimo y el máximo de los asteriodes
        //34 es el tamaño en pixeles del sprite
        float newSize = Methods.randomFloat(Settings.MIN_ASTEROID, Settings.MAX_ASTEROID) * 34;
        // Añadimos el primer asteroide en el array y al grupo
        Asteroid asteroide = new Asteroid(Settings.GAME_WIDTH, r.nextInt(Settings.GAME_HEIGHT - (int) newSize), newSize, newSize, Settings.ASTEROID_SPEED,3);
        asteroids.add(asteroide);
        addActor(asteroide);
        // Desde el segundo hasta el último asteroide
        for (int y = 1; y < numAsteroids; y++) {
            // Creamos el tamaño aleatorio
            newSize = Methods.randomFloat(Settings.MIN_ASTEROID, Settings.
                    MAX_ASTEROID) * 34;
            // Añadimos el asteroide
            asteroide = new Asteroid(asteroids.get(asteroids.size() -
                    1).getTailX() + Settings.ASTEROID_GAP, r.nextInt(Settings.GAME_HEIGHT -
                    (int) newSize), newSize, newSize, Settings.ASTEROID_SPEED,3);
            // Añadimos el asteroide al ArrayList
            asteroids.add(asteroide);
            // Añadimos el asteroide al grupo de actores
            addActor(asteroide);
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        // Si algún elemento se encuentra fuera de la pantalla, hacemos un reset del elemento
        if (bg.isLeftOfScreen()) {
            bg.reset(bg_back.getTailX());
        } else if (bg_back.isLeftOfScreen()) {
            bg_back.reset(bg.getTailX());
        }
        for (int i = 0; i < asteroids.size(); i++) {
            Asteroid asteroide = asteroids.get(i);
            if (asteroide.isLeftOfScreen()) {
                //el primer asteroide no tiene referencia de un asteroide anterior, por lo que concatena con el ultimo del array con size-1
                if (i == 0) {
                    asteroide.reset(asteroids.get(asteroids.size() - 1
                    ).getTailX() + Settings.ASTEROID_GAP);
                } else {
                    asteroide.reset(asteroids.get(i - 1).getTailX() + Settings.
                            ASTEROID_GAP);
                }

            }
        }
    }
    public boolean collides ( Spacecraft nave ) {
        // Comprobamos las colisiones entre cada asteroide y la nave
        for ( Asteroid asteroide : asteroids ) {
            if ( asteroide.collides ( nave ) ) {
                return true ;
            }
        }
        return false ;
    }

}
