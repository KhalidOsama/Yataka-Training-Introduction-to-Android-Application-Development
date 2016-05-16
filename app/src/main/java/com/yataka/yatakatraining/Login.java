package com.yataka.yatakatraining;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    private EditText etUsername;
    EditText etPassword;
    Button bLogin;
    TextView tvCalculation;
//    final String correctUsername = "Misbah";
//    final String correctPassword = "123";

    User user1 = new User("Misbah", "123");
    User user2 = new User("Abdullah", "1234");
    User user3 = new User("Shaikh", "12345");

    ArrayList<User> users = new ArrayList<>();

ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

progressDialog = new ProgressDialog(Login.this);

//        UI thread sleeping
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        //        System.out.println("step 1");
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(2000);
                    System.out.println("step 1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };



//        System.out.println("step 2");
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(1000);
                    System.out.println("step 2");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };


        thread1.start();
        thread2.start();



        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        tvCalculation = (TextView) findViewById(R.id.tvCalculation) ;

//        user1.username = "Misbah";
//        user1.setUsername("Misbah");
//        user1.setPassword("123");
//        user1.password = "123";

//        populate array list
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(new User("Alex", "Alex"));

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                user clicked login

                calculations();

                //for multiple users. New way
                for (int i = 0; i < users.size(); i++) {
                    if (validateUsername(etUsername.getText().toString(), i)) {
                        if (etPassword.getText().toString().equals(users.get(i).getPassword())) {
                            System.out.println("User logged in successfully.");
                            Log.d("tag", "User logged in successfully.");
                            Toast.makeText(Login.this, "Welcome "+etUsername.getText().toString().trim(), Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Login.this , DrawerActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Toast.makeText(Login.this, "Username correct, but password is wrong!", Toast.LENGTH_LONG).show();
                        }

                    }
                    else {

//                        System.out.println("User login failed.");
//                        Toast.makeText(Login.this, "User login failed. Wrong username.", Toast.LENGTH_LONG).show();
                    }
                }

                // For one user only, old way.
//                if (condition) {if true} else {if false}
//                System.out.println("User clicked.");
//                if (validateUsername(etUsername.getText().toString())) {
//                    if (etPassword.getText().toString().equals(user1.getPassword())) {
//                        System.out.println("User logged in successfully.");
//                        Log.d("tag", "User logged in successfully.");
//                        Toast.makeText(Login.this, "Welcome "+etUsername.getText().toString().trim(), Toast.LENGTH_LONG).show();
//                        Intent iChatting = new Intent(Login.this , Chatting.class);
//                        startActivity(iChatting);
//                    }
//                    else {
//                        Toast.makeText(Login.this, "Username correct, but password is wrong!", Toast.LENGTH_LONG).show();
//                    }
//
//                }
//                else {
//
//                    System.out.println("User login failed.");
//                    Toast.makeText(Login.this, "User login failed. Wrong username.", Toast.LENGTH_LONG).show();
//                }

            }
        });

    }

    private void calculations() {

        progressDialog.setMessage("Processing...");
        progressDialog.show();

        final Handler hVeiw = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                try {
                    progressDialog.dismiss();
                    tvCalculation.setText(msg.obj + "");
                    Toast.makeText(Login.this, "Calculation done.",Toast.LENGTH_SHORT).show();
                } catch (Exception e) {

                }
            }
        };

        Thread thC = new Thread() {
            @Override
            public void run() {
                super.run();
                long l = 0;
                for (int i = 0; i < 500000; i++) {
                    l = 2432*431+341+1234*545;
                    System.out.println(l);
                }
                Message msg = new Message();
                msg.obj = l;
                hVeiw.sendMessage(msg);
            }
        };
thC.start();

    }

    private boolean validateUsername(String stringArgument, int iIn) {
        stringArgument = stringArgument.trim();

        if (stringArgument.length() < 4) {
            Toast.makeText(Login.this, "Username is too short. Must be at least 4 chars long.", Toast.LENGTH_LONG).show();
        }
        else if (stringArgument.equals(users.get(iIn).getUsername())) {
            return true;
        }
        return false;
    }

}
