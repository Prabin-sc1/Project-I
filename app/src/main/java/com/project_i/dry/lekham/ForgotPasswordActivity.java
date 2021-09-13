package com.project_i.dry.lekham;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {
    private EditText email;
    private Button reset;
    private ProgressBar progressBar;
    private TextView returnTextView;



    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        email = findViewById(R.id.editTextTextEmailAddress);
        reset = findViewById(R.id.resetBtnId);
        returnTextView = findViewById(R.id.returnId);

        returnTextView.setText(Html.fromHtml("<u>Return to login</u>"));

        returnTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgotPasswordActivity.this, MainActivity2.class));
                finish();
            }
        });


        progressBar = findViewById(R.id.progressBarId);

        auth = FirebaseAuth.getInstance();
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });


    }
    private void resetPassword(){
        String emailString = email.getText().toString().trim();

        if (emailString.isEmpty()){
            email.setError("Email is required!");
            email.requestFocus();
            return;
//            Toast.makeText(this, "Email is required!", Toast.LENGTH_SHORT).show();
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(emailString).matches()){
            email.setError("Please provide email!");
            email.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(emailString).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ForgotPasswordActivity.this, "Check your email to reset password", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ForgotPasswordActivity.this, "Try again! Something went wrong!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}