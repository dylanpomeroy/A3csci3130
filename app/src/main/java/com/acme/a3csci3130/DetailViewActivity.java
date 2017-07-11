package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DetailViewActivity extends Activity {

    private EditText numberField, nameField, primaryField, addressField, provinceField;
    Business receivedBusinessInfo;
    private MyApplicationData appState;

    /**
     * Initialization method that runs when the activity's intent is invoked.
     * @param savedInstanceState The saved instance state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedBusinessInfo = (Business)getIntent().getSerializableExtra("Business");

        appState = ((MyApplicationData) getApplicationContext());

        numberField = (EditText) findViewById(R.id.txtNumber);
        nameField = (EditText) findViewById(R.id.txtName);
        primaryField = (EditText) findViewById(R.id.txtPrimary);
        addressField = (EditText) findViewById(R.id.txtAddress);
        provinceField = (EditText) findViewById(R.id.txtProvince);

        if(receivedBusinessInfo != null){
            numberField.setText(receivedBusinessInfo.number);
            nameField.setText(receivedBusinessInfo.name);
            primaryField.setText(receivedBusinessInfo.primary);
            addressField.setText(receivedBusinessInfo.address);
            provinceField.setText(receivedBusinessInfo.province);
        }
    }

    /**
     * Updates the business entry in Firebase with the text specified in the text fields.
     * @param v The view sent from the onclick action.
     */
    public void updateContact(View v){
        receivedBusinessInfo.number = numberField.getText().toString();
        receivedBusinessInfo.name = nameField.getText().toString();
        receivedBusinessInfo.primary = primaryField.getText().toString();
        receivedBusinessInfo.address = addressField.getText().toString();
        receivedBusinessInfo.province = provinceField.getText().toString();

        appState.firebaseReference.child(receivedBusinessInfo.id).updateChildren(receivedBusinessInfo.toMap());
    }

    /**
     * Erases the contact from firebase.
     * @param v The view of the item.
     */
    public void eraseContact(View v)
    {
        appState.firebaseReference.child(receivedBusinessInfo.id).removeValue();
    }
}
