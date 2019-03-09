package com.orellana.testapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    boolean gameIsActive = true;
    int yellow = 0;
    int red = 1;
    int emptySlot = 2;
    int activePlayer = yellow;
    int[] gameState = {emptySlot,emptySlot,emptySlot,emptySlot,emptySlot,emptySlot,emptySlot,
                        emptySlot,emptySlot,emptySlot,emptySlot,emptySlot,emptySlot,emptySlot,
                        emptySlot,emptySlot,emptySlot,emptySlot,emptySlot,emptySlot,emptySlot,
                        emptySlot,emptySlot,emptySlot,emptySlot,emptySlot,emptySlot,emptySlot,
                        emptySlot,emptySlot,emptySlot,emptySlot,emptySlot,emptySlot,emptySlot,
                        emptySlot,emptySlot,emptySlot,emptySlot,emptySlot,emptySlot,emptySlot};
    int [][] winningPos = {{0,1,2,3},{1,2,3,4},{2,3,4,5},{3,4,5,6},{7,8,9,10},{8,9,10,11},
                            {9,10,11,12},{10,11,12,13},{14,15,16,17},{15,16,17,18},{16,17,18,19},
                            {17,18,19,20},{21,22,23,24},{22,23,24,25},{23,24,25,26},{24,25,26,27},
                            {28,29,30,31},{29,30,31,32},{30,31,32,33},{31,32,33,34},{35,36,37,38},
                            {36,37,38,39},{37,38,39,40},{38,39,40,41},{35,28,21,14},{28,21,14,7},
                            {21,14,7,0},{36,29,22,15},{29,22,15,8},{22,15,8,1},{37,30,23,16},
                            {30,23,16,9},{23,16,9,2},{38,31,24,17},{31,24,17,10},{24,17,10,3},
                            {39,32,25,18},{32,25,18,11},{25,18,11,4},{40,33,26,19},{33,26,19,12},
                            {26,19,12,5},{41,34,27,20},{34,27,20,13},{27,20,13,6},{21,15,9,2},
                            {28,22,16,10},{22,16,10,4},{35,29,23,17},{29,23,17,11},{23,17,11,5},
                            {36,30,24,18},{30,24,18,12},{24,18,12,6},{37,31,25,19},{31,25,19,13},
                            {38,32,26,20},{3,11,19,27},{2,10,18,26},{10,18,26,34},{1,9,17,25},
                            {9,17,25,33},{17,25,33,41},{0,8,16,24},{8,16,24,32},{16,24,32,40},
                            {7,15,23,31},{15,23,31,39},{14,22,30,38}};

    public void dropin (View view){
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == emptySlot && gameIsActive) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000f);

            if (activePlayer == yellow) {
                counter.setImageResource(R.drawable.yellowplayer);
                activePlayer = red;
            } else {
                counter.setImageResource(R.drawable.redplayer);
                activePlayer = yellow;
            }

            for (int[] winningPos : winningPos){
                if (gameState[winningPos[0]] == gameState[winningPos[1]] && gameState[winningPos[1]]
                == gameState[winningPos[2]] && gameState[winningPos[2]] == gameState[winningPos[3]]
                && gameState[winningPos[0]] != emptySlot){

                   gameIsActive = false;
                   String winner = "Red Player";
                   if (gameState[winningPos[0]] == yellow){
                       winner = "Yellow Player";
                   }
                    TextView winnerMgs = (TextView) findViewById(R.id.winningText);
                   winnerMgs.setText(winner + "Victory!");
                    Button button = findViewById(R.id.playAgainButton);
                    button.setVisibility(View.VISIBLE);
                    TextView text = findViewById((R.id.winningText));
                    text.setVisibility(View.VISIBLE);

                } else {
                    boolean draw = true;
                    for (int counterState : gameState){
                        if (counterState == emptySlot) draw = false;
                    }
                    if (draw){
                        Button button = findViewById(R.id.playAgainButton);
                        button.setVisibility(View.VISIBLE);
                    }
                }
            }
        }

        counter.animate().translationYBy(1000f).setDuration(300);
    }

    public void newGame(View view) {

        gameIsActive = true;
        Button button = findViewById(R.id.playAgainButton);
        button.setVisibility(View.INVISIBLE);
        TextView text = findViewById((R.id.winningText));
        text.setVisibility(View.INVISIBLE);
        activePlayer = yellow;
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = emptySlot;
        }
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
