package com.mygdx.game.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.objects.Spacecraft;
import com.mygdx.game.screens.GameScreen;

public class InputHandler implements InputProcessor {

    // Objetos necesarios
    private Spacecraft spacecraft ;
    private GameScreen screen ;
    private Vector2 stageCoord;
    private Stage stage;
    // Enter para la gestión del movimiento de arrastre
    int previousY = 0 ;

    public InputHandler ( GameScreen screen ) {
    // Obtenemos todos los elementos necesarios
        this . screen = screen ;
        spacecraft = screen. getSpacecraft ( ) ;
        stage = screen.getStage();
    }


    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        previousY = screenY ;
        stageCoord = stage.screenToStageCoordinates(new Vector2(screenX, screenY));
        Actor actorHit = stage.hit(stageCoord.x, stageCoord.y, true);
        if (actorHit != null) {
            Gdx.app.log("HIT", actorHit.getName());
            if (actorHit.getName().equals("boton")) {
                screen.lanzar_misil();
            }
        }
        return true ;
    }

    @Override
    public boolean touchUp ( int screenX, int screenY, int pointer, int button )
    {
    // Cuando soltamos el dedo acabamos un movimiento
    // y ponemos la nave al estado normal
        spacecraft.goStraight ( ) ;
        return true ;
    }

    @Override
    public boolean touchDragged ( int screenX, int screenY, int pointer ) {
    // Ponemos un umbral para evitar gestionar eventos cuando dicho está quieto
        if ( Math . abs ( previousY - screenY ) > 2 )
    // Si la Y es mayor que la que tenemos
    // guardada es que va hacia abajo
            if ( previousY < screenY ) {
                spacecraft.goDown ( ) ;
            } else {
    // En caso contrario hacia arriba
                spacecraft.goUp ( ) ;
            }
    // Guardamos la posición de la Y
        previousY = screenY ;
        return true ;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
