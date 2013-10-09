package com.example.proj2;

import android.R.string;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

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
		
		myLinLayout.setLayoutParams(myLayoutParams);
		
		//title text view
		TextView titleTextView = new TextView(this);
		myLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		titleTextView.setLayoutParams(myLayoutParams);
		titleTextView.setText(R.string.quiz_scores);
		titleTextView.setTextSize(65);
		
		
		myLinLayout.addView(titleTextView);
		
		//sec text view
		TextView listText = new TextView(this);
		myLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		listText.setLayoutParams(myLayoutParams);
		listText.setText(R.string.quiz_scores);
		listText.setTextSize(35);
		
		
		myLinLayout.addView(listText);
		
		
		
		// Spinner Adapter
		ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, mListItems);
		spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		// Create Spinner
		Spinner quizSpinner = new Spinner(mContext);
		quizSpinner.setAdapter(spinnerAdapter);
		
		
		myLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		quizSpinner.setLayoutParams(myLayoutParams);
		
		//Add Layout View
		myLinLayout.addView(quizSpinner); 
		
		setContentView(myLinLayout);
		
	}


}