package com.tradan.firebaseauth;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.*;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {
    private FirebaseAuth fb;
    private EditText email;
    private EditText password;
    private Button login;
    private Button signup;
    private Toast toast;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        email=findViewById(R.id.signupemail);
        password=findViewById(R.id.signuppassword);
        login=findViewById(R.id.gologin);
        signup=findViewById(R.id.signup);
        fb=FirebaseAuth.getInstance();
        toast=new Toast(this);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email.getText().equals("")||email.getText()==null)
                {
                    Toast.makeText(SignupActivity.this,"Missing username or password!",Toast.LENGTH_LONG);
                }
                else if(password.getText().equals("")||password.getText()==null)
                {
                    Toast.makeText(SignupActivity.this,"Missing username or password!",Toast.LENGTH_LONG);
                }
                else {
                    String e = email.getText().toString();
                    String p = password.getText().toString();
                    fb.createUserWithEmailAndPassword(e, p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignupActivity.this, "Successfully Registered", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(SignupActivity.this, MainActivity.class);
                                startActivity(i);
                                finish();
                            } else {
                                Toast.makeText(SignupActivity.this, "Registration Failed", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
