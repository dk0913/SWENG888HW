package com.SWENG888.Homework1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    /*creating EditText object to get user's first name*/
    private EditText mFirstNameEditText;
    /*creating EditText object to get user's last name*/
    private EditText mLastNameEditText;
    /*creating EditText object to get user's date of birth*/
    private EditText mEditTextDOB;
    /*creating Button for user to click to trigger age calculation and display*/
    private Button mButton;

    /*onCreate creates the activity and wires up java objects to UI for user interaction */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*calling creation method for parent class*/
        super.onCreate(savedInstanceState);
        /*displays main activity on screen*/
        setContentView(R.layout.activity_main);

        /*initializing EditText object to wire it to the corresponding first name UI View */
        mFirstNameEditText = findViewById(R.id.firstNameEditText);
        /*initializing EditText object to wire it to the corresponding last name UI View */
        mLastNameEditText = findViewById(R.id.lastNameEditText);
        /*initializing EditText object to wire it to the corresponding date of birth UI View */
        mEditTextDOB = findViewById(R.id.editTextDOB);
        /*initializing Button object to wire it to the corresponding button UI View */
        mButton = findViewById(R.id.button);

        /*adding an OnClickListener to listen for the user clicking the button and trigger
        age calculation and toast display*/
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*storing user input first and last names in string objects*/
                String firstName = mFirstNameEditText.getText().toString();
                String lastName = mLastNameEditText.getText().toString();

                /*storing user input date of birth (DOB) in string objects*/
                String dateString = mEditTextDOB.getText().toString();
                /*initializing a dateObject for user's dob and storing today's date, */
                Date dateObject = new Date();
                Date today = new Date();

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");

                boolean goodInput = true;

                /*the try catch block below tries to parse the string version of the user's DOB
                into a Date object, and catches the ParseException if the user enters
                 an invalid date, then displays a toast prompting the user to retry the DOB input*/

                try {
                    //parsing user's birthdate and today's date into Date objects
                    dateObject = simpleDateFormat.parse(dateString);
                    today = simpleDateFormat.parse("2023-03-26");

                }
                catch(ParseException e){
                    Toast errorToast = Toast.makeText(view.getContext(),"Error: Date not in YYYY-MM-DD format, " +
                            "please try again",Toast.LENGTH_LONG);
                    errorToast.setGravity(0,0,0);
                    errorToast.show();
                    goodInput = false;
                }
                //Checking to see if input is valid before displaying age toast
                if(goodInput && dateObject.before(today) && (today.getYear()-dateObject.getYear()<1000)){
                    /*Calculating the user's age by subtracting year from current year */
                    String age = Integer.toString(today.getYear()-dateObject.getYear());

                    /*initializes a Toast object that displays the users age, sets the position on the screen
                    * using setGravity() method and displays the toast*/
                    Toast ageToast = Toast.makeText(view.getContext(), "Name: "+firstName+" "+lastName+"\n"+ "Your Age Is: "+age, Toast.LENGTH_LONG);
                    ageToast.setGravity(0,0,0);
                    ageToast.show();
                }else{
                    /*displaying toast to user indicating they have indicated a date in the wrong format
                    (that didn't cause a ParseException) or is a date in the future*/
                    Toast errorToast = Toast.makeText(view.getContext(),"Error: Date not in YYYY-MM-DD format or is in future " +
                            ", please try again",Toast.LENGTH_LONG);
                    errorToast.setGravity(0,0,0);
                    errorToast.show();
                }
            }
        });
    }

}