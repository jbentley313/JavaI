package com.example.bentley_jasonproject1;


import java.security.PrivateKey;
import java.security.PublicKey;

import android.R.integer;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		
		
		LinearLayout myLinLayout = new LinearLayout(this);
		myLinLayout.setOrientation(LinearLayout.VERTICAL);
		LayoutParams myLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		
		myLinLayout.setLayoutParams(myLayoutParams);
		
		
		TextView myTextView = new TextView(this);
		myLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		myTextView.setLayoutParams(myLayoutParams);
		myTextView.setText(R.string.welcome_value);
		
		myLinLayout.addView(myTextView);
		
		setContentView(myLinLayout);
		
		
		final int miles = 130;
		final Boolean isDark = false;
		String myString = "Start Driving!";
		
		final EditText nameText = new EditText(this);
		
		
		myLinLayout.addView(nameText);
		
		Button myButton = new Button(this);
		myButton.setText(myString);
		myButton.setLayoutParams(new LayoutParams (LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		myLinLayout.addView(myButton);
		myButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String enteredName;
				
				if (isDark == true) {
					System.out.println("You can't drive in the dark!");
				} else {
					enteredName = (nameText.getText().toString());
					System.out.println(enteredName);
					milesToGo(miles, enteredName);
				}
				
				
			}
		
			
			void milesToGo(int passMiles, String nameThisString)
			{
				for (int i = 0; i < passMiles; i++) {
					System.out.println(nameThisString + " has "  + (passMiles - i) + " miles to go!");
				}
			}
		});
		
		
	
		
		
		
		
		
		
	}

	
	

}
