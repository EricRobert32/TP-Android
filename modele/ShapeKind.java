package com.example.tp2_android_drawing.modele;

import com.example.tp2_android_drawing.R;

public enum ShapeKind {
        SEGMENT(R.drawable.segmentshape),
        RECTANGLE(R.drawable.rectangleshape),
        CURSIVE(R.drawable.cursiveshape);
        private int imageId;
        private ShapeKind(int imageId){
                this.imageId = imageId;
        }

        public int getImageId() {
                return imageId;
        }
}
