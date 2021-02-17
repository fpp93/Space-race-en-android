package com.mygdx.game.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetManager {


    // Sprite Sheet
    public static Texture sheet ;
    public static Texture  misil_mod;
    // Nave y fondos
    public static TextureRegion spacecraft, spacecraftDown, spacecraftUp, background ;
    public static TextureRegion Missile;
    //boton
    public static TextureRegion boton;
    public static Texture tboton;
    // Asteroide
    public static TextureRegion [ ] asteroide ;
    public static Animation<TextureRegion> asteroidAnim ;
    // Explosión
    public static TextureRegion [ ] explosion ;
    public static Animation<TextureRegion> explosionAnim ;
    public static Sound explosionSound;
    public static Music music;
    public static BitmapFont font;
    public static BitmapFont font2;

    public static void load(){
        // Cargamos las texturas y se le aplica el método de escalado 'nearest'
        sheet = new Texture ( Gdx. files . internal ( "sheet.png" ) ) ;
        sheet. setFilter ( Texture. TextureFilter . Nearest , Texture.
                TextureFilter . Nearest ) ;
        misil_mod = new Texture ( Gdx. files . internal ( "misil_mod.png" ) ) ;
        misil_mod. setFilter ( Texture. TextureFilter . Nearest , Texture.
                TextureFilter . Nearest ) ;
        //sprite del boton
        tboton = new Texture ( Gdx. files . internal ( "boton.png" ) ) ;
        tboton. setFilter ( Texture. TextureFilter . Nearest , Texture.
                TextureFilter . Nearest ) ;

        boton = new TextureRegion ( tboton, 0 , 0 , 600 , 600 ) ;
        // Sprites de la nave
        spacecraft = new TextureRegion ( sheet, 0 , 0 , 36 , 15 ) ;
        spacecraft. flip ( false , true ) ;
        spacecraftUp = new TextureRegion ( sheet, 36 , 0 , 36 , 15 ) ;
        spacecraftUp. flip ( false , true ) ;
        spacecraftDown = new TextureRegion ( sheet, 72 , 0 , 36 , 15 ) ;
        spacecraftDown. flip ( false , true ) ;
        Missile = new TextureRegion(misil_mod,0,0,10,10);
        Missile.flip(false,true);
        // Cargamos los 16 estados del asteroide
        asteroide = new TextureRegion [ 16 ] ;
        for ( int y = 0 ; y < asteroide. length ; y ++ ) {
            asteroide [ y ] = new TextureRegion ( sheet, y * 34 , 15 , 34 , 34
            ) ;
            asteroide [ y ] . flip ( false , true ) ;
            // Creamos la animación del asteroide y hacemos que se ejecute continuamente en sentido antihorario
            asteroidAnim = new Animation ( 0.05f, asteroide ) ;
            asteroidAnim. setPlayMode ( Animation.PlayMode.LOOP_REVERSED ) ;


        }
        // Creamos los 16 estados de la explosión
        explosion = new TextureRegion [ 16 ] ;
        int index = 0 ;
        for ( int y = 0 ; y < 2 ; y ++ ) {
            for ( int j = 0 ; j < 8 ; j ++ ) {
                explosion [ index ++ ] = new TextureRegion ( sheet, j * 64 , y
                        * 64 + 49 , 64 , 64 ) ;
                explosion [ index - 1 ] . flip ( false , true ) ;
            }
        }
        explosionAnim= new Animation<TextureRegion>(0.04f,explosion);

        // Fondo de pantalla
        background = new TextureRegion ( sheet, 0 , 177 , 480 , 135 ) ;
        background. flip ( false , true ) ;

        explosionSound = Gdx. audio . newSound ( Gdx. files . internal ( "sounds/explosion.wav" ) ) ;
        music = Gdx. audio . newMusic ( Gdx.files.internal ( "sounds/afterburner.ogg" ) ) ;
        music. setVolume ( 0.2f ) ;
        music. setLooping ( true ) ;
        // Font space
        FileHandle fontFile = Gdx.files.internal("fonts/space.fnt");
        font = new BitmapFont(fontFile, true);
        font.getData().setScale(0.4f);

    }

    public static void dispose(){

        //descartamos los recursos
        sheet.dispose();
        explosionSound. dispose ( ) ;
        music. dispose ( ) ;
    }
}
