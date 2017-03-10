package com.example.saloni.placementv2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener{

    private Button buttonRegister;

    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextName;
    private EditText editTextRollNumber;

    private ProgressDialog progressDialog;

    private RadioGroup radioGroupProgram;
    private RadioButton radioButtonProgram;

    private RadioGroup radioGroupStream;
    private RadioButton radioButtonStream;
    //Firebase Authentication Objest
    private FirebaseAuth firebaseAuth;
    private DatabaseReference uDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        buttonRegister =(Button) findViewById(R.id.buttonRegister);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextRollNumber = (EditText) findViewById(R.id.editTextRollNumber);

        buttonRegister.setOnClickListener(this);

        radioGroupProgram = (RadioGroup)findViewById(R.id.radioGroupProgram);
        radioGroupStream = (RadioGroup)findViewById(R.id.radioGroupStream);

        progressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();


    }

    private void registerUser()
    {

        final String email= editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String name= editTextName.getText().toString().trim();
        final String rollNumber = editTextRollNumber.getText().toString().trim();
        final String role = "student";


        int selectedProgram = radioGroupProgram.getCheckedRadioButtonId();
        int selectedStream = radioGroupStream.getCheckedRadioButtonId();

        radioButtonProgram = (RadioButton)findViewById(selectedProgram);
        radioButtonStream = (RadioButton)findViewById(selectedStream);

        final String program = radioButtonProgram.getText().toString();
        final String stream = radioButtonStream.getText().toString();


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

        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())
                {

                    Toast.makeText(RegisterUser.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();

                    //Adding user to user database
                    uDatabase = FirebaseDatabase.getInstance().getReference("Users");


                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    String currUID = user.getUid();

                    User newUser = new User(currUID, email, name, rollNumber, program, stream,role);

                    String UID = uDatabase.push().getKey();
                    uDatabase.child(UID).setValue(newUser);

                    Intent myIntent = new Intent(RegisterUser.this, GetJobProfiles.class);
                    RegisterUser.this.startActivity(myIntent);


                }

                else
                {
                    Toast.makeText(RegisterUser.this, "Error Occurred!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {

        if(view == buttonRegister)

        {


            registerUser();

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
