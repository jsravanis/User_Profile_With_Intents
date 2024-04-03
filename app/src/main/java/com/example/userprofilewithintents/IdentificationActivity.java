package com.example.userprofilewithintents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class IdentificationActivity extends AppCompatActivity {

    public static final String RESPONSE_KEY = "Response";
    EditText nameText, emailText;
    RadioGroup roleRadioGroup;
    Button identificationNextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identification);

        nameText = findViewById(R.id.nameText);
        emailText = findViewById(R.id.emailText);
        roleRadioGroup = findViewById(R.id.roleRadioGroup);
        identificationNextButton = findViewById(R.id.identificationNextButton);
        identificationNextButton.setOnClickListener(v -> {
            String name = nameText.getText().toString();
            String email = emailText.getText().toString();
            int checkedRadioButtonId = roleRadioGroup.getCheckedRadioButtonId();
            if(name.isEmpty() || email.isEmpty()){
                Toast.makeText(IdentificationActivity.this, "Name and Email shouldn't be empty", Toast.LENGTH_SHORT).show();
            }
            else if (checkedRadioButtonId == -1) {
                Toast.makeText(IdentificationActivity.this, "Select any role", Toast.LENGTH_SHORT).show();
            }
            else{
                RadioButton selectedButton = findViewById(checkedRadioButtonId);
                String role = selectedButton.getText().toString();
                Response response = new Response(name, email, role);
                Intent intent = new Intent(IdentificationActivity.this, DemographicActivity.class);
                intent.putExtra(RESPONSE_KEY, response);
                Log.d(MainActivity.TAG, "From Identification Activity: "+response);
                startActivity(intent);
            }
        });
    }
}