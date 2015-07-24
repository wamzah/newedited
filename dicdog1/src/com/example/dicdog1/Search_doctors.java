package com.example.dicdog1;

import java.util.ArrayList;
import java.util.List;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class Search_doctors extends ActionBarActivity {
	
private static Button button1;
private static List<Doctor> doctorList;
private static String searchJob;
private static String searchGender;
private static String searchName;
private static Spinner spinnerDoctor;
//private static Spinner spinnerHospital;
private static Spinner genderspinner;
private static Button button2;
private static Intent intent;
private static String check;
private static String searchHospital;
private static AutoCompleteTextView textView;
List<String> jobspec;
List<String> names;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getSupportActionBar();
				actionBar.hide();
		setContentView(R.layout.activity_search_doctors);
		//overridePendingTransition(R.layout.rotate_out, R.layout.rotatein);
		
		check="";
		//adding first job spinner
		jobspec=new ArrayList<String>();
		names=new ArrayList<String>();
		
		/*DatabaseHandler db=new DatabaseHandler(this);
        db.getReadableDatabase();
        jobspec=db.getJobList();        
        names=db.getNamesList();
        db.close();
        if(jobspec.contains("All"))
        {
        	jobspec.remove("All");
        }*/
		//Spinner spinner = (Spinner) findViewById(R.id.spinner1);
		jobspec=Landingpage.hospital_list;
		
		
		//search operation: calling all the spinners 
		spinnerDoctor=(Spinner)findViewById(R.id.spinner_hospital);
		//spinnerHospital=(Spinner)findViewById(R.id.Spinner01);
		//spinnerGender=(Spinner)findViewById(R.id.Spinner02);
		//initializing doctorlist,intent
		doctorList=new ArrayList<Doctor>();
		intent=new Intent(Search_doctors.this,DoctorsList.class);
		//search button
		button2=(Button)findViewById(R.id.buttonsearchdoc);
		textView = (AutoCompleteTextView) findViewById(R.id.doctorNameautoComplete);
		button2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				button_performed();
			}
		});

  		
		//array adapter for adding string
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.spinner_item_text, jobspec);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    //calling nothingclass for setting the default value on spinner
	    spinnerDoctor.setAdapter( new NothingSelectedSpinnerAdapter(adapter, R.layout.contact_spinner3_row_nothing_selected,this));
	
	  //adding second gender spinner
	  		genderspinner = (Spinner) findViewById(R.id.Spinner02);
	  		String genderspec[]={"male","female"};
	  		//array adapter for adding string
	  		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,R.layout.spinner2_item_text,genderspec);
	  	    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	  	    //calling nothingclass for setting the default value on spinner
	  	    genderspinner.setAdapter( new NothingSelectedSpinnerAdapter(adapter2, R.layout.contact_spinner2_row_nothing_selected,this));
       
	  	    //while(genderspinner.getS)
	  	    //adding parse
	  	  spinnerDoctor.setOnItemSelectedListener(new OnItemSelectedListener(){

				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					//Toast.makeText(getApplicationContext(), ""+genderspinner.getSelectedItem(), Toast.LENGTH_LONG).show();
					
					String value11 = (String) spinnerDoctor.getSelectedItem();
					String value22 = (String) genderspinner.getSelectedItem();
					ParseQuery<ParseObject> query = ParseQuery.getQuery("DoctorsTable");
					/*if(value22.equals("male" ) || value22.equals("female" ))
					{
						query.whereEqualTo("Hospital", value11);
						query.whereEqualTo("Gender", value22);
						query.setLimit(1000);
					}
					else
					{*/
						query.whereEqualTo("Hospital", value11);
						query.setLimit(1000);
					//}
					query.findInBackground(new FindCallback<ParseObject>() {
						
						@Override
						public void done(List<ParseObject> la,
								com.parse.ParseException e) {
							// TODO Auto-generated method stub
							 if(la!=null){
								 
								 for(int i=0;i<la.size();i++)
									{
									 String s=la.get(i).getString("Name");
									 /*if(i==0)
									 {
										 names.removeAll(names);
									 }*/
									 if(i==la.size()-1)
									 {
										 check="end";
										 //la=null;
										 method_done(check);
										 break;
										 }
									 else if(!(names.contains(s)))
									 {
										 method_done(s); 
									 }
									 };}
			                  else{//handle the error}
			                	  Toast.makeText(getApplicationContext(), "There is no doctor of this category ", Toast.LENGTH_LONG).show();
			                            }
							
						}
					});
					
				}

				@Override
				public void onNothingSelected(AdapterView<?> parent) {
					// TODO Auto-generated method stub
					
				}
				
			});
	  	      
	 /* 	//adding hospital spinner
	  		Spinner hospitalspinner = (Spinner) findViewById(R.id.Spinner01);
	  		List<String> hospspec=new ArrayList<String>();
	  		DatabaseHandler db1=new DatabaseHandler(this);
	        db1.getReadableDatabase();
	        hospspec=db1.getHospitalList();
	        db1.close();
	  		//array adapter for adding string
	  		ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,R.layout.spinner3_item_text,hospspec);
	  	    adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	  	    //calling nothingclass for setting the default value on spinner
	  	    hospitalspinner.setAdapter( new NothingSelectedSpinnerAdapter(adapter3, R.layout.contact_spinner3_row_nothing_selected,this));
	  	*/    
	  	  
	  	    //setting home button. it directs to dashboard
	  	    button1=(Button)findViewById(R.id.button2);
			button1.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i=new Intent(Search_doctors.this,DashboardActivity.class);
					
					startActivity(i);
					finish();
				}
			});
			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.doctors, menu);
		return true;
	}
	public void method_done(String s)
	{
		if(s.equals("end"))
		{
		  	// Get the string array
		  	//String[] countries = getResources().getStringArray(R.array.countries_array);
		  	  		  	  		  	  		  	  		  	  
	  		  // 	Create the adapter and set it to the AutoCompleteTextView 
  		  ArrayAdapter<String> nameadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
  		  textView.setAdapter(nameadapter);
		  	  	  	    	  	    	  	    	  	
		}
		else
		{
			//Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
			names.add(s);
			
		}					
   }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	
	public void onBackPressed() {
                    Intent intent = new Intent(this, DashboardActivity.class);
                    startActivity(intent);
                    finish();
        
	} 
