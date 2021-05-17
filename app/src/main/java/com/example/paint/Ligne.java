package com.example.paint;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

/*
    La classe Ligne dépend de la classe Forme.
    Elle mémorise l'origine ainsi que la destination de la ligne pour ensuite l'afficher.
*/

public class Ligne extends Forme{
    private Point origine_;
    private Point destination_;

    public void setOrigine(Point point) {
        origine_ = point;
    }

    public void setDestination(Point point) {
        destination_ = point;
    }

    public Ligne(Point origine, Point destination, int color){
        super(color);
        this.origine_ = origine;
        this.destination_ = destination;
    }

    public void dessiner(Paint pinceau, Canvas toile){
        int x1 = origine_.x;
        int y1 = origine_.y;
        int x2 = destination_.x;
        int y2 = destination_.y;

        //Changement de la couleur du pinceau avec la couleur contenu dans la classe Forme.
        pinceau.setColor(getColor());

        //Dessin de la ligne
        toile.drawLine(x1,y1,x2,y2,pinceau);
    }
}
