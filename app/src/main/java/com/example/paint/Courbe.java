package com.example.paint;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.ArrayList;

/*
    La classe Courbe dépend de la classe Forme.
    Elle créé un tableau de points qui est utilisé pour mémoriser le chemin a dessiner pour afficher la courbe.
*/

public class Courbe extends Forme{

    private ArrayList<Point> points_;

    public Courbe(int color) {
        super(color);
        points_ = new ArrayList<>();
    }

    public void ajouter(Point point) {
        points_.add(point);
    }

    public void afficher(Paint pinceau, Canvas toile) {

        //Changement de la couleur du pinceau avec la couleur contenu dans la classe Forme.
        pinceau.setColor(getColor());

        //Création la courbe avec les points de la liste
        for(int i=1; i < points_.size(); i++){
            int x1 = points_.get(i-1).x;
            int y1 = points_.get(i-1).y;
            int x2 = points_.get(i).x;
            int y2 = points_.get(i).y;

            toile.drawLine(x1,y1,x2,y2,pinceau);
        }
    }
}
