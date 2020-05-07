package com.example.tp2_android_drawing.modele;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class RectangleShape implements DrawableShape {

    private float coordone[];
    public RectangleShape(float[] coordone) {
        this.coordone =coordone;
    }

    @Override
    public void drawShape(ShapeProperties properties, Canvas canvas) {
        Paint noir = new Paint();
        noir.setStrokeWidth(5);
        noir.setStyle(Paint.Style.STROKE);
        canvas.drawRect(coordone[0]+properties.getX(),coordone[1]+properties.getY(),coordone[2]+properties.getX(), coordone[3]+properties.getY(),noir);
    }
}
