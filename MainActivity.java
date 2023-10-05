package com.example.assignment1_v2;

/**
 * Mobile Application Development Assignment #1 - SOFE4640U
 * Student Name: Sanjigth Gnanabaskaran
 * Student ID: 100635268
 * Due Date: October 4th, 2023 @ 11:59pm
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // In order to use the user input values, the declarations for variables,
    // XML file parameters(EditText, TextView & Button) must be stated here for
    // use within the java file.
    double mortgagePrinciple, amortizationYears, interestRate;
    EditText mortgage_principle_amount;
    EditText interest_rate;
    EditText amortization_period;
    TextView monthly_payment_text;
    Button calculate_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // This line of code is necessary to have new content displayed,
        // and the use case for this is the information button on the main page.
        setContentView(R.layout.activity_main);

        // The XML file palette usage (EditView, TextView and Button) must be
        // located by the id used within the code so the program is able to access
        // these parameters that are being used for the user input purpose.
        mortgage_principle_amount = (EditText) findViewById(R.id.mortgage_principle_amount);
        amortization_period = (EditText) findViewById(R.id.amortization_period);
        interest_rate = (EditText) findViewById(R.id.interest_rate);
        calculate_button = (Button) findViewById(R.id.calculate_button);

        calculate_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // On clicking the CALCULATE button, the program will take the user inputs
                // and send it through this function call
               calculateInput();
            }
        });

    }
    private void calculateInput() {

        // In order to use the user input values, the program must convert the values into
        // numerical values that can be used, otherwise it would be processed as garbage data
        mortgagePrinciple = Integer.valueOf(mortgage_principle_amount.getText().toString());
        amortizationYears = Integer.valueOf(amortization_period.getText().toString());
        interestRate = Integer.valueOf(interest_rate.getText().toString());
        monthly_payment_text = (TextView) findViewById(R.id.monthly_payment_text);

        // This is the calculation formula that is being used for the purpose of
        // replicating the mortgage payment calculator, this formula is most likely
        // not the same as the one used in the reference as it was difficult to
        // find a universal one that worked based on the conditions available.
        double paymentFrequency = amortizationYears * 12;
        double frequencyCalc = (1 + (interestRate/100));
        double totalFrequencyCalc = Math.pow(frequencyCalc, paymentFrequency);
        double numeratorCalc = (interestRate/100)* totalFrequencyCalc;
        double denominatorCalc = totalFrequencyCalc - 1;
        double totalFraction = numeratorCalc / denominatorCalc;
        // int conversion on the final amount due to double producing significant
        // amount of decimals, therefore causing the result to be long in size.
        double paymentAmount = (int) (mortgagePrinciple * totalFraction);

        // In order to display the monthly payment, the TextView  is being used
        // with the .setTest function within the library to display the result.
        monthly_payment_text.setText("Your monthly payments would be:$" + paymentAmount);
    }
    // Intent used to re-route user to get more information from
    // external website, in order to get an understanding on the calculator
    public void infoIntent(View view) {
        String url = "https://www.td.com/ca/en/personal-banking/products/mortgages/calculators-tools";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}