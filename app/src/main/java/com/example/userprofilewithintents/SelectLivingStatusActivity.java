package com.example.userprofilewithintents;

import static com.example.userprofilewithintents.IdentificationActivity.RESPONSE_KEY;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SelectLivingStatusActivity extends AppCompatActivity {
    Response response;
    RadioGroup livingStatusRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_living_status);

        livingStatusRadioGroup = findViewById(R.id.livingStatusRadioGroup);

        if(getIntent() != null && getIntent().getExtras() != null && getIntent().hasExtra(RESPONSE_KEY)){
            Log.d(MainActivity.TAG, "onCreate of Select Living Status Activity: "+response);
            response = (Response) getIntent().getSerializableExtra(RESPONSE_KEY);
        }

        findViewById(R.id.submitLivingButton).setOnClickListener(v -> {
            int checkedRadioButtonId = livingStatusRadioGroup.getCheckedRadioButtonId();
            if(checkedRadioButtonId == -1)
                Toast.makeText(SelectLivingStatusActivity.this, "Select any living status", Toast.LENGTH_SHORT).show();
            else {
                Intent intent = new Intent();
                RadioButton selectedButton = findViewById(checkedRadioButtonId);
                response.setLivingStatus(selectedButton.getText().toString());
                intent.putExtra(RESPONSE_KEY, response);
                Log.d(MainActivity.TAG, "From Select Living Status Activity: "+response);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        findViewById(R.id.cancelLivingButton).setOnClickListener(v -> {
            finish();
        });
    }
}