package com.connollyed.braintrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initalise
        final Button button0 = (Button) findViewById(R.id.button0);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);

        final ArrayList<Button> buttons_list = new ArrayList<>();
        buttons_list.add(button0);
        buttons_list.add(button1);
        buttons_list.add(button2);
        buttons_list.add(button3);

        //Generate Random Number
        Random gen = new Random();
        int num1 = gen.nextInt(50);
        int num2 = gen.nextInt(50);
        answer = num1 + num2;

        TextView formula = (TextView) findViewById(R.id.formula_text);
        formula.setText(num1 + " + " + num2);

        // LinkedList to hold answers to be displayed on buttons
        LinkedList<Integer> Answers = new LinkedList<>();
        Answers.add(answer);

        //Generate 3 more random answers and add them to Answers List
        Answers.add(gen.nextInt(50));
        Answers.add(gen.nextInt(50));
        Answers.add(gen.nextInt(50));

        Collections.shuffle(Answers);

        for(int i=0;i<Answers.size();i++){
            buttons_list.get(i).setText(String.valueOf(Answers.get(i)));
            buttons_list.get(i).setTag(String.valueOf(Answers.get(i)));
        }


        final TextView timer_txt = (TextView) findViewById(R.id.timer_text);
        CountDownTimer timer = new CountDownTimer(30*1000+300, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer_txt.setText(String.valueOf((int)(millisUntilFinished / 1000)));
            }

            @Override
            public void onFinish() {
                timer_txt.setText("0");
                for(int i=0;i<buttons_list.size();i++){
                    buttons_list.get(i).setClickable(false);
                }
            }
        }.start();

    }

    private int correct = 0;
    private int total_questions = 0;
    public void answer(View view) {

        Log.i("TAG = ", (String) view.getTag());

        // Initalise
        Button button0 = (Button) findViewById(R.id.button0);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);

        ArrayList<Button> buttons_list = new ArrayList<>();
        buttons_list.add(button0);
        buttons_list.add(button1);
        buttons_list.add(button2);
        buttons_list.add(button3);


       if(view.getTag().equals(String.valueOf(answer))) {
           Log.i("ANSWER", "CORRECT");
           correct++;
       } else {
           Log.i("ANSWER", "WRONG");
       }
        total_questions++;

        //Update Correct/Total Stats
        TextView stats = (TextView) findViewById(R.id.stats_text);
        stats.setText(correct + " / " + total_questions);


        //Generate Random Number
        Random gen = new Random();
        int num1 = gen.nextInt(50);
        int num2 = gen.nextInt(50);
        answer = num1 + num2;

        TextView formula = (TextView) findViewById(R.id.formula_text);
        formula.setText(num1 + " + " + num2);

        // LinkedList to hold answers to be displayed on buttons
        LinkedList<Integer> Answers = new LinkedList<>();
        Answers.add(answer);

        //Generate 3 more random answers and add them to Answers List
        Answers.add(gen.nextInt(50));
        Answers.add(gen.nextInt(50));
        Answers.add(gen.nextInt(50));

        Collections.shuffle(Answers);

        for(int i=0;i<Answers.size();i++){
            buttons_list.get(i).setText(String.valueOf(Answers.get(i)));
            buttons_list.get(i).setTag(String.valueOf(Answers.get(i)));
        }
    }
}
