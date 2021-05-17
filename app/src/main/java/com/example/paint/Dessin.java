package com.example.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;

public class Dessin extends FrameLayout {
    private Paint pinceau_;
    private ArrayList<Forme> formes_;
    private Ligne ligneCourante_;
    private Rectangle rectangleCourant_;
    int color_;

    public Dessin(Context contexte){
        super(contexte);
        setBackgroundColor(0xFFFFFFFF); //Coouleur de fond
        setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        //Création du pinceau
        pinceau_ = new Paint();
        pinceau_.setStyle(Paint.Style.FILL);
        pinceau_.setStrokeWidth(4);

        formes_ = new ArrayList<>();
        ligneCourante_ = null;
        rectangleCourant_ = null;

        color_ = 0xFF000000; //Couleur de base
    }

    public void effacer(){
        //Réinitialisation du dessin
        formes_ = new ArrayList<>();
        this.invalidate();
    }

    //Accesseurs
    public void ajouter(Courbe courbe){
        formes_.add(courbe);
    }

    public void ajouter(Ligne ligne){
        formes_.add(ligne);
    }

    public void ajouter(Rectangle rectangle){
        formes_.add(rectangle);
    }

    public void setLigneCourante(Ligne ligne){
        this.ligneCourante_ = ligne;
    }

    public void deleteLigneCourante(){
        this.ligneCourante_ = null;
    }

    public void setRectangleCourant(Rectangle rectangle){
        this.rectangleCourant_ = rectangle;
    }

    public void deleteRectangleCourant(){
        this.rectangleCourant_ = null;
    }

    public void setColor(int color) {
        this.color_ = color;
    }

    public int getColor() {
        return color_;
    }


    @Override protected void onDraw(Canvas toile){
        super.onDraw(toile);

        for(int i = 0; i < formes_.size(); i++) {
            //Affichage des formes en utilisant les fontions en fonction des classes.
            if(formes_.get(i) instanceof Courbe) {
                //Affichage des courbes
                Courbe courbe = (Courbe)formes_.get(i);
                courbe.afficher(pinceau_, toile);
            }else if(formes_.get(i) instanceof Ligne) {
                //Affichage des lignes
                Ligne ligne  = (Ligne)formes_.get(i);
                ligne.dessiner(pinceau_, toile);
            }else if(formes_.get(i) instanceof Rectangle) {
                //Affichage des rectangles
                Rectangle rectangle  = (Rectangle)formes_.get(i);
                rectangle.dessiner(pinceau_, toile);
            }
        }

        //Si une ligne est en cours de création alors on l'affiche
        if(ligneCourante_ != null){
            ligneCourante_.dessiner(pinceau_, toile);
        }

        //Si un rectangle est en cours de création alors on l'affiche
        if(rectangleCourant_ != null){
            rectangleCourant_.dessiner(pinceau_, toile);
        }
    }
}