/*public void onBackPressed()
{
    if(buttonCount >= 1)
    {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    else
    {
        Toast.makeText(this, "Press the back button once again to close the application.", Toast.LENGTH_SHORT).show();
        buttonCount++;
    }
}
}*/
	
	//button for extracting data from database and showing on listview
	public void button_performed()
	{

		if(genderspinner.getSelectedItem()==null || spinnerDoctor.getSelectedItem()==null )
		{
			Toast.makeText(getApplicationContext(), "Select All categories", Toast.LENGTH_LONG).show();
		}
		else
		{
			searchGender=(String)genderspinner.getSelectedItem();
			searchHospital = (String) spinnerDoctor.getSelectedItem();
			searchName = textView.getText().toString();			
	      	
			ParseQuery<ParseObject> query = ParseQuery.getQuery("DoctorsTable");
			query.whereEqualTo("Gender", searchGender);
			query.whereEqualTo("Hospital", searchHospital);
			query.whereEqualTo("Name", searchName);
			query.findInBackground(new FindCallback<ParseObject>() {
				@Override
				public void done(List<ParseObject> la,
						com.parse.ParseException e) {
					// TODO Auto-generated method stub
					 if(la!=null){
						 //startActivity(intent);
						 if(la.size()==0)
						 {
							 Toast.makeText(getApplicationContext(), "No doctor of these characteristics found in this hospital ", Toast.LENGTH_LONG).show();
						 }
						 else
						 {
						
							 Intent intent2=new Intent(Search_doctors.this,SelectedDoctor.class);
							 intent2.putExtra("doctor", searchName);
						     startActivity(intent2);	
							 /*searchJob=la.get(0).getString("Job");
							 searchGender=la.get(0).getString("Gender");
							intent.putExtra("speciality", searchJob);
					      	intent.putExtra("gender", searchGender);
					      	intent.putExtra("hospital", searchHospital);
					      	intent.putExtra("name", searchName);
					      	startActivity(intent);*/
						 }
						
	                         }
	                  else{//handle the error}
	                	  Toast.makeText(getApplicationContext(), "There is no doctor of this category ", Toast.LENGTH_LONG).show();
	                       }
					
				}
			});
			
		}
	}
		
     }

