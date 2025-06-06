package com.example.lab03_bacha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Quiz5 extends AppCompatActivity {

    TextView optionSwift, optionJS, optionHtml, optionPhp;
    Button bNext;
    String selectedAnswer = "";
    int score = 0;
    String correctAnswer = "SWIFT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz5);

        // Récupération des éléments du layout
        optionSwift = findViewById(R.id.optionSwift);
        optionJS = findViewById(R.id.optionJS);
        optionHtml = findViewById(R.id.optionHtml);
        optionPhp = findViewById(R.id.optionPhp);
        bNext = findViewById(R.id.bNext);

        // Récupérer le score du quiz précédent
        score = getIntent().getIntExtra("score", 0);

        // Ajout des listeners aux options
        View.OnClickListener optionClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetOptionColors();
                v.setBackgroundColor(getResources().getColor(R.color.yellow)); // Highlight selection
                selectedAnswer = ((TextView) v).getText().toString();
            }
        };

        optionSwift.setOnClickListener(optionClickListener);
        optionJS.setOnClickListener(optionClickListener);
        optionHtml.setOnClickListener(optionClickListener);
        optionPhp.setOnClickListener(optionClickListener);

        // Action bouton Suivant
        bNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedAnswer.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Merci de choisir une réponse S.V.P !", Toast.LENGTH_SHORT).show();
                } else {
                    if (selectedAnswer.equals(correctAnswer)) {
                        score += 1;
                    }

                    Intent intent = new Intent(Quiz5.this, Score.class);
                    intent.putExtra("score", score);
                    intent.putExtra("maxScore", 5); // Tu peux adapter ça selon le total de questions
                    startActivity(intent);
                    overridePendingTransition(R.anim.exit, R.anim.entry);
                    finish();
                }
            }
        });
    }

    // Remettre les couleurs initiales des options
    private void resetOptionColors() {
        optionSwift.setBackgroundColor(getResources().getColor(R.color.grayLight));
        optionJS.setBackgroundColor(getResources().getColor(R.color.grayLight));
        optionHtml.setBackgroundColor(getResources().getColor(R.color.grayLight));
        optionPhp.setBackgroundColor(getResources().getColor(R.color.grayLight));
    }
}