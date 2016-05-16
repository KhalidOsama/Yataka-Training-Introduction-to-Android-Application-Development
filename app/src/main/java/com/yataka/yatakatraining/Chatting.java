package com.yataka.yatakatraining;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class Chatting extends AppCompatActivity {

    EditText edMessage;
    ScrollView svMessages;
    ProgressBar pbSend;
    Button bSend;
    TextView tvMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatting_layout);

        bSend = (Button) findViewById(R.id.bSend);
        tvMessage = (TextView) findViewById(R.id.tvMessage);
        edMessage = (EditText) findViewById(R.id.editText);
        svMessages = (ScrollView) findViewById(R.id.svMessages);
        pbSend = (ProgressBar) findViewById(R.id.pbSend);






        bSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                user clicked
                pbSend.setVisibility(View.VISIBLE);
                bSend.setVisibility(View.INVISIBLE);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tvMessage.append("\n"+edMessage.getText().toString());
                        edMessage.setText("");
//                        svMessages.scrollTo(0, svMessages.getBottom());

                        svMessages.post(new Runnable() {
                            @Override
                            public void run() {
                                svMessages.fullScroll(View.FOCUS_DOWN);
                            }
                        });
                        pbSend.setVisibility(View.INVISIBLE);
                        bSend.setVisibility(View.VISIBLE);
                    }
                }, 2000);


            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        new Utility().onOptionsItemSelected(item, Chatting.this, tvMessage.getText().toString());
        return super.onOptionsItemSelected(item);
    }

}
