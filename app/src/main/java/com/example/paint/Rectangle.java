package com.example.paint;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.ArrayList;

/*
    La classe Courbe dépend de la classe Forme.
    La classe rectangle mémorise les coordonnées des 4 coins du rectangle et dessine des lignes entre eux.
*/

public class Rectangle extends Forme{
    private Point origine_;
    private Point destination_;

    public void setOrigine(Point point) {
        origine_ = point;
    }

    public void setDestination(Point point) {
        destination_ = point;
    }

    public Rectangle(Point origine, Point destination, int color) {
        super(color);
        this.origine_ = origine;
        this.destination_ = destination;
    }

    public void dessiner(Paint pinceau, Canvas toile){
        //Récupération des coordonnées
        int x1 = origine_.x;
        int y1 = origine_.y;
        int x2 = origine_.x;
        int y2 = destination_.y;
        int x3 = destination_.x;
        int y3 = destination_.y;
        int x4 = destination_.x;
        int y4 = origine_.y;

        //Changement de la couleur du pinceau avec la couleur contenu dans la classe Forme.
        pinceau.setColor(getColor());

        //Création du rectangle
        toile.drawLine(x1,y1,x2,y2,pinceau);
        toile.drawLine(x2,y2,x3,y3,pinceau);
        toile.drawLine(x3,y3,x4,y4,pinceau);
        toile.drawLine(x4,y4,x1,y1,pinceau);
    }
}
