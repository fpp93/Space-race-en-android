package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.helpers.AssetManager;
import com.mygdx.game.utils.Methods;
import com.mygdx.game.utils.Scrollable;
import com.mygdx.game.utils.Settings;

import javax.sound.sampled.Line;

public class Misil extends Actor {

    private Rectangle collisionLine;
    private float runtime;
    private Vector2 position;
    private float width,height;
    private float velocity ;
    private boolean leftOfScreen;

    public Misil ( float x, float y, float width, float height, float velocity) {

        this.height=height;
        this.width=width;
        position=new Vector2(x,y);
        this.velocity = velocity;
        collisionLine = new Rectangle( );
        leftOfScreen=false;
    }
    public Misil(){

    }

    public boolean isLeftOfScreen() {
        return leftOfScreen;
    }

    public void setLeftOfScreen(boolean leftOfScreen) {
        this.leftOfScreen = leftOfScreen;
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

    public Rectangle getCollisionLine() {
        return collisionLine;
    }

    public void setCollisionLine(Rectangle collisionLine) {
        this.collisionLine = collisionLine;
    }

    @Override
    public void act ( float delta ) {
       position. x += velocity * delta ;
        runtime += delta ;
        if (position.x > Settings.GAME_WIDTH ) {
            leftOfScreen = true;
    }
        collisionLine. set( position. x , position. y , width, height ) ;
    }
    @Override
    public void draw ( Batch batch, float parentAlpha ) {
        super . draw ( batch, parentAlpha ) ;
        batch. draw ( getMissileTexture ( ) , position. x , position. y ,
                width, height ) ;
    }
    public TextureRegion getMissileTexture ( ) {

                return AssetManager.Missile ;

    }

}
