package com.yataka.yatakatraining;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by khalid.osama on 5/15/2016.
 */
public class Utility {

    public void onOptionsItemSelected(MenuItem item, final Activity activity, String s) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_share) {
            Toast.makeText(activity.getBaseContext(), "Share", Toast.LENGTH_SHORT).show();
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, s);
            sendIntent.setType("text/plain");
            activity.startActivity(sendIntent);
        }

        else if (id == R.id.action_about) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
            alertDialog.setTitle("About us");
            alertDialog.setIcon(R.mipmap.ic_launcher);
            alertDialog.setMessage("This app is copyrighted by me.\nKindly rate our app.");
            alertDialog.setPositiveButton("Rate us", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(activity, "Thank you.", Toast.LENGTH_SHORT).show();
                }
            });
            alertDialog.setNegativeButton("Later", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(activity, "Later it is.", Toast.LENGTH_SHORT).show();
                }
            });
            alertDialog.setCancelable(false);
            alertDialog.show();
        }


    }
}
