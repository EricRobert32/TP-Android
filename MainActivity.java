package com.example.tp2_android_drawing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;


import com.example.tp2_android_drawing.modele.LineShapes;
import com.example.tp2_android_drawing.modele.ShapeContainer;
import com.example.tp2_android_drawing.modele.ShapeProperties;
import com.example.tp2_android_drawing.view.DrawingView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DrawingView dv = findViewById(R.id.drawingView);
        final ShapeContainer sc = new ShapeContainer();
        final LineShapes ls = new LineShapes(new float[]{0.0f, 0.0f, 50.0f, 15.0f});
        sc.add(ls, new ShapeProperties(10.0f, 30.0f));
        final float coo[] = new float[]{0f, 0f};
        dv.setOnTouchListener((v, event) -> {
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                    coo[0] = event.getX();
                    coo[1] = event.getY();
                    break;

                case MotionEvent.ACTION_UP:
                    LineShapes line = new LineShapes(new float[]{0f,0f,event.getX()-coo[0],event.getY()-coo[1]});
                    sc.add(line,new ShapeProperties(coo[0],coo[1]));
                    break;
            }
            return true;
        });

        dv.setModel(sc);

        //listener to move the line
        dv.setOnClickListener(v -> {
            sc.add(ls, new ShapeProperties(20.0f, 40.0f));
            Log.d("MyActivity", "test");
        });


    }
}