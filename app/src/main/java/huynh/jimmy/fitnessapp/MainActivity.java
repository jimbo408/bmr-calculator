package huynh.jimmy.fitnessapp;
import android.widget.EditText;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.TextView;
import android.text.TextUtils;



public class MainActivity extends AppCompatActivity {

    private String selectedGender;
    private String selectedGoal;
    private String selectedActivity;
    private double gender_val1,gender_val2,gender_val3,gender_val4;
    private double activity_value;
    private int goal_value;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner genderSpinner = (Spinner) findViewById(R.id.gender_spinner);
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                //set values based on gender selection
                selectedGender = parent.getItemAtPosition(pos).toString();
                if (selectedGender.equals("Male")) {
                    gender_val1 = 66;
                    gender_val2 = 6.23;
                    gender_val3 = 12.7;
                    gender_val4 = 6.8;
                }

                else {
                    gender_val1 = 655;
                    gender_val2 = 4.35;
                    gender_val3 = 4.7;
                    gender_val4 = 4.7;
                }

            }

            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        Spinner activitySpinner = (Spinner) findViewById(R.id.activity_spinner);
        activitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                selectedActivity = parent.getItemAtPosition(pos).toString();

                //set activity level selected
                switch(selectedActivity) {
                    case "Sedentary":
                        activity_value = 1.2;
                        break;
                    case "Lightly active":
                        activity_value = 1.375;
                        break;
                    case "Moderately active":
                        activity_value = 1.55;
                        break;
                    case "Very active":
                        activity_value = 1.725;
                        break;
                    case "Extra active":
                        activity_value = 1.9;
                        break;
                }

            }

            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        Spinner goalSpinner = (Spinner) findViewById(R.id.goal_spinner);
        goalSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                selectedGoal = parent.getItemAtPosition(pos).toString();

                //set selected goal
                switch(selectedGoal) {
                    case "Weight loss":
                        goal_value = -500;
                        break;
                    case "Maintain":
                        goal_value = 0;
                        break;
                    case "Weight gain":
                        goal_value = 500;
                        break;

                }

            }

            public void onNothingSelected(AdapterView<?> parent) {

            }

        });


        Button reset = (Button) findViewById(R.id.resetBtn);
        reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText getAge = (EditText) findViewById(R.id.setAge);
                EditText getheight = (EditText) findViewById(R.id.setHeight);
                EditText getweight = (EditText) findViewById(R.id.setWeight);
                TextView results = (TextView) findViewById( R.id.results);

                //reset all text input fields to empty
                getAge.setText("");
                getheight.setText("");
                getweight.setText("");
                results.setText("");


            }
        });

        Button submit = (Button) findViewById(R.id.submitBtn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText getAge = (EditText) findViewById(R.id.setAge);
                EditText getHeight = (EditText) findViewById(R.id.setHeight);
                EditText getWeight = (EditText) findViewById(R.id.setWeight);
                TextView results = (TextView) findViewById( R.id.results);

                String ageText = getAge.getText().toString();
                String heightText = getHeight.getText().toString();
                String weightText = getWeight.getText().toString();

                //displays error message if text fields are empty
                if (TextUtils.isEmpty(ageText)) {
                        getAge.setError( "Enter a valid age" );
                        return;
                    }
                if (TextUtils.isEmpty(heightText)) {
                        getHeight.setError("Enter a valid height");
                        return;
                    }
                if (TextUtils.isEmpty(weightText)) {
                        getWeight.setError("Enter a valid weight");
                        return;
                    }

                int age = Integer.parseInt(ageText);
                int height = Integer.parseInt(heightText);
                int weight = Integer.parseInt(weightText);


                //calculate and display BMR
                int bmr = (int) Math.round(gender_val1 + (gender_val2 * weight) + (gender_val3 * height)
                        - (gender_val4 * age));
                int result = (int) (bmr * activity_value) + goal_value;
                results.setText("BMR: "+ bmr + "\nCalories per day: " + result);

                }


        });


    }
}

