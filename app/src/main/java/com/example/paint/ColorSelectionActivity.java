package com.example.paint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

public class ColorSelectionActivity extends AppCompatActivity {

    //Attribut
    private int currentColor_;
    private int red_;
    private int green_;
    private int blue_;
    EditText redText_;
    EditText greenText_;
    EditText blueText_;
    FrameLayout colorPreview_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_selection);

        //Récupérration de la couleure actuelle
        currentColor_ = MainActivity.getColor();
        red_ = (currentColor_ & 0x00FF0000) >> 16;
        green_ = (currentColor_ & 0x0000FF00) >> 8;
        blue_ = (currentColor_ & 0x000000FF);

        //Récupération des éléments utiles
        redText_ = findViewById(R.id.red);
        greenText_ = findViewById(R.id.green);
        blueText_ = findViewById(R.id.blue);
        colorPreview_ = findViewById(R.id.colorPreview);

        //Affichage des valeurs actuelles
        redText_.setText(String.valueOf(red_));
        greenText_.setText(String.valueOf(green_));
        blueText_.setText(String.valueOf(blue_));

        //Affichage de la preview avec la couleur actuelle
        int backgroundColor = 0xff000000 | (red_ << 16) | (green_ << 8) | blue_;
        colorPreview_.setBackgroundColor(backgroundColor);

        //Ajout d'écouteurs pour changer la couleur de la preview lors du changement d'une valeur
        redText_.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!(s.toString().isEmpty())) {
                    refreshPreview();
                }
            }
        });

        greenText_.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!(s.toString().isEmpty())) {
                    refreshPreview();
                }
            }
        });

        blueText_.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!(s.toString().isEmpty())) {
                    refreshPreview();
                }
            }
        });
    }

    public void validateColor(View vue) {
        //Appuis sur le bouton de validation
        red_ = Integer.parseInt(redText_.getText().toString());
        green_ = Integer.parseInt(greenText_.getText().toString());
        blue_ = Integer.parseInt(blueText_.getText().toString());

        int newColor = 0xff000000 | (red_ << 16) | (green_ << 8) | blue_;
        MainActivity.setColor(newColor);

        //Fermeture de l'activité
        finish();
    }

    public void refreshPreview(){
        //Raffraichissement de l'apercu de couleur
        int red = Integer.parseInt(redText_.getText().toString());
        int green = Integer.parseInt(greenText_.getText().toString());
        int blue = Integer.parseInt(blueText_.getText().toString());

        int backgroundColor = 0xff000000 | (red << 16) | (green << 8) | blue;
        colorPreview_.setBackgroundColor(backgroundColor);
    }

    public void resetValue(View vue){
        //Réinitialisation des valeurs courantes
        red_ = (currentColor_ & 0x00FF0000) >> 16;
        green_ = (currentColor_ & 0x0000FF00) >> 8;
        blue_ = (currentColor_ & 0x000000FF);

        //Affichage des nouvelles valeurs
        redText_.setText(String.valueOf(red_));
        greenText_.setText(String.valueOf(green_));
        blueText_.setText(String.valueOf(blue_));

        //Réinitialisation de la preview
        refreshPreview();
    }
}