package com.example.higherlower;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Random random = new Random();
    private ImageView mDiceImage;
    private ListView mThrowListView;
    private List<Throw> throwList;
    private ArrayAdapter throwListAdapter;
    private FloatingActionButton mHigherButton;
    private FloatingActionButton mLowerButton;

    private TextView scoreView;
    private TextView highscoreView;

    private int previousNumber = 3;
    private int currentNumber;
    private int currentScore = 0;
    private int highscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //bind dice ImageView
        mDiceImage = findViewById(R.id.diceImageView);
        //bind ListView
        mThrowListView = findViewById(R.id.throwListView);
        //create ArrayAdapter with the ArrayList "throwList"
        throwList = new ArrayList<>();
        throwListAdapter = new ArrayAdapter(this, R.layout.throwlist, R.id.throwlist, throwList);
        //set ListView's ArrayAdapter
        mThrowListView.setAdapter(throwListAdapter);

        //bind scoretext en highscoretext
        scoreView = findViewById(R.id.scoreText);
        highscoreView = findViewById(R.id.highscoreText);

        //bind buttons
        mHigherButton = findViewById(R.id.higherButton);
        mLowerButton = findViewById(R.id.lowerButton);

        mHigherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kies random getal uit deze methode
                roll();
                //check de score met gegeven view, true staat voor de keuze "hoger"
                checkScore(v, true);
            }
        });

        //onclick voor de "lower" knop
        mLowerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kies random getal uit met deze methode
                roll();
                //check de score met gegeven view, false staat voor de keuze "lager"
                checkScore(v, false);
            }
        });
    }

    /**
     * Deze methode genereert een random getal tussen 1 en 6.
     * Dit wordt vervolgens toegevoegd aan de lijst.
     * Op basis van de gegenereerde getal wordt de juiste afbeelding laten zien.
     */
    private void roll(){
        Throw diceNumber = new Throw();
        diceNumber.setThrowNumber(random.nextInt(6) + 1);
        currentNumber = diceNumber.getThrowNumber();

        throwList.add(diceNumber);
        throwListAdapter.notifyDataSetChanged();

        switch(diceNumber.getThrowNumber()) {
            case 1:
                mDiceImage.setImageResource(R.drawable.dice1);
                break;
            case 2:
                mDiceImage.setImageResource(R.drawable.dice2);
                break;
            case 3:
                mDiceImage.setImageResource(R.drawable.dice3);
                break;
            case 4:
                mDiceImage.setImageResource(R.drawable.dice4);
                break;
            case 5:
                mDiceImage.setImageResource(R.drawable.dice5);
                break;
            case 6:
                mDiceImage.setImageResource(R.drawable.dice6);
                break;
        }
    }

    /**
     * Deze methode checkt de score door de currentNumber te vergelijken met de previousNumber
     * Vervolgens worden de highScore en currentScore textViews aangepast
     * @param v de view waarin de snackbar wordt laten zien
     * @param choice de keuze van de gebruiker, true = hoger en false = lager
     */
    private void checkScore(View v, boolean choice) {
        //als de speler de goede keuze heeft gemaakt
        if((choice && currentNumber > previousNumber) || ((!choice) && currentNumber < previousNumber)){
            currentScore++;
            if(currentScore > highscore) {
                highscore = currentScore;
                highscoreView.setText(getString(R.string.highscore) + " " + highscore);
            }
            Snackbar.make(v, "You win!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else if(currentNumber == previousNumber) {
            Snackbar.make(v, "It's a draw", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else {
            currentScore = 0;
            Snackbar.make(v, "You LOSE!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        //sla het nummer op als "vorige nummer" om dit te kunnen gebruiken voor de volgende worp
        previousNumber = currentNumber;
        scoreView.setText(getString(R.string.score) + " " + currentScore);
    }
}
