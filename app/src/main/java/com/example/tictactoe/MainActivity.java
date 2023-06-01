package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    //0:red   1: yellow   2: empty
    int[] gameState={2,2,2,2,2,2,2,2,2};


    int[][] winPos= {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    int activePlayer = 0 ;

    boolean gameFinished = false;

    public void dropin(View view) {

        ImageView place = (ImageView) view;

        int tapPlace = Integer.parseInt(place.getTag().toString());

        if (gameState[tapPlace]==2 && !gameFinished) {

            gameState[tapPlace] = activePlayer;

            place.setTranslationY(-1500);

            if (activePlayer == 0) {
                place.setImageResource(R.drawable.red);
                activePlayer = 1;
            } else {
                place.setImageResource(R.drawable.yellow);
                activePlayer = 0;
            }

            place.animate().translationYBy(1500).rotationXBy(1800).setDuration(1000);

            for (int[] winArr : winPos) {

                if (gameState[winArr[0]] == gameState[winArr[1]] && gameState[winArr[2]] == gameState[winArr[1]] && gameState[winArr[0]] != 2) {

                    String winner = "";
                    if (activePlayer == 1) {
                        winner = "Red";
                        gameFinished = true;
                    }
                    else {
                        winner = "Yellow";
                        gameFinished = true;
                    }
                    Button playAgain = (Button) findViewById(R.id.button);
                    TextView winnerView = (TextView)findViewById(R.id.winnerView);
                    winnerView.setText(winner + " Won🥳");
                    playAgain.setVisibility(View.VISIBLE);
                    winnerView.setVisibility(View.VISIBLE);

                }



            }
        }
        boolean found = false;
        int val = 2;
        for (int x : gameState){
            if (x == val){
                found = true;
                break;
            }
        }
        if (gameFinished){
            Toast.makeText(this, "Game finished!! Play Again", Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, String.valueOf(found), Toast.LENGTH_SHORT).show();
        }
        if (!gameFinished && !found){
            Button playAgain = (Button) findViewById(R.id.button);
            TextView winnerView = (TextView)findViewById(R.id.winnerView);
            winnerView.setText("DRAW 🙂");
            Toast.makeText(this, "Draw!! Play Again", Toast.LENGTH_SHORT).show();
            playAgain.setVisibility(View.VISIBLE);
            winnerView.setVisibility(View.VISIBLE);
        }

    }

    public void playGameAgain(View view){

        Button playAgain = (Button) findViewById(R.id.button);
        TextView winnerView = (TextView)findViewById(R.id.winnerView);
        winnerView.setText(" Won🥳");
        playAgain.setVisibility(View.INVISIBLE);
        winnerView.setVisibility(View.INVISIBLE);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLay);
        for(int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        Arrays.fill(gameState, 2);
        activePlayer = 0 ;
        gameFinished = false;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}