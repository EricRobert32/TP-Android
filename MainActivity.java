package com.example.tp2_android_drawing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.example.tp2_android_drawing.modele.CursiveShape;
import com.example.tp2_android_drawing.modele.LineShape;
import com.example.tp2_android_drawing.modele.LineShapes;

import com.example.tp2_android_drawing.modele.RectangleShape;
import com.example.tp2_android_drawing.modele.ShapeContainer;
import com.example.tp2_android_drawing.modele.ShapeKind;
import com.example.tp2_android_drawing.modele.ShapeProperties;
import com.example.tp2_android_drawing.view.DrawingView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private ShapeKind selectedShapeKind;
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
            ArrayList<Float> cuu = new ArrayList<>();
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                    coo[0] = event.getX();
                    coo[1] = event.getY();
                    switch (selectedShapeKind){
                        case CURSIVE:
                            cuu.add(0f);
                            cuu.add(0f);
                    }
                    break;

                case MotionEvent.ACTION_UP:
                    switch (selectedShapeKind){
                        case SEGMENT:
                            LineShape line = new LineShape(new float[]{0f,0f,event.getX()-coo[0],event.getY()-coo[1]});
                            sc.add(line,new ShapeProperties(coo[0],coo[1]));
                            break;

                        case RECTANGLE:
                            RectangleShape rec = new RectangleShape(new float[]{0f,0f,event.getX()-coo[0],event.getY()-coo[1]});
                            sc.add(rec,new ShapeProperties(coo[0],coo[1]));
                            break;

                        case CURSIVE:
                            cuu.add(event.getX()-coo[0]);
                            cuu.add(event.getY()-coo[1]);
                            float[] cuu2 = new float[cuu.size()];
                            int i =0;
                            for (Float f : cuu) {
                                cuu2[i] = f;
                                i++;
                            }
                            CursiveShape cu = new CursiveShape(cuu2);
                            sc.add(cu, new ShapeProperties(coo[0],coo[1]));
                            break;
                    }
                    break;

                    case MotionEvent.ACTION_MOVE:
                        switch (selectedShapeKind){
                            case CURSIVE:
                                cuu.add(event.getX()-coo[0]);
                                cuu.add(event.getY()-coo[1]);
                                break;
                        }
            }
            return true;
        });
        ListView lv = (ListView)findViewById(R.id.shapePalette);
        lv.setAdapter(new ArrayAdapter<ShapeKind>(this, android.R.layout.simple_list_item_1, ShapeKind.values()));
        lv.setOnItemClickListener( (adapterView, view, i, l) -> {
            selectedShapeKind = (ShapeKind) adapterView.getItemAtPosition(i);
        });
        dv.setModel(sc);

        //listener to move the line
        dv.setOnClickListener(v -> {
            sc.add(ls, new ShapeProperties(20.0f, 40.0f));
        });


    }
}