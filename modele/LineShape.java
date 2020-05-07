package com.example.tp2_android_drawing.modele;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Arrays;

public class LineShape implements DrawableShape {
    private float coordone[];

    public LineShape(float[] coordone) {
        this.coordone = coordone;
    }

    @Override
    public void drawShape(ShapeProperties properties, Canvas canvas) {
        Paint noir = new Paint();
        noir.setColor(Color.BLACK);
        noir.setStrokeWidth(5);
        canvas.drawLine(coordone[0]+properties.getX(),coordone[1]+properties.getY(),coordone[2]+properties.getX(), coordone[3]+properties.getY(),noir);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineShape lineShape = (LineShape) o;
        return Arrays.equals(coordone, lineShape.coordone);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(coordone);
    }
}
