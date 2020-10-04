package com.example.diceroll;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences2;
    SharedPreferences.Editor editor2;
    Random random = new Random();
    private Button roll;
    private ImageView dice1, dice2;
    int diceValue1;
    int diceValue2;
    int image1;
    int image2;
    static boolean started = true;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        roll = (Button) findViewById(R.id.rollButton);
        dice1 = (ImageView) findViewById(R.id.diceIm1);
        dice2 = (ImageView) findViewById(R.id.diceIm2);
        sharedPreferences = getSharedPreferences("dice1", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        sharedPreferences2 = getSharedPreferences("dice2", Context.MODE_PRIVATE);
        editor2 = sharedPreferences2.edit();

        if (started == false) {
            setDiceOnCreate();
        }

        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice();
                started = false;
            }
        });
    }

    public void rollDice() {
        diceValue1 = random.nextInt(6) + 1;
        diceValue2 = random.nextInt(6) + 1;

        image1 = getResources().getIdentifier("ic_" + diceValue1, "drawable", "com.example.diceroll");
        image2 = getResources().getIdentifier("ic_" + diceValue2, "drawable", "com.example.diceroll");

        dice1.setImageResource(image1);
        dice2.setImageResource(image2);
        commitToSharedPreferences();
    }

    public void commitToSharedPreferences() {
        editor.putInt("dice1", diceValue1);
        editor2.putInt("dice2", diceValue2);
        editor.commit();
        editor2.commit();
    }

    public void setDiceOnCreate() {
        sharedPreferences = getSharedPreferences("dice1", Context.MODE_PRIVATE);
        sharedPreferences2 = getSharedPreferences("dice2", Context.MODE_PRIVATE);
        diceValue1 = sharedPreferences.getInt("dice1", 0);
        diceValue2 = sharedPreferences2.getInt("dice2", 0);
        image1 = getResources().getIdentifier("ic_" + diceValue1, "drawable", "com.example.diceroll");
        image2 = getResources().getIdentifier("ic_" + diceValue2, "drawable", "com.example.diceroll");
        dice1.setImageResource(image1);
        dice2.setImageResource(image2);
    }
}