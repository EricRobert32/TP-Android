package com.example.tp2_android_drawing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.example.tp2_android_drawing.modele.CursiveShape;
import com.example.tp2_android_drawing.modele.LineShape;

import com.example.tp2_android_drawing.modele.RectangleShape;
import com.example.tp2_android_drawing.modele.ShapeBuilder;
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
        ShapeBuilder build = new ShapeBuilder();
        final ShapeContainer sc = new ShapeContainer();
        final float coo[] = new float[]{0f, 0f};
        ArrayList<Float> cuu = new ArrayList<>();
        dv.setOnTouchListener((v, event) -> {
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
                            /*
                            LineShape line = new LineShape(new float[]{0f,0f,event.getX()-coo[0],event.getY()-coo[1]});
                            sc.add(line,new ShapeProperties(coo[0],coo[1]));
                             */
                            build.setShapeKind(ShapeKind.SEGMENT);
                            sc.add(build.build(new float[]{0f,0f,event.getX()-coo[0],event.getY()-coo[1]}),new ShapeProperties(coo[0],coo[1]));
                            break;

                        case RECTANGLE:
                            /*
                            RectangleShape rec = new RectangleShape(new float[]{0f,0f,event.getX()-coo[0],event.getY()-coo[1]});
                            sc.add(rec,new ShapeProperties(coo[0],coo[1]));
                             */
                            build.setShapeKind(ShapeKind.RECTANGLE);
                            sc.add(build.build(new float[] {0f,0f,event.getX()-coo[0],event.getY()-coo[1]}),new ShapeProperties(coo[0],coo[1]));
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
                            /*
                            CursiveShape cu = new CursiveShape(cuu2);
                            sc.add(cu, new ShapeProperties(coo[0],coo[1]));
                            cuu.clear();
                             */
                            build.setShapeKind(ShapeKind.CURSIVE);
                            sc.add(build.build(cuu2), new ShapeProperties(coo[0],coo[1]));
                            cuu.clear();
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
                        break;
            }
            return true;
        });
        GridView gv = (GridView) findViewById(R.id.shapePalette);
        gv.setAdapter(new ArrayAdapter<ShapeKind>(this, android.R.layout.simple_list_item_1, ShapeKind.values()){
            public View getView(int position, View recycledView, ViewGroup parent){
                if(recycledView == null){
                    recycledView = getLayoutInflater().inflate(R.layout.icon_menu, null);
                }
                TextView nameView = (TextView)recycledView.findViewById(R.id.textIcon);
                nameView.setText(""+getItem(position));
                ImageView imageView = (ImageView)recycledView.findViewById(R.id.imageIcon);
                imageView.setImageResource(getItem(position).getImageId());
                return recycledView;
            }
        });
        gv.setOnItemClickListener( (adapterView, view, i, l) -> {
            selectedShapeKind = (ShapeKind) adapterView.getItemAtPosition(i);
        });
        dv.setModel(sc);


    }
}