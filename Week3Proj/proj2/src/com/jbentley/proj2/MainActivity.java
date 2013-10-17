package com.jbentley.proj2;

import java.util.List;

import com.example.proj2.R;
import com.example.proj2.R.string;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

public class MainActivity extends Activity {
	Context mContext;
	String[] mListItems;
	static String TAG = "NETWORK DATA - MAINACTIVITY";

	@Override
	protected void onCreate(final Bundle savedInstanceState) {

		// initialize parse
		Parse.initialize(this, "ebgtabNEAUYh9B9P70yD3wZ9urXIoVaLptXL2cvg",
				"WBEb6Htd6rYSgwy2XHA8puvxEKnsnrzVsMUQTBz2");

		// parse analytics
		ParseAnalytics.trackAppOpened(getIntent());
		super.onCreate(savedInstanceState);
		mContext = this;
		mListItems = getResources().getStringArray(R.array.quiz_view);

		// linear layout
		final LinearLayout myLinLayout = new LinearLayout(this);
		myLinLayout.setOrientation(LinearLayout.VERTICAL);
		LayoutParams myLayoutParams = new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

		final LinearLayout buttonLayout = new LinearLayout(this);
		buttonLayout.setOrientation(LinearLayout.VERTICAL);
		LayoutParams buttonParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		buttonLayout.setLayoutParams(buttonParams);

		// layout params
		myLinLayout.setLayoutParams(myLayoutParams);

		// lastname text view
		final TextView lastNameT = new TextView(this);
		myLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		lastNameT.setLayoutParams(myLayoutParams);
		lastNameT.setText(R.string.lastname);
		lastNameT.setTextSize(15);

		// firstname text view
		final TextView firstName = new TextView(this);
		firstName.setLayoutParams(myLayoutParams);
		firstName.setText(R.string.firstName);
		firstName.setTextSize(15);

		// title text view
		final TextView titleTextView = new TextView(this);
		myLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		titleTextView.setLayoutParams(myLayoutParams);
		titleTextView.setText(R.string.quiz_scores);
		titleTextView.setTextSize(33);

		final TextView scoreTextView = new TextView(this);
		scoreTextView.setLayoutParams(myLayoutParams);
		scoreTextView.setText(R.string.Score);

		final TextView finalTextView = new TextView(this);
		myLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		finalTextView.setLayoutParams(myLayoutParams);

		finalTextView.setTextSize(20);

		// myLinLayout.addView(finalTextView);

		// button creation
		final Button myButton = new Button(this);
		myButton.setText(R.string.IRA);
		myButton.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		myLinLayout.addView(myButton);
		myButton.setOnClickListener(new View.OnClickListener() {

			@Override
			// onClick handler
			public void onClick(View v) {

				System.out.println("intro");
				titleTextView.setText(myButton.getText().toString());
				myLinLayout.removeAllViews();

				// last name editText and Textview
				final EditText lastNameText = new EditText(mContext);
				myLinLayout.addView(lastNameT);
				myLinLayout.addView(lastNameText);

				// first name editText and Textview
				final EditText firstNameText = new EditText(mContext);
				myLinLayout.addView(firstName);
				myLinLayout.addView(firstNameText);

				// score editText and Textview
				final EditText scoreEdText = new EditText(mContext);
				scoreEdText.setInputType(InputType.TYPE_CLASS_NUMBER);

				myLinLayout.addView(scoreTextView);
				myLinLayout.addView(scoreEdText);

				// back button
				final Button backButon = new Button(mContext);
				backButon.setText(R.string.Back);
				backButon.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						myLinLayout.removeAllViews();
						onCreate(savedInstanceState);

					}
				});

				// Display Scores button
				final Button scoreButton = new Button(mContext);
				scoreButton.setText(R.string.dispScore);
				scoreButton.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						ParseQuery<ParseObject> query = ParseQuery
								.getQuery("IRAQuiz");
						query.findInBackground(new FindCallback<ParseObject>() {
							public void done(List<ParseObject> quizScoreList,
									ParseException e) {
								if (e == null) {
									Log.d("score",
											"Retrieved " + quizScoreList.size()
													+ " scores");

									myLinLayout.removeView(finalTextView);
									myLinLayout.addView(finalTextView);
									for (ParseObject scoreInfo : quizScoreList) {
										final String firstname = scoreInfo
												.getString("firstname");
										String lastname = scoreInfo
												.getString("lastname");
										String score = scoreInfo
												.getString("score");
										// String testDesc =
										// scoreInfo.getString("quizDescription");

										finalTextView.setText(lastname + ", "
												+ firstname + "     " + score
												+ "\n"
												+ finalTextView.getText());
										System.out.println(firstname);

									}

								} else {
									Log.d("score", "Error: " + e.getMessage());
								}
							}
						});
					}
				});

				// Save button
				final Button saveButton = new Button(mContext);
				saveButton.setText(R.string.Save);
				saveButton.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View vf) {
						final String passedLastName = lastNameText.getText()
								.toString();
						final String passedFirstName = firstNameText.getText()
								.toString();
						final String passedScore = scoreEdText.getText()
								.toString();
						final String passedQuizDesc = "IRAQuiz";

						// save to parse function call
						saveToParse(passedScore, passedLastName,
								passedFirstName, passedQuizDesc);

						lastNameText.setText("");
						firstNameText.setText("");
						scoreEdText.setText("");
					}

				});

				// add Views (buttons)
				myLinLayout.addView(backButon);
				myLinLayout.addView(scoreButton);
				myLinLayout.addView(saveButton);

			}

		});

		final Button myButton2 = new Button(this);
		myButton2.setText(R.string.SF);
		myButton2.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		myLinLayout.addView(myButton2);
		myButton2.setOnClickListener(new View.OnClickListener() {

			@Override
			// onClick handler
			public void onClick(View vSf) {

				System.out.println("sf");
				titleTextView.setText(myButton.getText().toString());
				myLinLayout.removeAllViews();

				// last name editText and Textview
				final EditText lastNameText = new EditText(mContext);
				myLinLayout.addView(lastNameT);
				myLinLayout.addView(lastNameText);

				// first name editText and Textview
				final EditText firstNameText = new EditText(mContext);
				myLinLayout.addView(firstName);
				myLinLayout.addView(firstNameText);

				// score editText and Textview
				final EditText scoreEdText = new EditText(mContext);
				scoreEdText.setInputType(InputType.TYPE_CLASS_NUMBER);

				myLinLayout.addView(scoreTextView);
				myLinLayout.addView(scoreEdText);

				// back button
				final Button backButon = new Button(mContext);
				backButon.setText(R.string.Back);
				backButon.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						myLinLayout.removeAllViews();
						onCreate(savedInstanceState);

					}
				});

				// Display Scores button
				final Button scoreButton = new Button(mContext);
				scoreButton.setText(R.string.dispScore);
				scoreButton.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						ParseQuery<ParseObject> query = ParseQuery
								.getQuery("SFQuiz");
						query.findInBackground(new FindCallback<ParseObject>() {
							public void done(List<ParseObject> quizScoreList,
									ParseException e) {
								if (e == null) {
									Log.d("score",
											"Retrieved " + quizScoreList.size()
													+ " scores");

									myLinLayout.removeView(finalTextView);
									myLinLayout.addView(finalTextView);
									for (ParseObject scoreInfo : quizScoreList) {
										final String firstname = scoreInfo
												.getString("firstname");
										String lastname = scoreInfo
												.getString("lastname");
										String score = scoreInfo
												.getString("score");
										// String testDesc =
										// scoreInfo.getString("quizDescription");

										finalTextView.setText(lastname + ", "
												+ firstname + "     " + score
												+ "\n"
												+ finalTextView.getText());
										System.out.println(firstname);

									}

								} else {
									Log.d("score", "Error: " + e.getMessage());
								}
							}
						});
					}
				});

				// Save button
				final Button saveButton = new Button(mContext);
				saveButton.setText(R.string.Save);
				saveButton.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View vf) {
						final String passedLastName = lastNameText.getText()
								.toString();
						final String passedFirstName = firstNameText.getText()
								.toString();
						final String passedScore = scoreEdText.getText()
								.toString();
						final String passedQuizDesc = "SFQuiz";

						// save to parse function call
						saveToParse(passedScore, passedLastName,
								passedFirstName, passedQuizDesc);

						lastNameText.setText("");
						firstNameText.setText("");
						scoreEdText.setText("");
					}

				});

				// add Views (buttons)
				myLinLayout.addView(backButon);
				myLinLayout.addView(scoreButton);
				myLinLayout.addView(saveButton);

			}

		});

		final Button myButton3 = new Button(this);
		myButton3.setText(R.string.ARC);
		myButton3.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		myLinLayout.addView(myButton3);
		myButton3.setOnClickListener(new View.OnClickListener() {

			@Override
			// onClick handler
			public void onClick(View v) {

				System.out.println("ARC");
				titleTextView.setText(myButton.getText().toString());
				myLinLayout.removeAllViews();

				// last name editText and Textview
				final EditText lastNameText = new EditText(mContext);
				myLinLayout.addView(lastNameT);
				myLinLayout.addView(lastNameText);

				// first name editText and Textview
				final EditText firstNameText = new EditText(mContext);
				myLinLayout.addView(firstName);
				myLinLayout.addView(firstNameText);

				// score editText and Textview
				final EditText scoreEdText = new EditText(mContext);
				scoreEdText.setInputType(InputType.TYPE_CLASS_NUMBER);

				myLinLayout.addView(scoreTextView);
				myLinLayout.addView(scoreEdText);

				// back button
				final Button backButon = new Button(mContext);
				backButon.setText(R.string.Back);
				backButon.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						myLinLayout.removeAllViews();
						onCreate(savedInstanceState);

					}
				});

				// Display Scores button
				final Button scoreButton = new Button(mContext);
				scoreButton.setText(R.string.dispScore);
				scoreButton.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View arcV) {
						// TODO Auto-generated method stub
						ParseQuery<ParseObject> query = ParseQuery
								.getQuery("ARCQuiz");
						query.findInBackground(new FindCallback<ParseObject>() {
							public void done(List<ParseObject> quizScoreList,
									ParseException e) {
								if (e == null) {
									Log.d("score",
											"Retrieved " + quizScoreList.size()
													+ " scores");

									myLinLayout.removeView(finalTextView);
									myLinLayout.addView(finalTextView);
									for (ParseObject scoreInfo : quizScoreList) {
										final String firstname = scoreInfo
												.getString("firstname");
										String lastname = scoreInfo
												.getString("lastname");
										String score = scoreInfo
												.getString("score");
										// String testDesc =
										// scoreInfo.getString("quizDescription");

										finalTextView.setText(lastname + ", "
												+ firstname + "     " + score
												+ "\n"
												+ finalTextView.getText());
										System.out.println(firstname);

									}

								} else {
									Log.d("score", "Error: " + e.getMessage());
								}
							}
						});
					}
				});

				// Save button
				final Button saveButton = new Button(mContext);
				saveButton.setText(R.string.Save);
				saveButton.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View vf) {
						final String passedLastName = lastNameText.getText()
								.toString();
						final String passedFirstName = firstNameText.getText()
								.toString();
						final String passedScore = scoreEdText.getText()
								.toString();
						final String passedQuizDesc = "ARCQuiz";

						// save to parse function call
						saveToParse(passedScore, passedLastName,
								passedFirstName, passedQuizDesc);

						lastNameText.setText("");
						firstNameText.setText("");
						scoreEdText.setText("");
					}

				});

				// add Views (buttons)
				myLinLayout.addView(backButon);
				myLinLayout.addView(scoreButton);
				myLinLayout.addView(saveButton);

			}

		});

		// Add Layout Views

		setContentView(myLinLayout);
		addContentView(buttonLayout, buttonParams);

	}

	// Save to parse function
	public void saveToParse(String pScore, String pLname, String pFname,
			String pQDesc) {
		if (connectionStatus(mContext)) {
			ParseObject quizScore = new ParseObject(pQDesc);
			quizScore.put("score", pScore);
			quizScore.put("lastname", pLname);
			quizScore.put("firstname", pFname);
			quizScore.put("quizDescription", pQDesc);
			quizScore.saveInBackground(new SaveCallback() {
				//
				public void done(ParseException e) {
					if (e != null) {

						System.out.println("error saving!!!!!");
						Toast.makeText(mContext,
								"Sorry, the score did NOT save.",
								Toast.LENGTH_LONG).show();
					} else {
						System.out.println("success saving!!");
						Toast.makeText(mContext, "Success Saving Score!",
								Toast.LENGTH_LONG).show();

					}
				}

			});

		} else {
			Toast.makeText(mContext,
					"No Network connection! \nNetwork connection is required to save a score.",
					Toast.LENGTH_LONG).show();
		}
	}

	public Boolean connectionStatus(Context mContext) {

		Boolean conn = false;
		ConnectivityManager conMan = (ConnectivityManager) mContext
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = conMan.getActiveNetworkInfo();
		if (netInfo != null) {
			if (netInfo.isConnected()) {
				Log.i(TAG, "connection type: " + netInfo.getTypeName());
				conn = true;
			}
		}

		return conn;

	}

}