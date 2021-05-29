package com.kzawm.dicepiggame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button newGame;
    LinearLayout p1,p2;
    TextView title_p1,title_p2;
    TextView totalscore_p1,totalscore_p2;
    TextView currscore_p1,currscore_p2;
    EditText finalScore;
    ImageView image;
    Button roll,hold;
    Integer[] scores = {0,0};
    Integer holdscore = 0;
    Integer currPlayer = 1;
    Integer final_score = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newGame = (Button) findViewById(R.id.newGame);
        p1 = (LinearLayout) findViewById(R.id.player1Area);
        p2 = (LinearLayout) findViewById(R.id.player2Area);
        title_p1 = (TextView) findViewById(R.id.player1);
        title_p2 = (TextView) findViewById(R.id.player2);
        totalscore_p1 = (TextView) findViewById(R.id.player1Score);
        totalscore_p2 = (TextView) findViewById(R.id.player2Score);
        currscore_p1 = (TextView) findViewById(R.id.player1Cscore);
        currscore_p2 = (TextView) findViewById(R.id.player2Cscore);
        finalScore = (EditText) findViewById(R.id.finalScore);
        roll = (Button) findViewById(R.id.roll);
        hold = (Button) findViewById(R.id.hold);
        image = (ImageView) findViewById(R.id.dice);


        roll.setOnClickListener(this);
        hold.setOnClickListener(this);
        newGame.setOnClickListener(this);

        title_p1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_brightness_1_24, 0);
        p1.setBackgroundResource(R.drawable.active_player);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.newGame:
                scores[0] = 0;
                scores[1] = 0;
                holdscore = 0;
                currPlayer = 1;
                title_p1.setText("Player 1");
                title_p2.setText("Player 2");
                int color = Color.parseColor("#000000");
                title_p1.setTextColor(color);
                title_p2.setTextColor(color);
                title_p1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_brightness_1_24, 0);
                title_p2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_brightness_2_24, 0);
                p1.setBackgroundResource(R.drawable.active_player);
                p2.setBackgroundResource(R.drawable.inactive_player);
                totalscore_p1.setText("0");
                totalscore_p2.setText("0");
                currscore_p1.setText("0");
                currscore_p2.setText("0");
                roll.setEnabled(true);
                hold.setEnabled(true);
                image.setImageBitmap(null);
                break;
            case R.id.roll:
                int random = new Random().nextInt(6) + 1;
                int id = getResources().getIdentifier("com.kzawm.dicepiggame:drawable/dice" + random, null, null);
                image.setImageResource(id);
                if(random != 1){
                    holdscore += random;
                    if(currPlayer == 1) {
                        currscore_p1.setText(holdscore+"");
                    }
                    else{
                        currscore_p2.setText(holdscore+"");
                    }
                }
                else{
                    holdscore = 0;
                    if(currPlayer == 1) {
                        currscore_p1.setText(holdscore+"");
                        currPlayer = 2;
                        title_p2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_brightness_1_24, 0);
                        title_p1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_brightness_2_24, 0);
                        p2.setBackgroundResource(R.drawable.active_player);
                        p1.setBackgroundResource(R.drawable.inactive_player);
                    }
                    else{
                        currscore_p2.setText(holdscore+"");
                        currPlayer = 1;
                        title_p1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_brightness_1_24, 0);
                        title_p2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_brightness_2_24, 0);
                        p1.setBackgroundResource(R.drawable.active_player);
                        p2.setBackgroundResource(R.drawable.inactive_player);
                    }
                }

                break;
            case R.id.hold:
                if(!finalScore.getText().toString() .equals(""))
                    final_score = Integer.parseInt(finalScore.getText().toString());
                if(currPlayer == 1) {
                    scores[0] += holdscore;
                    holdscore = 0;
                    totalscore_p1.setText(scores[0]+"");
                    if(scores[0] < final_score) {
                        currscore_p1.setText("0");
                        currPlayer = 2;
                        title_p2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_brightness_1_24, 0);
                        title_p1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_brightness_2_24, 0);
                        p2.setBackgroundResource(R.drawable.active_player);
                        p1.setBackgroundResource(R.drawable.inactive_player);
                    }
                    else{
                        title_p1.setText("WINNER");
                        int c = Color.parseColor("#ff0000");
                        title_p1.setTextColor(c);
                        title_p1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_brightness_2_24, 0);
                        roll.setEnabled(false);
                        hold.setEnabled(false);

                    }
                }
                else{
                    scores[1] += holdscore;
                    holdscore = 0;
                    totalscore_p2.setText(scores[1]+"");
                    if(scores[1] < final_score) {
                        currscore_p2.setText("0");
                        currPlayer = 1;
                        title_p1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_brightness_1_24, 0);
                        title_p2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_brightness_2_24, 0);
                        p1.setBackgroundResource(R.drawable.active_player);
                        p2.setBackgroundResource(R.drawable.inactive_player);
                    }
                    else{
                        title_p2.setText("WINNER");
                        int c1 = Color.parseColor("#ff0000");
                        title_p2.setTextColor(c1);
                        title_p2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_brightness_2_24, 0);
                        roll.setEnabled(false);
                        hold.setEnabled(false);
                    }
                }
                break;
        }
    }
}