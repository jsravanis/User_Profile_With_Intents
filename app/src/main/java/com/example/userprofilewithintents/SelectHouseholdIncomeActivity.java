package com.example.userprofilewithintents;

import static com.example.userprofilewithintents.IdentificationActivity.RESPONSE_KEY;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

public class SelectHouseholdIncomeActivity extends AppCompatActivity {
    Response response;
    SeekBar incomeSeekBar;
    String[] incomeRangeValues;
    TextView householdIncomeRange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_household_income);

        incomeRangeValues = getResources().getStringArray(R.array.incomeRangeValues);
        incomeSeekBar = findViewById(R.id.incomeSeekBar);
        householdIncomeRange = findViewById(R.id.householdIncomeRange);
        householdIncomeRange.setText(incomeRangeValues[0]);

        if(getIntent() != null && getIntent().getExtras() != null && getIntent().hasExtra(RESPONSE_KEY)){
            Log.d(MainActivity.TAG, "onCreate of Select Household Income Activity: "+response);
            response = (Response) getIntent().getSerializableExtra(RESPONSE_KEY);
        }

        incomeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                householdIncomeRange.setText(incomeRangeValues[progress]);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        findViewById(R.id.submitHouseholdButton).setOnClickListener(v -> {
            Intent intent = new Intent();
            response.setIncomeRange(incomeRangeValues[incomeSeekBar.getProgress()]);
            intent.putExtra(RESPONSE_KEY, response);
            Log.d(MainActivity.TAG, "From Select Household Income Activity: "+response);
            setResult(RESULT_OK, intent);
            finish();
        });

        findViewById(R.id.cancelHouseholdButton).setOnClickListener(v -> {
            finish();
        });
    }
}