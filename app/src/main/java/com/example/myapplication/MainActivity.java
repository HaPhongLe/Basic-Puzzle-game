package com.example.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final int COLUMN = 3;

    private static final int DIMENSIONS = COLUMN * COLUMN;

    private GestureDetectGridView mGridView;

    private int mColumnWidth, mColumnHeight;

    private String[] tileList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        scramble();



        setDimensions();
    }

    private void setDimensions() {
        ViewTreeObserver vto = mGridView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mGridView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int displayWidth = mGridView.getMeasuredWidth();
                int displayHeight = mGridView.getMeasuredHeight();

                int statusbarHeight = getStatusBarHeight(getApplicationContext());
                int requiredHeight = displayHeight - statusbarHeight;

                mColumnWidth = displayWidth/COLUMN;
                mColumnHeight = displayHeight/COLUMN;

                display();
            }
        });
    }

    private int getStatusBarHeight(Context context){
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen","android");

        if(resourceId >0){
            result = context.getResources().getDimensionPixelSize(resourceId);
        }

        return result;
    }

    private void display() {
        ArrayList<Button> buttons = new ArrayList<>();
        Button button;

        for (int i =0; i < tileList.length; i++)
        {
            button = new Button(this);
            if (tileList[i].equals("0")){
                button.setBackgroundResource(R.drawable.luffy_onepiece_1);
            }else if (tileList[i].equals("1")){
                button.setBackgroundResource(R.drawable.luffy_onepiece_2);
            }else if (tileList[i].equals("2")){
                button.setBackgroundResource(R.drawable.luffy_onepiece_3);
            }else if (tileList[i].equals("3")){
                button.setBackgroundResource(R.drawable.luffy_onepiece_4);
            }else if (tileList[i].equals("4")){
                button.setBackgroundResource(R.drawable.luffy_onepiece_5);
            }else if (tileList[i].equals("5")){
                button.setBackgroundResource(R.drawable.luffy_onepiece_6);
            }else if (tileList[i].equals("6")){
                button.setBackgroundResource(R.drawable.luffy_onepiece_7);
            }else if (tileList[i].equals("7")){
                button.setBackgroundResource(R.drawable.luffy_onepiece_8);
            }else if (tileList[i].equals("8")){
                button.setBackgroundResource(R.drawable.luffy_onepiece_9);
            }
            buttons.add(button);
        }

        mGridView.setAdapter(new CustomAdapter(buttons, mColumnWidth, mColumnHeight));
    }

    private void scramble(){
        int index;
        String temp;
        Random random = new Random();

        for (int i = tileList.length - 1; i >0; i--){
            index = random.nextInt(i + 1);
            temp = tileList[index];
            tileList[index] = tileList[i];
            tileList[i] = temp;
        }
    }

    private void init()
    {
        mGridView = (GestureDetectGridView) findViewById(R.id.grid);
        mGridView.setNumColumns(COLUMN);


        tileList = new String[DIMENSIONS];
        for (int i = 0; i < DIMENSIONS; i++){
            tileList[i] = String.valueOf(i);//assign the value for all the tiles in tilelist
        }

    }
}
