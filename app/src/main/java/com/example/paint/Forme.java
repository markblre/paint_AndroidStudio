package com.example.paint;

/*
    La classe Forme contient la couleur des dessins de l'utilisateur.
    Elle est utlisé pour tout les dessins.
*/

public class Forme {
    private int color_;

    public void setColor(int c) {
        this.color_ = c;
    }

    public int getColor() {
        return color_;
    }

    public Forme(int c) {
        color_ = c;
    }
}
