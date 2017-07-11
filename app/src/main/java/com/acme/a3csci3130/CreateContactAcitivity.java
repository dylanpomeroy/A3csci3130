package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateContactAcitivity extends Activity {

    private Button submitButton;
    private EditText numberField, nameField, primaryField, addressField, provinceField;
    private MyApplicationData appState;

    /**
     * Initialization method that runs when the activity's intent is invoked.
     * @param savedInstanceState The saved instance state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact_acitivity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        submitButton = (Button) findViewById(R.id.submitButton);
        numberField = (EditText) findViewById(R.id.txtNumber);
        nameField = (EditText) findViewById(R.id.txtName);
        primaryField = (EditText) findViewById(R.id.txtPrimary);
        addressField = (EditText) findViewById(R.id.txtAddress);
        provinceField = (EditText) findViewById(R.id.txtProvince);
    }

    /**
     * Submits the information provided in the text fields to a firebase object.
     * @param v The view of the item.
     */
    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        String businessID = appState.firebaseReference.push().getKey();
        Business business = new Business(
                businessID,
                numberField.getText().toString(),
                nameField.getText().toString(),
                primaryField.getText().toString(),
                addressField.getText().toString(),
                provinceField.getText().toString()
        );

        appState.firebaseReference.child(businessID).setValue(business);

        finish();

    }
}
