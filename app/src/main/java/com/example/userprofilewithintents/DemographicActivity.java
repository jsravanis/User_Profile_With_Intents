package com.example.userprofilewithintents;

import static com.example.userprofilewithintents.IdentificationActivity.RESPONSE_KEY;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DemographicActivity extends AppCompatActivity implements View.OnClickListener {

    Response response;
    TextView educationText, maritalStatusText, livingStatusText, incomeText;

    ActivityResultLauncher<Intent> startSelectedActivityForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode() == RESULT_OK && result.getData() != null && result.getData().getSerializableExtra(RESPONSE_KEY) != null){
                response = (Response) result.getData().getSerializableExtra(RESPONSE_KEY);
                Log.d(MainActivity.TAG, "onActivityResult of Demographic Activity: "+response);
                if(response.getEducationLevel() != null)
                    educationText.setText(response.getEducationLevel());
                if(response.getMaritalStatus() != null)
                    maritalStatusText.setText(response.getMaritalStatus());
                if(response.getLivingStatus() != null)
                    livingStatusText.setText(response.getLivingStatus());
                if(response.getIncomeRange() != null)
                    incomeText.setText(response.getIncomeRange());
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demographic);

        educationText = findViewById(R.id.educationText);
        maritalStatusText = findViewById(R.id.maritalStatusText);
        livingStatusText = findViewById(R.id.livingStatusText);
        incomeText = findViewById(R.id.incomeText);

        if(getIntent() != null && getIntent().getExtras() != null && getIntent().hasExtra(RESPONSE_KEY)){
            response = (Response) getIntent().getSerializableExtra(RESPONSE_KEY);
            Log.d(MainActivity.TAG, "onCreate of Demographic Activity: "+response);
        }

        findViewById(R.id.educationSelectButton).setOnClickListener(this);
        findViewById(R.id.maritalStatusSelectButton).setOnClickListener(this);
        findViewById(R.id.livingStatusSelectButton).setOnClickListener(this);
        findViewById(R.id.incomeSelectButton).setOnClickListener(this);
        findViewById(R.id.demographicNextButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        if(v.getId() == R.id.educationSelectButton)
            intent = new Intent(DemographicActivity.this, SelectEducationActivity.class);
        else if (v.getId() == R.id.maritalStatusSelectButton)
            intent = new Intent(DemographicActivity.this, SelectMaritalStatusActivity.class);
        else if (v.getId() == R.id.livingStatusSelectButton)
            intent = new Intent(DemographicActivity.this, SelectLivingStatusActivity.class);
        else if (v.getId() == R.id.incomeSelectButton)
            intent = new Intent(DemographicActivity.this, SelectHouseholdIncomeActivity.class);

        if(intent != null && v.getId() != R.id.demographicNextButton){
            intent.putExtra(RESPONSE_KEY, response);
            Log.d(MainActivity.TAG, "From Demographic Activity: "+response);
            startSelectedActivityForResult.launch(intent);
        }

        if(v.getId() == R.id.demographicNextButton) {
            String education = educationText.getText().toString();
            String maritalStatus = maritalStatusText.getText().toString();
            String livingStatus = livingStatusText.getText().toString();
            String income = incomeText.getText().toString();
            Log.d(MainActivity.TAG, "onClick: ");
            if(education.equals("N/A") || maritalStatus.equals("N/A") || livingStatus.equals("N/A") || income.equals("N/A")){
                Log.d(MainActivity.TAG, "onClick: entered");
                Toast.makeText(this, "Please select all the demographic options", Toast.LENGTH_SHORT).show();
            }
            else {
                intent = new Intent(DemographicActivity.this, ProfileActivity.class);
                response.setEducationLevel(education);
                response.setMaritalStatus(maritalStatus);
                response.setLivingStatus(livingStatus);
                response.setIncomeRange(income);
                intent.putExtra(RESPONSE_KEY, response);
                Log.d(MainActivity.TAG, "From Demographic Activity: "+response);
                startActivity(intent);
            }
        }
    }
}