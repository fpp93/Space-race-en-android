package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.game.helpers.AssetManager;
import com.mygdx.game.screens.GameScreen;

public class Button extends Actor implements InputProcessor {

    private Circle collisionButton;
    private float runtime;
    private Vector2 position;
    private float width,height;
    int previousY = 0 ;
    private Vector2 stageCoord;
    private Stage stage;
    private GameScreen screen;

    public Button ( float x, float y, float width, float height)  {

        this.height=height;
        this.width=width;
        position=new Vector2(x,y);
        collisionButton = new Circle( );
        setBounds(position.x,position.y,width,height);
        setTouchable(Touchable.enabled);

    }

    @Override
    public void draw (Batch batch, float parentAlpha ) {
        super . draw ( batch, parentAlpha ) ;
        batch. draw ( getBotonTexture ( ) , position. x , position. y ,
                width, height ) ;
    }
    public TextureRegion getBotonTexture ( ) {
        return  AssetManager.boton;
    }
    public Circle getCollisionButton() {
        return collisionButton;
    }

    public void setCollisionButton(Circle collisionButton) {
        this.collisionButton = collisionButton;
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

    @Override
    public void setWidth(float width) {
        this.width = width;
    }

    @Override
    public float getHeight() {
        return height;
    }

    @Override
    public void setHeight(float height) {
        this.height = height;
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
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
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
