package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;
    int activePlayer= 0;
    int[] gameState={2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winStates={{0, 1, 2}, {3, 4, 5}, {6, 7, 8}
                      ,{0, 3, 6}, {1, 4, 7}, {2, 5, 8}
                      ,{0, 4, 8}, {2, 4 , 6}};
    public void playerTap(View view){
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if(!gameActive){
            gameReset();
        }
        if(gameState[tappedImage] == 2){
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if(activePlayer == 0){
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("O turn - Tap to play");
            }
            else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X turn - Tap to play");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        for(int[] winPosition : winStates){
            if(gameState[winPosition[0]] == gameState[winPosition[1]] && gameState[winPosition[1]] == gameState[winPosition[2]] && gameState[winPosition[0]]!=2){
                String winnerSt;
                gameActive = false;
                if(gameState[winPosition[0]] == 0){
                    winnerSt = "X won";
                }
                else{
                    winnerSt = "0 won";
                }
                TextView status = findViewById(R.id.status);
                status.setText(winnerSt);
            }
        }
        if(gameState[0]!=2 && gameState[1]!=2 && gameState[2]!=2 && gameState[3]!=2 &&
                gameState[4]!=2 && gameState[5]!=2 && gameState[6]!=2 && gameState[7]!=2 &&
                gameState[8]!=2){
            TextView status = findViewById(R.id.status);
            status.setText("Match Withdrawed");
            gameActive = false;
        }
    }
    public void gameReset(){
        gameActive = true;
        activePlayer = 0;
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        ((ImageView) findViewById(R.id.imageButton0)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageButton1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageButton2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageButton3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageButton4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageButton5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageButton6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageButton7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageButton8)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("X turn - Tap to play");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}