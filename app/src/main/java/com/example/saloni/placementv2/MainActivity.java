package com.example.saloni.placementv2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonLogIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private ProgressDialog progressDialog;
    private TextView textViewSignUp;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        firebaseAuth = FirebaseAuth.getInstance();

        /*if(firebaseAuth.getCurrentUser() != null)
        {
            Log.d("currentuser",firebaseAuth.getCurrentUser().toString());
            Intent myIntent = new Intent(MainActivity.this, HomePage.class);
            MainActivity.this.startActivity(myIntent);
        }*/

        buttonLogIn = (Button)findViewById(R.id.buttonLogIn);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textViewSignUp = (TextView) findViewById(R.id.textViewSignUp);

        progressDialog = new ProgressDialog(this);

        buttonLogIn.setOnClickListener(this);
        textViewSignUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if(view == buttonLogIn)
        {

            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();

            if(TextUtils.isEmpty(email))
            {
                Toast.makeText(this, "Email Cannot be empty",Toast.LENGTH_SHORT).show();

                return;

            }


            if(TextUtils.isEmpty(password))
            {

                Toast.makeText(this, "Password Cannot be empty", Toast.LENGTH_SHORT).show();

                return;

            }

            //If everything is valid

            progressDialog.setMessage("Logging In...");
            progressDialog.show();

            firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful())
                    {
                        Intent myIntent = new Intent(MainActivity.this, GetJobProfiles.class);
                        MainActivity.this.startActivity(myIntent);
                    }
                }
            });
        }

        if(view == textViewSignUp)
        {

            textViewSignUp.setTextColor(Color.parseColor("#3FADA8"));

            Intent myIntent = new Intent(MainActivity.this, RegisterUser.class);
            MainActivity.this.startActivity(myIntent);
        }
    }

}
