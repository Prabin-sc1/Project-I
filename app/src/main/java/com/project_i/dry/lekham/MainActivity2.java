package com.project_i.dry.lekham;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class MainActivity2 extends AppCompatActivity {

//    private static int RC_SIGN_IN = 100;
//    GoogleSignInClient mGoogleSignInClient;

    private TextView login, signup, forgot;
    private Button lizardButton;
    private TextInputLayout confirmPwd;

    private boolean isSignup = true;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private FirebaseUser mUser;
    private TextInputEditText email;
    private TextInputEditText password;
    private TextInputEditText confirmPassword;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(FirebaseAuth firebaseAuth) {
                mUser = firebaseAuth.getCurrentUser();

                if(mUser != null){
//                    Toast.makeText(MainActivity2.this, "Signed In", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity2.this, "Not Signed In", Toast.LENGTH_SHORT).show();
                }
            }
        };
        login = findViewById(R.id.loginId);
        signup = findViewById(R.id.signupId);
        lizardButton = findViewById(R.id.lizardId);
        forgot = findViewById(R.id.forgotId);
        confirmPwd = findViewById(R.id.confirmId);

        email = findViewById(R.id.emailEditId);
        password = findViewById(R.id.passwordEditId);
        confirmPassword = findViewById(R.id.confirmEditId);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSignup = false;
                login.setBackground(getResources().getDrawable(R.drawable.text_selected));
                login.setTextColor(getResources().getColor(R.color.white));
                login.setElevation(4);
                signup.setElevation(0);

                signup.setBackground(getResources().getDrawable(R.drawable.text_unselected));
                signup.setTextColor(getResources().getColor(R.color.background));
                lizardButton.setText("Log in");
                forgot.setVisibility(View.VISIBLE);
                confirmPwd.setVisibility(View.GONE);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSignup = true;
                login.setBackground(getResources().getDrawable(R.drawable.text_unselected));
                login.setTextColor(getResources().getColor(R.color.background));
                login.setElevation(0);
                signup.setElevation(4);

                signup.setBackground(getResources().getDrawable(R.drawable.text_selected));
                signup.setTextColor(getResources().getColor(R.color.white));
                lizardButton.setText("Sign up");
                forgot.setVisibility(View.INVISIBLE);
                confirmPwd.setVisibility(View.VISIBLE);
            }
        });


        lizardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailString = email.getText().toString();
                String passwordString = password.getText().toString();
                String confirmPwdString = confirmPassword.getText().toString();
                if(isSignup){
//                    Toast.makeText(MainActivity2.this, "in signup ", Toast.LENGTH_SHORT).show();
                    if(!emailString.equals("") && !passwordString.equals("")  && !confirmPwdString.equals("")&& (confirmPwdString.equals(passwordString))){

                            signup(emailString, passwordString);

                                login.setBackground(getResources().getDrawable(R.drawable.text_selected));
                                login.setTextColor(getResources().getColor(R.color.white));
                                login.setElevation(4);
                                signup.setElevation(0);

                                signup.setBackground(getResources().getDrawable(R.drawable.text_unselected));
                                signup.setTextColor(getResources().getColor(R.color.background));
                                lizardButton.setText("Log in");
                                forgot.setVisibility(View.VISIBLE);
                                confirmPwd.setVisibility(View.GONE);

                                email.setText("");
                                password.setText("");
                                confirmPassword.setText("");



                    } else {
                        Toast.makeText(MainActivity2.this, "please fill correctly", Toast.LENGTH_SHORT).show();
                    }


                }else{
                    if(!emailString.equals("") && !passwordString.equals("")){
                        login(emailString, passwordString);
                        startActivity(new Intent(MainActivity2.this,MainActivity3.class));
                        finish();
                    }else{
                        Toast.makeText(MainActivity2.this, "Please fill correctly", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
//
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();
//
//        // Build a GoogleSignInClient with the options specified by gso.
//        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
//        // Check for existing Google Sign In account, if the user is already signed in
//// the GoogleSignInAccount will be non-null.
//        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
////        updateUI(account);
//
//        // Set the dimensions of the sign-in button.
//        SignInButton signInButton = findViewById(R.id.sign_in_button);
//        signInButton.setSize(SignInButton.SIZE_STANDARD);
//
//        signInButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                signIn();
//            }
//        });
//    }
//    private void signIn() {
//        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
//        startActivityForResult(signInIntent, RC_SIGN_IN);
//    }
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
//        if (requestCode == RC_SIGN_IN) {
//            // The Task returned from this call is always completed, no need to attach
//            // a listener.
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            handleSignInResult(task);
//        }
//    }
//
//    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
//        try {
//            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
//
//            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
//            if (acct != null) {
//                String personName = acct.getDisplayName();
//                String personGivenName = acct.getGivenName();
//                String personFamilyName = acct.getFamilyName();
//                String personEmail = acct.getEmail();
//                String personId = acct.getId();
//                Uri personPhoto = acct.getPhotoUrl();
//
//
//
//            }
//            startActivity(new Intent(MainActivity2.this, MainActivity3.class));
//
//
//            // Signed in successfully, show authenticated UI.
////            updateUI(account);
//        } catch (ApiException e) {
//            // The ApiException status code indicates the detailed failure reason.
//            // Please refer to the GoogleSignInStatusCodes class reference for more information.
//            Log.w("message",e.toString());
////            updateUI(null);
//        }
    }

    private void signup(String emailString, String passwordString) {
        mAuth.createUserWithEmailAndPassword(emailString, passwordString).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity2.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity2.this, "Failed to create account", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    private void login(String emailString, String passwordString) {
        mAuth.signInWithEmailAndPassword(emailString,passwordString).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete( Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity2.this, "we are sign in", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity2.this, "failed to sign in", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}