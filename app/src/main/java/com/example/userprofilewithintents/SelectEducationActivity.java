package com.example.userprofilewithintents;

import static com.example.userprofilewithintents.IdentificationActivity.RESPONSE_KEY;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SelectEducationActivity extends AppCompatActivity {
    Response response;
    RadioGroup educationLevelRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_education);

        educationLevelRadioGroup = findViewById(R.id.educationLevelRadioGroup);

        if(getIntent() != null && getIntent().getExtras() != null && getIntent().hasExtra(RESPONSE_KEY)){
            Log.d(MainActivity.TAG, "onCreate of Select Education Activity: "+response);
            response = (Response) getIntent().getSerializableExtra(RESPONSE_KEY);
        }

        findViewById(R.id.submitEducationButton).setOnClickListener(v -> {
            int checkedRadioButtonId = educationLevelRadioGroup.getCheckedRadioButtonId();
            if(checkedRadioButtonId == -1)
                Toast.makeText(SelectEducationActivity.this, "Select any education level", Toast.LENGTH_SHORT).show();
            else {
                Intent intent = new Intent();
                RadioButton selectedButton = findViewById(checkedRadioButtonId);
                response.setEducationLevel(selectedButton.getText().toString());
                intent.putExtra(RESPONSE_KEY, response);
                Log.d(MainActivity.TAG, "From Select Education Activity: "+response);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        findViewById(R.id.cancelEducationButton).setOnClickListener(v -> {
            finish();
        });
    }
}