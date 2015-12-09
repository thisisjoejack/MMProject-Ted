package com.money.mmproject;

import static com.money.mmproject.Constants.FIRST_COLUMN;
import static com.money.mmproject.Constants.SECOND_COLUMN;
import static com.money.mmproject.Constants.THIRD_COLUMN;
import static com.money.mmproject.Constants.FOURTH_COLUMN;
import static com.money.mmproject.Constants.FIFTH_COLUMN;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class HistoryActivity extends AppCompatActivity {
    private ArrayList<HashMap<String, String>> list;
    private TransactionsDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ListView listView=(ListView)findViewById(R.id.listView1);

        list=new ArrayList<HashMap<String,String>>();


        db = new TransactionsDB(getApplication());

        Cursor CR = db.getInformation(db);
        Cursor checkNull = db.getInformation(db);

        if (checkNull == null || checkNull.getCount() <= 0) {
            Toast.makeText(getApplicationContext(), "There is no transaction history yet.",
                    Toast.LENGTH_LONG).show();
        }
        if (CR.moveToFirst()) {
            int i =0;
            do {
                HashMap<String,String> temp=new HashMap<String, String>();


                //id
                temp.put(FIRST_COLUMN, Integer.toString(i) );
                //date
                temp.put(SECOND_COLUMN, CR.getString(0));
                //category
                temp.put(THIRD_COLUMN, "$" + CR.getString(1) );
                //paid amount
                temp.put(FOURTH_COLUMN, CR.getString(2));
                //description
                temp.put(FIFTH_COLUMN,  CR.getString(3));
                list.add(temp);
                i++;
            } while (CR.moveToNext());
        }



 /*       temp.put(FIRST_COLUMN, "1");
        temp.put(SECOND_COLUMN, "date2");
        temp.put(THIRD_COLUMN, "Category");
        temp.put(FOURTH_COLUMN, "0.00");
        temp.put(FIFTH_COLUMN, "SomeInfo1");
        list.add(temp);

        HashMap<String,String> temp2=new HashMap<String, String>();
        temp2.put(FIRST_COLUMN, "2");
        temp2.put(SECOND_COLUMN, "date");
        temp2.put(THIRD_COLUMN, "Category");
        temp2.put(FOURTH_COLUMN, "0.00");
        temp2.put(FIFTH_COLUMN, "SomeInfo2");
        list.add(temp2);

        HashMap<String,String> temp3=new HashMap<String, String>();
        temp3.put(FIRST_COLUMN, "3");
        temp3.put(SECOND_COLUMN, "date1");
        temp3.put(THIRD_COLUMN, "Category");
        temp3.put(FOURTH_COLUMN, "0.00");
        temp3.put(FIFTH_COLUMN, "SomeInfo3");
        list.add(temp3);
        */

        ListViewAdapter adapter=new ListViewAdapter(this, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id)
            {
                int pos=position+1;
                Toast.makeText(HistoryActivity.this, Integer.toString(pos) + " Clicked", Toast.LENGTH_SHORT).show();
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
