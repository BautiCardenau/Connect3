package com.example.bautistacardenau.connect3;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0:rojo; 1:verde ; 2: vacio
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive = true;
    int steps = 0;
    int stopper = 0;
    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        Log.i("Tag", counter.getTag().toString());

        Log.i("INFO", Integer.toString(steps));
        int tappedCounter = Integer.parseInt(counter.getTag().toString()); //asigno el valor del tag a la variable de la imagen que aprete
        if (steps < 10){
        if (gameState[tappedCounter] == 2 && gameActive) {
            gameState[tappedCounter] = activePlayer; //lo que hago aca es asignar el color a la imagen en la que aprete
            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.roja128);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.verde128b);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).rotationBy(3600).setDuration(400);
            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    //Si se cumplen es que alguien gano
                    gameActive = false; //con esto establezco que el juego termino
                    String winner = "";
                    //Como yo puse que se cambie automaticamente una vez que apreta un jugador, el que gana es el contrario al jugador activo
                    if (activePlayer == 1) {
                        winner = "Red";
                    } else {
                        winner = "Green";
                    }
                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                    winnerTextView.setText(winner + " has won!");
                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);
                    steps = -1;
                }

                }
                    steps = steps + 1;
            }



        }
        stopper = steps - 1;
        Log.i("Steps", Integer.toString(steps));
        Log.i("Stopper", Integer.toString(stopper));
        if (stopper == 8){
            Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

            TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

            playAgainButton.setVisibility(View.VISIBLE);

            winnerTextView.setVisibility(View.VISIBLE);

            android.support.v7.widget.GridLayout gridLayout = (android.support.v7.widget.GridLayout) findViewById(R.id.gridLayout);

            winnerTextView.setText("It is a draw!");
//queria hacer dos contadores por separado, fijares cuando uno llegue a 10 el otro sea 9
            steps = 0;
            stopper = 0;

        }
    }

    public void playAgain(View view) {

        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

        playAgainButton.setVisibility(View.INVISIBLE);

        winnerTextView.setVisibility(View.INVISIBLE);

        android.support.v7.widget.GridLayout gridLayout = (android.support.v7.widget.GridLayout) findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount(); i++) {

            ImageView counter = (ImageView) gridLayout.getChildAt(i);

            counter.setImageDrawable(null);

        }

        for (int i=0; i<gameState.length; i++) {

            gameState[i] = 2;

        }

        activePlayer = 0;

        gameActive = true;

        steps = 0;
        stopper = 0;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
