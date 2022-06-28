package com.example.tasktrackerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String passPatternSpecial="\" !\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~\"";
    EditText username,email,password, repassword;
    Button register,goToLogIn;

    DBHelper DB;

    private Boolean isCorrectEmail(String email){
        return email.matches(emailPattern)==true?true:false;
    }

    private int checkPassword(String pass){
        if(pass.toString().length()<8)
            return 101;
        Boolean isAllRight=false;
        for(int i=0;i<pass.length();i++) {
            if (pass.charAt(i) <= '9' && pass.charAt(i) >= '0')
                isAllRight = true;
        }
        if(isAllRight==false)
            return 102;
        isAllRight=false;
        for(int i=0;i<pass.length();i++) {
            if (pass.charAt(i) <= 'Z' && pass.charAt(i) >= 'A')
                isAllRight = true;
        }
        if(isAllRight==false)
            return 103;
        isAllRight=false;
        for(int i=0;i<pass.length();i++) {
            if (pass.charAt(i) <= 'z' && pass.charAt(i) >= 'a')
                isAllRight = true;
        }
        if(isAllRight==false)
            return 104;
        isAllRight=false;
        for(int i=0;i<pass.length();i++){
            for (int j=0;j<passPatternSpecial.length();j++)
                if(pass.charAt(i)==passPatternSpecial.charAt(j))
                    isAllRight=true;
        }
        if(isAllRight==false)
            return 105;
        return 100;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username=(EditText) findViewById(R.id.username);
        email=(EditText) findViewById(R.id.email);
        password=(EditText) findViewById(R.id.password);
        repassword=(EditText) findViewById(R.id.repass);
        register=(Button) findViewById(R.id.register);
        goToLogIn=(Button) findViewById(R.id.gotologin);

        DB= new DBHelper(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String mail=email.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(user.equals("")||mail.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(RegisterActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if(isCorrectEmail(mail)==true) {
                        if (pass.equals(repass)) {
                            switch (checkPassword(pass)) {
                                case 100:
                                    Boolean checkuser = DB.checkusername(user);
                                    Boolean checkemail = DB.checkEmail(mail);
                                    if (checkuser == false && checkemail == false) {
                                        Boolean insert = DB.insertData(user, mail, pass);
                                        if (insert == true) {
                                            Toast.makeText(RegisterActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        if (checkemail == true && checkuser == true) {
                                            Toast.makeText(RegisterActivity.this, "User and email already exists! please sign in", Toast.LENGTH_SHORT).show();
                                        } else if (checkemail == true) {
                                            Toast.makeText(RegisterActivity.this, "Email already exists! please sign in", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(RegisterActivity.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    break;
                                case 101:
                                    Toast.makeText(RegisterActivity.this, "Passwords is not 8 letters long", Toast.LENGTH_SHORT).show();
                                    break;
                                case 102:
                                    Toast.makeText(RegisterActivity.this, "Passwords must contains digits", Toast.LENGTH_SHORT).show();
                                    break;
                                case 103:
                                    Toast.makeText(RegisterActivity.this, "Passwords must contains big letters", Toast.LENGTH_SHORT).show();
                                    break;
                                case 104:
                                    Toast.makeText(RegisterActivity.this, "Passwords must contains low letter letters", Toast.LENGTH_SHORT).show();
                                    break;
                                case 105:
                                    Toast.makeText(RegisterActivity.this, "Passwords must contains special charaters", Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    Toast.makeText(RegisterActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        } else {
                            Toast.makeText(RegisterActivity.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(RegisterActivity.this, "Incorrent email", Toast.LENGTH_SHORT).show();
                    }
                } }
        });

        goToLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LogInActivity.class);
                startActivity(intent);
            }
        });

    }
}