package com.nevictus.android.buttonclickapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    //Declare variable to store our widgets references into
    private EditText userInput;
    private TextView textView;
    private static final String TAG = "MainActivity";

    private final String TEXT_CONTENTS = "Textcontents";

    //keep track of # of times  widget was clicked
    //private int numTimesClicked = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: We are in onCreate method ");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find by id to established reference to our widgets
        userInput = findViewById(R.id.editText2);
        Button button = findViewById(R.id.button2);
        textView = findViewById(R.id.textView4);

        //remove default text "TextView"
        textView.setText("");
        //remove default text "EditText"
        userInput.setText("");

        //Let's make the textView Scroll
        textView.setMovementMethod(new ScrollingMovementMethod());

        //Create an object of the type OnClickListener
        View.OnClickListener ourOnClickListener = new View.OnClickListener() {

            //Override the onClick() with our own instructions
            @Override
            public void onClick(View v) {


                //  numTimesClicked +=  1;
//                //String message to display on screen
//                String result = "The button got tapped " + numTimesClicked + " time";
//                //chain the string stored in result to app textView
//
//                //Add an "s " time if button was pressed more than once
//                if(numTimesClicked != 1){
//                    result += "s";
//                }
//                //remove the blank line at the start of the plain text "Name" with "n"
//                result += "\n";
//
//                textView.append(result);

                //Let's try get the text this time from the Plain Text field
                // when a user type something instead of hardcoded text.
                //Override the onClick() with our own instructions


                //Chain .toString() to return a String instead of Editable text causing error
                String result = userInput.getText().toString();
                result += "\n";
                textView.append(result);
                userInput.setText("");


            }// end onClick()

        };
        //chaining/passing our object method above the button to register the click
        button.setOnClickListener(ourOnClickListener);
        Log.d(TAG, "onCreate: out of onCreate methode");


    } //end onCreate


    //Override this lifecycle activity to get the info from method below
    // and save it on the textView when Activity state is restored
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(TAG, "onRestoreInstanceState: we're in onRestoreInstanceState");

        super.onRestoreInstanceState(savedInstanceState);

        //--Retrieve the value save from the Bundle & assign that to a string--

        //String savedString = savedInstanceState.getString(TEXT_CONTENTS);
        //textView.setText(savedString);

        //Easier way than 2 lines above
        textView.setText(savedInstanceState.getString(TEXT_CONTENTS));

        Log.d(TAG, "onRestoreInstanceState: we're out onRestoreInstanceState");
    }


    //Use this activity lifecycle method to pass the Bundle Key-value info
    // from our activity

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState: in");

        //Save the value of the textView into that Bundle.
        //We put this above the super because it is the one that save to the Bundle
        outState.putString(TEXT_CONTENTS, textView.getText().toString());
        //
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: out");
    }

    //This is place to always save permanent user data in your app lifeCycle.
    @Override
    protected void onPause() {
        super.onPause();
    }
}//end class
