package com.example.elevator2;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ImageView imgMan;
    private FloatingActionButton btnOne,btnTwo,btnThree;
    private TextView txtFloorNumber,txtTimeRemaining;
    private ExtendedFloatingActionButton button;
    private Button start;

    ArrayList<Integer> myArray;
    public Elevator elevator;
    public CountDownTimer countDownTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize UI elements
        initViews();

        //Create elevator class instance
        elevator = new Elevator(4);

        //initialize array for movement
        myArray = new ArrayList<>();
        myArray.add(0,0);

        //initialize countdown timer
        countDownTimer = new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                txtTimeRemaining.setText("Time remaining: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                txtTimeRemaining.setText("Moving to ground floor");
                moveToGround();
            }
        };

        //Start the elevator
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.start();
            }
        });

        //Get user input at ground floor
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animate(elevator.pressButton('G'));
                countDownTimer.cancel();
                countDownTimer.start();
            }
        });

        //Get user input at first floor
        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animate(elevator.pressButton('A'));
                countDownTimer.cancel();
                countDownTimer.start();
            }
        });

        //Get user input at second floor
        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animate(elevator.pressButton('B'));
                countDownTimer.cancel();
                countDownTimer.start();
            }
        });

        //Get user input at third floor
        btnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animate(elevator.pressButton('C'));
                countDownTimer.cancel();
                countDownTimer.start();
            }
        });

    }

    //Return to ground floor(resting state) on no input
    private void moveToGround() {
        animate(elevator.pressButton('G'));
    }

    //Animate icon based on user input
    private void animate(int current){
        switch (current){
            case 0:
                txtFloorNumber.setText("Ground");
                myArray.add(1,current);
                move((myArray.get(1)-myArray.get(0))*40);
                myArray.clear();
                myArray.add(0,current);
                break;
            case 1:
                txtFloorNumber.setText("First");
                myArray.add(1,current);
                move((myArray.get(1)-myArray.get(0))*40);
                myArray.clear();
                myArray.add(0,current);
                break;
            case 2:
                txtFloorNumber.setText("Second");
                myArray.add(1,current);
                move((myArray.get(1)-myArray.get(0))*40);
                myArray.clear();
                myArray.add(0,current);
                break;
            case 3:
                txtFloorNumber.setText("Third");
                myArray.add(1,current);
                move((myArray.get(1)-myArray.get(0))*40);
                myArray.clear();
                myArray.add(0,current);
                break;
            default:
                Toast.makeText(this, "Invalid floor number", Toast.LENGTH_SHORT).show();
                break;

        }
    }


    //Compute the number of pixels for moving and duration of animation
    private void move(float pixel){
        float pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, pixel, getResources().getDisplayMetrics());
        float currentPos = imgMan.getTranslationY();
        ObjectAnimator animation = ObjectAnimator.ofFloat(imgMan, "translationY", currentPos - pixels);
        animation.setDuration(1500); // 1 second
        animation.start();
    }

    //Destroy time on app closure to avoid memory leaks
    @Override
    protected void onDestroy() {
        super.onDestroy();
        countDownTimer.cancel();
    }

    //Initialize UI elements
    private void initViews() {
        imgMan = findViewById(R.id.man);
        btnOne = findViewById(R.id.btnOne);
        btnTwo = findViewById(R.id.btnTwo);
        btnThree = findViewById(R.id.btnThree);
        button = findViewById(R.id.btnMove);
        txtFloorNumber = findViewById(R.id.txtFloorNumber);
        start = findViewById(R.id.btnStart);
        txtTimeRemaining = findViewById(R.id.txtTimeRemaining);
    }
}
