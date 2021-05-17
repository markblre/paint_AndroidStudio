package com.example.paint;

import android.bluetooth.le.ScanSettings;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;

public class Ecouteur implements View.OnTouchListener {
    Courbe courbe_;
    Ligne ligne_;
    Rectangle rectangle_;
    int outil_;

    public void setOutil(int outil) {
        this.outil_ = outil;
    }

    public Ecouteur() {
        courbe_ = null;
        ligne_ = null;
        rectangle_ = null;
        outil_ = 1;
    }

    @Override public boolean onTouch(View vue, MotionEvent event) {
        System.out.println("touché!");

        int action = event.getAction();
        int x = (int)event.getX();
        int y = (int) event.getY();
        Dessin dessin = (Dessin)vue;

        switch(outil_){
            case (1):{
                //Courbe
                if(action == MotionEvent.ACTION_DOWN) {
                    //Création d'une courbe.
                    System.out.println("Doigt posé au point " + x +  ";" + y);
                    courbe_ = new Courbe(dessin.getColor());
                    dessin.ajouter(courbe_);
                }else if (action == MotionEvent.ACTION_UP){
                    //Réinitialisation de la variable locale courbe.
                    System.out.println("Doigt levé au point " + x +  ";" + y);
                    courbe_ = null;
                }else if(action == MotionEvent.ACTION_MOVE) {
                    //Ajout et mémorisation de points à la liste suivant le déplacement du doigt.
                    System.out.println("Doigt déplacé au point " + x +  ";" + y);
                    courbe_.ajouter(new Point(x,y));
                }
                break;
            }

            case (2):{
                //Ligne
                if(action == MotionEvent.ACTION_DOWN) {
                    //Création d'une ligne temporaire rouge représentant la ligne en cours de tracé.
                    System.out.println("Doigt posé au point " + x +  ";" + y);
                    ligne_ = new Ligne(new Point(0,0), new Point(0,0), 0xFFFF0000);
                    ligne_.setOrigine(new Point(x,y));
                    ligne_.setDestination(new Point(x,y));
                }else if (action == MotionEvent.ACTION_UP){
                    //Suppression de la ligne rouge temporaire et mémorisation de cette ligne.
                    System.out.println("Doigt levé au point " + x +  ";" + y);
                    ligne_.setColor(dessin.getColor());
                    dessin.ajouter(ligne_);
                    ligne_ = null;
                    dessin.deleteLigneCourante();
                }else if(action == MotionEvent.ACTION_MOVE) {
                    //Suivi du doigt par la destination de la ligne temporaire
                    System.out.println("Doigt déplacé au point " + x +  ";" + y);
                    ligne_.setDestination(new Point(x,y));
                    dessin.setLigneCourante(ligne_);
                }
                break;
            }

            case (3):{
                //Rectangle
                if(action == MotionEvent.ACTION_DOWN) {
                    //Création d'un rectangle temporaire rouge représentant le rectangle en cours de tracé.
                    System.out.println("Doigt posé au point " + x +  ";" + y);
                    rectangle_ = new Rectangle(new Point(0,0), new Point(0,0), 0xFFFF0000);
                    rectangle_.setOrigine(new Point(x,y));
                    rectangle_.setDestination(new Point(x,y));
                }else if (action == MotionEvent.ACTION_UP){
                    //Suppression du rectangle rouge temporaire et mémorisation de ce rectangle.
                    System.out.println("Doigt levé au point " + x +  ";" + y);
                    rectangle_.setColor(dessin.getColor());
                    dessin.ajouter(rectangle_);
                    rectangle_ = null;
                    dessin.deleteRectangleCourant();
                }else if(action == MotionEvent.ACTION_MOVE) {
                    //Suivi du doigt par la destination du rectangle temporaire
                    System.out.println("Doigt déplacé au point " + x +  ";" + y);
                    rectangle_.setDestination(new Point(x,y));
                    dessin.setRectangleCourant(rectangle_);
                }
                break;
            }
        }

        //Raffraichissement de l'affichage
        dessin.invalidate();
        return true;
    }
}
