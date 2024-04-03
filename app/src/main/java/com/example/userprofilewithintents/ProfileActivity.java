package com.example.userprofilewithintents;

import static com.example.userprofilewithintents.IdentificationActivity.RESPONSE_KEY;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    Response response;

    TextView profileNameText, profileEmailText, profileRoleText, profileEducationText,
            profileMaritalStatusText, profileLivingStatusText, profileIncomeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileNameText = findViewById(R.id.profileNameText);
        profileEmailText = findViewById(R.id.profileEmailText);
        profileRoleText = findViewById(R.id.profileRoleText);
        profileEducationText = findViewById(R.id.profileEducationText);
        profileMaritalStatusText = findViewById(R.id.profileMaritalStatusText);
        profileLivingStatusText = findViewById(R.id.profileLivingStatusText);
        profileIncomeText = findViewById(R.id.profileIncomeText);

        if(getIntent() != null && getIntent().getExtras() != null && getIntent().hasExtra(RESPONSE_KEY)) {
            response = (Response) getIntent().getSerializableExtra(RESPONSE_KEY);
            Log.d(MainActivity.TAG, "onCreate of Profile Activity: " + response);
            profileNameText.setText(response.getName());
            profileEmailText.setText(response.getEmail());
            profileRoleText.setText(response.getRole());
            profileEducationText.setText(response.getEducationLevel());
            profileMaritalStatusText.setText(response.getMaritalStatus());
            profileLivingStatusText.setText(response.getLivingStatus());
            profileIncomeText.setText(response.getIncomeRange());
        }
    }
}