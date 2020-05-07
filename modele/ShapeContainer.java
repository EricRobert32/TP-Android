package com.example.tp2_android_drawing.modele;

import android.graphics.Canvas;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ShapeContainer {
    private Map<DrawableShape, ShapeProperties> shapes;
    private HashSet<ShapeContainerChangeListener> setListener;


    public ShapeContainer() {
        this.shapes = new HashMap<DrawableShape, ShapeProperties>();
        this.setListener = new HashSet<ShapeContainerChangeListener>();
    }

    public void draw(Canvas canvas) {
        for (Map.Entry<DrawableShape, ShapeProperties> entry : shapes.entrySet()
        ) {
            entry.getKey().drawShape(entry.getValue(), canvas);
        }
    }

    public boolean add(DrawableShape shape, ShapeProperties properties) {
        boolean added = shapes.put(shape, properties) == null;
        fireListeners();
        return added;
    }

    public void addChangeListener(ShapeContainerChangeListener listener) {
        setListener.add(listener);
    }

    public void removeChangeListener(ShapeContainerChangeListener listener) {
        setListener.remove(listener);
    }

    //MEthode de mise a jour de la View
    public void fireListeners() {
        for (ShapeContainerChangeListener listener: setListener ){
            listener.onShapeContainerChange();
        }
    }
}
