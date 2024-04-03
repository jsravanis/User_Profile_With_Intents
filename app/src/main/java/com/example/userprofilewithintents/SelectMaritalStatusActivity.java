package com.example.userprofilewithintents;

import static com.example.userprofilewithintents.IdentificationActivity.RESPONSE_KEY;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SelectMaritalStatusActivity extends AppCompatActivity {
    Response response;
    RadioGroup maritalStatusRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_marital_status);

        maritalStatusRadioGroup = findViewById(R.id.maritalStatusRadioGroup);

        if(getIntent() != null && getIntent().getExtras() != null && getIntent().hasExtra(RESPONSE_KEY)){
            Log.d(MainActivity.TAG, "onCreate of Select Marital Status Activity: "+response);
            response = (Response) getIntent().getSerializableExtra(RESPONSE_KEY);
        }

        findViewById(R.id.submitMaritalButton).setOnClickListener(v -> {
            int checkedRadioButtonId = maritalStatusRadioGroup.getCheckedRadioButtonId();
            if(checkedRadioButtonId == -1)
                Toast.makeText(SelectMaritalStatusActivity.this, "Select any marital status", Toast.LENGTH_SHORT).show();
            else {
                Intent intent = new Intent();
                RadioButton selectedButton = findViewById(checkedRadioButtonId);
                response.setMaritalStatus(selectedButton.getText().toString());
                intent.putExtra(RESPONSE_KEY, response);
                Log.d(MainActivity.TAG, "From Select Marital Status Activity: "+response);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        findViewById(R.id.cancelMaritalButton).setOnClickListener(v -> {
            finish();
        });
    }
}