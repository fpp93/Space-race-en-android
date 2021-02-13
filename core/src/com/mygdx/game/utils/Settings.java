package com.mygdx.game.utils;

public class Settings {

    //medida de la pantalla que se escalara segun la necesidad

    public static final int GAME_WIDTH = 240;
    public static final int GAME_HEIGHT = 135;

    //propiedades de la nave
    public static final float SPACECRAFT_VELOCITY = 50;
    public static final int SPACECRAFT_WIDTH = 36;
    public static final int SPACECRAFT_HEIGHT  = 15;
    public static final float SPACECRAFT_STARTX  = 20;
    public static final int SPACECRAFT_STARTY  = GAME_HEIGHT/2-SPACECRAFT_HEIGHT/2;

    //rango de valores para cambiar el tamaño del asteroide
    public static final float MAX_ASTEROID=1.5f;
    public static final float MIN_ASTEROID=0.5f;

    //configuracion scrollable
    public static final int ASTEROID_SPEED= -50;
    public static final int ASTEROID_GAP = 75;
    public static final int BG_SPEED = -100;

    //propiedades del misil

    public static final int MISIL_WIDTH = 10;
    public static final int MISIL_HEIGHT = 10;
    public static final int MISIL_SPEED= 120;
    public static final int MISIL_STARTX = 58;
    public static final int MISIL_STARTY = GAME_HEIGHT/2-SPACECRAFT_HEIGHT/2+3;

    //propiedades del boton

    public static final int BOTON_WIDTH = 24;
    public static final int BOTON_HEIGHT = 24;
    public static final int BOTON_STARTX = 200;
    public static final int BOTON_STARTY = 100;

}
