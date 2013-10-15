package com.jbentley.proj2;

import com.example.proj2.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	Context mContext;
	String[] mListItems;
	
	
	
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		mListItems = getResources().getStringArray(R.array.quiz_view);
		
		//linear layout
		LinearLayout myLinLayout = new LinearLayout(this);
		myLinLayout.setOrientation(LinearLayout.VERTICAL);
		LayoutParams myLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		
		//layout params
		myLinLayout.setLayoutParams(myLayoutParams);
		
		//title text view
		final TextView titleTextView = new TextView(this);
		myLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		titleTextView.setLayoutParams(myLayoutParams);
		titleTextView.setText(R.string.quiz_scores);
		titleTextView.setTextSize(55);
		
		
		
		
		
		//sec text view
		final TextView listText = new TextView(this);
		myLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		listText.setLayoutParams(myLayoutParams);
		listText.setText(R.string.quiz_scores);
		listText.setTextSize(15);
		
		
		
		
		
		
		// Spinner Adapter
		ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, mListItems);
		spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		// Create Spinner
		final Spinner quizSpinner = new Spinner(mContext);
		
		//set adapter for quizSpinner
		quizSpinner.setAdapter(spinnerAdapter);
		
		
		myLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		quizSpinner.setLayoutParams(myLayoutParams);
		quizSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				// set text to blank if item not chosen
				if (mListItems[position].equalsIgnoreCase("Please Choose a Course") ) {
					Toast.makeText(mContext, mListItems[position] , Toast.LENGTH_LONG).show();
					listText.setText("");
					//read json with selected text
				} else {
				
				Toast.makeText(mContext, "Quiz scores for " + mListItems[position] , Toast.LENGTH_LONG).show();
				String selected = mListItems[position];
				System.out.println(selected);
				listText.setText(JsonClass.readJSON(selected));
				}
				
				
				
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		//Add Layout Views
		setContentView(myLinLayout);
		myLinLayout.addView(titleTextView);
		myLinLayout.addView(quizSpinner); 
		myLinLayout.addView(listText);
	}


}