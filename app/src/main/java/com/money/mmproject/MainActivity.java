package com.money.mmproject;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView p=new ImageView(this);
        p=(ImageView)findViewById(R.id.logo);

        int resource_id= getResources().getIdentifier("logo", "drawable", getPackageName());
        //InputStream inputStream = getContext().getAssets().open(animal.get(position).getFilename());
        //Drawable drawable2 = Drawable.createFromStream(inputStream, null);
        Drawable drawable = getResources().getDrawable(resource_id);
        p.setBackgroundColor(0);
        p.setImageDrawable(drawable);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public void onclickWeekly(View view){
        Intent myIntent = new Intent(MainActivity.this,NavigateFragment.class);
        myIntent.putExtra("section number", 1);
        MainActivity.this.startActivity(myIntent);
    }

    public void onclickAnnualy(View view){
        //Intent myIntent = new Intent(MainActivity.this,xxx_PUTYOURCLASSNAMEINHERE_xxx.class);
        //MainActivity.this.startActivity(myIntent);
    }

    public void onclickTransaction(View view){
        Intent myIntent = new Intent(MainActivity.this,addTransaction.class);
        MainActivity.this.startActivity(myIntent);
    }

    public void onClickHistory(View view) {
        Intent myIntent = new Intent(MainActivity.this, HistoryActivity.class);
        MainActivity.this.startActivity(myIntent);
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

            case R.id.visual_summary:
                Intent visualSummary = new Intent(getApplicationContext(),
                        NavigateFragment.class);
                startActivity(visualSummary);
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
