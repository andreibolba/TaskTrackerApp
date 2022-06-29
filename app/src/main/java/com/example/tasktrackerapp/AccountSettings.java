package com.example.tasktrackerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AccountSettings extends AppCompatActivity {
    public static User loggedUser;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String passPatternSpecial="\" !\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~\"";
    EditText email,password, repassword,firstName,lastName,phone;
    Button edit,gotoaccount,delete,logout;

    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);
        email=(EditText) findViewById(R.id.emailSettings);
        password=(EditText) findViewById(R.id.passwordSettings);
        repassword=(EditText) findViewById(R.id.repassSettings);
        firstName=(EditText) findViewById(R.id.fNameSettings);
        lastName=(EditText) findViewById(R.id.lNameSettings);
        phone=(EditText) findViewById(R.id.phoneSettings);
        edit=(Button) findViewById(R.id.edit);
        gotoaccount=(Button) findViewById(R.id.backtoaccount);
        logout=(Button)findViewById(R.id.logoutSettings);
        delete=(Button)findViewById(R.id.deleteSetting);

        DB= new DBHelper(this);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = email.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                String fName = firstName.getText().toString();
                String lName = lastName.getText().toString();
                String phoneNumber = phone.getText().toString();

                if (mail.equals("") && pass.equals("") && repass.equals("") && fName.equals("") && lName.equals("") && phoneNumber.equals(""))
                    Toast.makeText(AccountSettings.this, "Please enter at least one field", Toast.LENGTH_SHORT).show();
                else {
                    User editedUser=loggedUser;
                    if(!fName.equals(""))
                        editedUser.setfName(fName);
                    if(!lName.equals(""))
                        editedUser.setlName(lName);
                    if(!phoneNumber.equals(""))
                        editedUser.setPhone(phoneNumber);
                    if(!mail.equals(""))
                    {
                        if(Utils.isCorrectEmail(mail))
                        {
                            if(DB.checkEmail(mail)==false)
                                editedUser.setEmail(mail);
                            else{
                                Toast.makeText(AccountSettings.this, "Email exists", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        else{
                            Toast.makeText(AccountSettings.this, "Email in wrong format", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    if(!pass.equals("")){
                        if (pass.equals(repass)) {
                            switch (Utils.checkPassword(pass)) {
                                case 100:
                                    editedUser.setPass(pass);
                                    break;
                                case 101:
                                    Toast.makeText(AccountSettings.this, "Passwords is not 8 letters long", Toast.LENGTH_SHORT).show();
                                    return;
                                case 102:
                                    Toast.makeText(AccountSettings.this, "Passwords must contains digits", Toast.LENGTH_SHORT).show();
                                    return;
                                case 103:
                                    Toast.makeText(AccountSettings.this, "Passwords must contains big letters", Toast.LENGTH_SHORT).show();
                                    return;
                                case 104:
                                    Toast.makeText(AccountSettings.this, "Passwords must contains low letter letters", Toast.LENGTH_SHORT).show();
                                    return;
                                case 105:
                                    Toast.makeText(AccountSettings.this, "Passwords must contains special charaters", Toast.LENGTH_SHORT).show();
                                    return;
                                default:
                                    Toast.makeText(AccountSettings.this, "Error", Toast.LENGTH_SHORT).show();
                                    return;
                            }
                        } else {
                            Toast.makeText(AccountSettings.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    if(DB.updateUser(editedUser,loggedUser.getUsername()))
                    {
                        Toast.makeText(AccountSettings.this, "Account edited", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),PersonalProfileActivity.class);
                        PersonalProfileActivity.loggedUser=editedUser;
                        startActivity(intent);
                    }
                    else
                        Toast.makeText(AccountSettings.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        gotoaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),PersonalProfileActivity.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LogInActivity.class);
                startActivity(intent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(DB.deleteAccount(loggedUser)==true){
                    Intent intent = new Intent(getApplicationContext(),LogInActivity.class);
                    Toast.makeText(AccountSettings.this, "Account deleted!", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }else{
                    Toast.makeText(AccountSettings.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}