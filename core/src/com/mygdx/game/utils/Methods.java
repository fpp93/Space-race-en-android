package com.mygdx.game.utils;


import java.util.Random;

public class Methods {
        // Método que devuelve un float aleatorio entre un mínimo y un máximo
        public static float randomFloat ( float min, float max ) {
            Random r = new Random ( ) ;
            //nextFloat
            return r.nextFloat() * (max - min) + min ;
        }
    }

