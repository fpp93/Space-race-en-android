package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.helpers.AssetManager;
import com.mygdx.game.utils.Scrollable;

public class Background extends Scrollable {
    public Background (float x, float y, float width, float height, float velocity) {
        super(x,y,width,height,velocity);
    }

    public void draw (Batch batch, float parentAlpha ) {
        super . draw ( batch, parentAlpha ) ;
        batch. disableBlending ( ) ;
        batch. draw ( AssetManager. background , position. x , position. y , width, height ) ;
        batch. enableBlending ( ) ;

    }

}
