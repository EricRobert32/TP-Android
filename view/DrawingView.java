package com.example.tp2_android_drawing.view;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.tp2_android_drawing.modele.ShapeContainer;
import com.example.tp2_android_drawing.modele.ShapeContainerChangeListener;

public class DrawingView extends View {
    private ShapeContainer model;
    public DrawingView(Context context) {
        super(context);
    }

    public DrawingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public DrawingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    public void setModel(ShapeContainer shapeContainer)
    {
        ShapeContainerChangeListener listener = this::invalidate;
        this.model = shapeContainer;
        model.addChangeListener(listener);
        this.invalidate();
    }

    public void onDraw(Canvas canvas)
    {
        if (model != null)
            model.draw(canvas);
    }

}