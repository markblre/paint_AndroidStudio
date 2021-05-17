package com.example.paint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.security.DomainLoadStoreParameter;

public class MainActivity extends AppCompatActivity {

    static Ecouteur ecouteur;
    static Dessin dessin;
    Button buttonLine;
    Button buttonCurve;
    Button buttonRectangle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameLayout scene = findViewById(R.id.scene);

        if (ecouteur == null){ecouteur = new Ecouteur();}
        if (dessin == null){
            dessin = new Dessin(this);
            dessin.setOnTouchListener(ecouteur);
        }

        scene.addView(dessin);

        buttonLine = findViewById(R.id.buttonLine);
        buttonCurve = findViewById(R.id.buttonCurve);
        buttonRectangle = findViewById(R.id.buttonRectangle);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();

        FrameLayout scene = findViewById(R.id.scene);
        scene.removeView(dessin);
    }

    //Selection des outils
    public void selectionnerCourbe(View vue){
        ecouteur.setOutil(1);
        buttonLine.setBackgroundColor(0xFFA3A3A3);
        buttonCurve.setBackgroundColor(0xFF616161);
        buttonRectangle.setBackgroundColor(0xFFA3A3A3);
    }

    public void selectionnerLigne(View vue){
        ecouteur.setOutil(2);
        buttonLine.setBackgroundColor(0xFF616161);
        buttonCurve.setBackgroundColor(0xFFA3A3A3);
        buttonRectangle.setBackgroundColor(0xFFA3A3A3);
    }

    public void selectionnerRectangle(View vue){
        ecouteur.setOutil(3);
        buttonLine.setBackgroundColor(0xFFA3A3A3);
        buttonCurve.setBackgroundColor(0xFFA3A3A3);
        buttonRectangle.setBackgroundColor(0xFF616161);
    }

    public void effacerDessin(View vue){
        dessin.effacer();
    }

    public void changeColorButton(View vue) {
        //Ouverture de l'activité de choix de couleur
        Intent changeColorActivity = new Intent(this,ColorSelectionActivity.class);
        startActivity(changeColorActivity);
    }

    static public int getColor() {
        return dessin.getColor();
    }

    static public void setColor(int c) {
        dessin.setColor(c);
    }
}