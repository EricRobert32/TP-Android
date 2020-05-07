package com.example.tp2_android_drawing.modele;

public class ShapeBuilder {
    private ShapeKind shapeKind;

    public ShapeBuilder() {

    }

    public void setShapeKind(ShapeKind s){
        this.shapeKind = s;
    }

    public DrawableShape build(float[] coords){

        switch (shapeKind){
            case CURSIVE:
                return new CursiveShape(coords);

            case RECTANGLE:
                return new RectangleShape(coords);

            case SEGMENT:
                return new LineShape(coords);

        }
        throw new IllegalStateException("ShapeKind is wrong");
    }


}
