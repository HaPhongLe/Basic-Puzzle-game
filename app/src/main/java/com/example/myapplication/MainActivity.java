package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final int COLUMN = 3;

    private static final int DIMENSIONS = COLUMN * COLUMN;

    private String[] tileList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        scramble();

        display();
    }

    private void display() {

    }

    private void scramble(){
        int index;
        String temp;
        Random random = new Random();

        for (int i = tileList.length - 1; i <0; i--){
            index = random.nextInt(i + 1);
            temp = tileList[index];
            tileList[index] = tileList[i];
            tileList[i] = temp;
        }
    }

    private void init()
    {
        tileList = new String[DIMENSIONS];
        for (int i = 0; i < DIMENSIONS; i++){
            tileList[i] = String.valueOf(i);//assign the value for all the tiles in tilelist
        }

    }
}
