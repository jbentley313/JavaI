package com.jbentley.proj2;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proj2.R;
import com.jbentley.connectivityPackage.connectivityClass;
import com.jbentley.scoresPackage.saveClass;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;

public class MainActivity extends Activity {
	Context mContext;
	String[] mListItems;
	Integer passedCourseString;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainbtnlayout);

		// initialize parse
		Parse.initialize(this, "ebgtabNEAUYh9B9P70yD3wZ9urXIoVaLptXL2cvg",
				"WBEb6Htd6rYSgwy2XHA8puvxEKnsnrzVsMUQTBz2");

		// parse analytics
		ParseAnalytics.trackAppOpened(getIntent());
		super.onCreate(savedInstanceState);
		mContext = this;
		connectivityClass neConn = new connectivityClass();

		Button SFbtn = (Button) findViewById(R.id.SFButton);
		Button IRAbtn = (Button) findViewById(R.id.IRAButton);
		Button ARCbtn = (Button) findViewById(R.id.ARCButton);

		SFbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setContentView(R.layout.form);
				passedCourseString = (R.string.sound_foundations);
System.out.println(passedCourseString);

				Button Backbtn = (Button) findViewById(R.id.BackButton);
				Backbtn.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						onCreate(savedInstanceState);
						passedCourseString = null;
					}
				});
			}
		});

		IRAbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setContentView(R.layout.form);
				passedCourseString = (R.string.intro_to_recording);

				Button Backbtn = (Button) findViewById(R.id.BackButton);
				Backbtn.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						setContentView(R.layout.mainbtnlayout);
						onCreate(savedInstanceState);
						passedCourseString = null;
					}
				});
			}
		});

		ARCbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setContentView(R.layout.form);
				passedCourseString = (R.string.advanced_recording);

				Button Backbtn = (Button) findViewById(R.id.BackButton);
				Backbtn.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						setContentView(R.layout.mainbtnlayout);
						onCreate(savedInstanceState);
						passedCourseString = null;
					}
				});
			}
		});

	}

	// if (neConn.connectionStatus(mContext)) {
	// saveClass saver = new saveClass();
	// System.out.println("yepppppppzzzzz");
	// }

	// toast function (alert for required fields)

	
	
//	if (passedCourseString == (R.string.sound_foundations)) {
//		System.out.println("you chose sf");
//	}
	
	public void makeSomeToast(Context mContext) {
		Toast.makeText(mContext, "Please enter all fields.", Toast.LENGTH_LONG)
				.show();
	}

}
