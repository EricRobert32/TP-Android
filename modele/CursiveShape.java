package com.example.tp2_android_drawing.modele;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public class CursiveShape implements DrawableShape {
    private float coordone[];

    public CursiveShape(float[] coordone) {
        this.coordone = coordone;
    }

    @Override
    public void drawShape(ShapeProperties properties, Canvas canvas) {
        Paint noir = new Paint();
        noir.setColor(Color.BLACK);
        noir.setStrokeWidth(5);
        for(int x =0; x<coordone.length-2; x+=2){
            canvas.drawLine(coordone[0+x]+properties.getX(),coordone[1+x]+properties.getY(),coordone[2+x]+properties.getX(), coordone[3+x]+properties.getY(),noir);
        }
    }
}
