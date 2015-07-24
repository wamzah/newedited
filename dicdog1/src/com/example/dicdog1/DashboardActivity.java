package com.example.dicdog1;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class DashboardActivity extends ActionBarActivity {
private static Button button1;
private static Intent i;
private static Button buttontime;
private static Button buttonDoctors;
private static int buttonCount;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar bar=getSupportActionBar();
		bar.hide();
		setContentView(R.layout.activity_dashboard);
		//overridePendingTransition(R.layout.rotate_out, R.layout.rotatein);
		//search by doctor button click
		//Toast.makeText(getApplicationContext(), Landingpage.hospital_list.get(0), Toast.LENGTH_LONG).show();
		
		
		buttonCount=0;
		button1=(Button)findViewById(R.id.buttonHosp);
		button1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				i=new Intent(DashboardActivity.this,DoctorsActivity.class);
				 
				startActivity(i);
		      
			}
		});
		buttonDoctors=(Button)findViewById(R.id.buttonDoctor);
		buttonDoctors.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				i=new Intent(DashboardActivity.this,Search_doctors.class);
				startActivity(i);
				finish();
		      
			}
		});
		buttontime=(Button)findViewById(R.id.buttontime);
		buttontime.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				i=new Intent(DashboardActivity.this,SearchingByTime.class);
				 
				startActivity(i);
		      
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.dashboard, menu);
		getMenuInflater().inflate(R.menu.dashboard, menu);
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.help) {
			onHelp();
			return true;
		}
		if (id == R.id.About) {
			new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("About Us")
            .setMessage("DocDig is an app that lets you find doctors in Islamabad "
            		+ "\n\n\n Sponsored By Code for Pakistan")
            .setPositiveButton("Ok", null).setInverseBackgroundForced(true).show();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	public void onHelp() {
	       new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Help")
	               .setMessage("DocDig is an app that lets you find doctors in Islamabad "
	               		+ "through following options..\n\n"
	               		+ "1. Search By Doctor --  In this option you would enter the Hospital name and "
	               		+ "the name of the doctor and the gender and the details of that doctor would be shown\n\n"
	               		+ "2. Search By Time -- You would select the hospital name, speciality, gender and the required date and time "
	               		+ "and all the doctors of that category will be shown\n\n"
	               		+ "3. Search By Hospital -- In this option, you would enter the hospital name, speciality and gender and the "
	               		+ "list of doctors would be shown")
	               .setPositiveButton("Okay, Got it", null).show();
	   }
	
	public Animation inFromRightAnimation()
	{
	    Animation inFromRight = new TranslateAnimation(
	            Animation.RELATIVE_TO_PARENT, +1.0f,
	            Animation.RELATIVE_TO_PARENT, 0.0f,
	            Animation.RELATIVE_TO_PARENT, 0.0f,
	            Animation.RELATIVE_TO_PARENT, 0.0f);
	    inFromRight.setDuration(240);
	    inFromRight.setInterpolator(new AccelerateInterpolator());
	    return inFromRight;
	}
	public Animation outToRightAnimation()
	{
	    Animation outtoLeft = new TranslateAnimation(
	            Animation.RELATIVE_TO_PARENT, -1.0f,
	            Animation.RELATIVE_TO_PARENT, 0.0f,
	            Animation.RELATIVE_TO_PARENT, 0.0f,
	            Animation.RELATIVE_TO_PARENT, 0.0f);
	    outtoLeft.setDuration(240);
	    outtoLeft.setInterpolator(new AccelerateInterpolator());
	    return outtoLeft;
	}
	
	public void onBackPressed()
	{
	    if(buttonCount >= 1)
	    {
	        Intent intent = new Intent(Intent.ACTION_MAIN);
	        intent.addCategory(Intent.CATEGORY_HOME);
	        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	        startActivity(intent);
	        //finish();
	    }
	    else
	    {
	        Toast.makeText(this, "Press the back button once again to close the application.", Toast.LENGTH_SHORT).show();
	        buttonCount++;
	    }
	}
}