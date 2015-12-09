package com.money.mmproject;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class addTransaction extends AppCompatActivity {

    ArrayList<String> items = new ArrayList<String>();
    Spinner categories;
    Button save;
    EditText amount;
    EditText description;
    TransactionsDB sql;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_transaction);

        sql = new TransactionsDB(getApplicationContext());

        items.add("Food");
        items.add("Accessories");
        items.add("Others");
        categories = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        categories.setAdapter(adapter);

        amount = (EditText) findViewById(R.id.amountField);
        description = (EditText) findViewById(R.id.description);


        save = (Button) findViewById(R.id.submit);
        save.setOnClickListener(new Button.OnClickListener() {


            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                System.out.println("Current time => " + c.getTime());

                SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");

                ContentValues values = new ContentValues();
                values.put("transdate",df.format(c.getTime()));
                values.put("category", categories.getSelectedItem().toString());
                values.put("description", description.getText().toString());
                values.put("amount", amount.getText().toString());
                sql.insert(values);
                amount.setText("");
                description.setText("");
                Toast.makeText(getBaseContext(), "Transaction Saved", Toast.LENGTH_SHORT).show();
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

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //int id = item.getItemId();
        switch(item.getItemId()) {
            case R.id.action_user_profile:
                Intent userProfileIntent = new Intent(getApplicationContext(),
                        UserProfileActivity.class);
                startActivity(userProfileIntent);
                return true;
            case R.id.update_online:
                Intent updateOnline = new Intent(getApplicationContext(),
                        updateActivity.class);
                startActivity(updateOnline);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
