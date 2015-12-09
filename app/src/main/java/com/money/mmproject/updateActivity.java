package com.money.mmproject;

import android.content.Intent;
//import android.database.Cursor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//import com.parse.Parse;
import com.parse.ParseUser;


//import com.parse.ParseObject;


public class updateActivity extends AppCompatActivity {
    private TransactionsDB db;
    private UserProfileDBHandler userDB;
    private Button RegisterUserButton;
    private Button updateTransactionsButton;
    private Button logoutButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        RegisterUserButton = (Button) findViewById(R.id.RegisterUserButton);
        updateTransactionsButton = (Button) findViewById(R.id.UpdateTransactionsButton);
        logoutButton = (Button) findViewById(R.id.LogOutButton);
    /*    ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();
        */

     /*   db = new TransactionsDB(getApplication());
        userDB = new UserProfileDBHandler(getApplication());
        Cursor CR = db.getInformation(db);
        Cursor userCR = userDB.getInformation(userDB);
        if (CR.moveToFirst()) {
            do {
                System.out.println("ddMMyyyyhhmmss: " + CR.getString(0) + " Amount: " + CR.getString(1) +
                        " Category: " + CR.getString(2) + " Description: " + CR.getString(3));
            } while (CR.moveToNext());
        }

        if(userCR.moveToFirst()) {
            do {
                System.out.println("user name " + userCR.getString(0) + " name: " + userCR.getString(1) +
                        " income: " + userCR.getString(2) + " saving: " + userCR.getString(3) +
                        " spending: " + userCR.getString(4));
            } while (CR.moveToNext());
        }
        */

        ParseUser currentUser = ParseUser.getCurrentUser();
        if(currentUser == null || currentUser.getUsername() == null) {
            System.out.println("NO USER NAME DETECTED.");
        }
        else {
            String struser = currentUser.getUsername().toString();

            System.out.println("STR USER: " + struser);
        }
        updateTransactionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        RegisterUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser currentUser = ParseUser.getCurrentUser();
                if(currentUser == null || currentUser.getUsername() == null) {
                    Intent updateOnline = new Intent(getApplicationContext(),
                            LoginSignupActivity.class);
                    startActivity(updateOnline);

                }
                else {
                    Toast.makeText(getApplicationContext(),
                            "This user is already logged in",
                            Toast.LENGTH_SHORT).show();
                }


            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ParseUser currentUser = ParseUser.getCurrentUser();
                if(currentUser == null || currentUser.getUsername() == null) {
                    Toast.makeText(getApplicationContext(),
                            "The user is not logged in",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    ParseUser.logOut();
                    Toast.makeText(getApplicationContext(),
                            "Logged out successfully",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.action_user_profile:
                Intent userProfileIntent = new Intent(getApplicationContext(),
                        UserProfileActivity.class);
                startActivity(userProfileIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent a = new Intent(this,MainActivity.class);
            a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(a);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
