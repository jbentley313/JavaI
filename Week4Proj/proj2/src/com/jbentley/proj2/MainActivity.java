package com.jbentley.proj2;

import java.util.List;

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
import com.parse.ParseQuery;

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
		final connectivityClass neConn = new connectivityClass();

		Button SFbtn = (Button) findViewById(R.id.SFButton);
		Button IRAbtn = (Button) findViewById(R.id.IRAButton);
		Button ARCbtn = (Button) findViewById(R.id.ARCButton);

		// Sound Foundations button
		SFbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setContentView(R.layout.form);
				passedCourseString = (R.string.sound_foundations);
				System.out.println(passedCourseString);

				// SF Back button
				Button Backbtn = (Button) findViewById(R.id.BackButton);
				Backbtn.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						onCreate(savedInstanceState);
						passedCourseString = null;
					}
				});

				// SF Save button
				Button Savebtn = (Button) findViewById(R.id.SaveButton);
				Savebtn.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub

						// check network status then save
						if (neConn.connectionStatus(mContext)) {

							EditText scoreEdText = (EditText) findViewById(R.id.Score);
							EditText lastNameText = (EditText) findViewById(R.id.lastNameField);
							EditText firstNameText = (EditText) findViewById(R.id.firstNameField);

							final String passedLastName = lastNameText
									.getText().toString();
							final String passedFirstName = firstNameText
									.getText().toString();
							final String passedScore = scoreEdText.getText()
									.toString();
							final String passedQuizDesc = "SFQuiz";
							if ((firstNameText.getText().length() > 0
									&& lastNameText.getText().length() > 0 && scoreEdText
									.getText().length() > 0)) {

								// save to parse function call
								saveClass saveToParse = new saveClass();
								saveToParse.saveToParse(passedScore,
										passedLastName, passedFirstName,
										passedQuizDesc);
								// reset fields
								lastNameText.setText("");
								firstNameText.setText("");
								scoreEdText.setText("");
							} else {

								// alert fields missing
								makeSomeToast(mContext);
							}

						}

					}
				});

				// SF Display button

				// Display Scores button
				Button scoreButton = (Button) findViewById(R.id.DisplayButton);

				scoreButton.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View arcV) {
						if (neConn.connectionStatus(mContext)) {
							// TODO Auto-generated method stub
							ParseQuery<ParseObject> query = ParseQuery
									.getQuery("SFQuiz");
							query.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
							query.findInBackground(new FindCallback<ParseObject>() {
								public void done(
										List<ParseObject> quizScoreList,
										ParseException e) {
									if (e == null) {
										Log.d("score", "Retrieved "
												+ quizScoreList.size()
												+ " scores");

										for (ParseObject scoreInfo : quizScoreList) {
											final String firstname = scoreInfo
													.getString("firstname");
											String lastname = scoreInfo
													.getString("lastname");
											String score = Integer
													.toString(scoreInfo
															.getInt("score"));
											TextView finalTextView = (TextView) findViewById(R.id.scoresTextView);
											finalTextView.setText(lastname
													+ ", " + firstname
													+ "     " + score + "\n"
													+ finalTextView.getText());

										}

									} else {
										Log.d("score",
												"Error: " + e.getMessage());
									}
								}
							});
						} else {
							Toast.makeText(
									mContext,
									"No Network Connection.\nNetwork Connection Required.",
									Toast.LENGTH_LONG).show();
						}
					}
				});
			}
		});

		// IRA Button
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
				// IRA Save button
				Button Savebtn = (Button) findViewById(R.id.SaveButton);
				Savebtn.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub

						// check network status then save
						if (neConn.connectionStatus(mContext)) {

							EditText scoreEdText = (EditText) findViewById(R.id.Score);
							EditText lastNameText = (EditText) findViewById(R.id.lastNameField);
							EditText firstNameText = (EditText) findViewById(R.id.firstNameField);

							final String passedLastName = lastNameText
									.getText().toString();
							final String passedFirstName = firstNameText
									.getText().toString();
							final String passedScore = scoreEdText.getText()
									.toString();
							final String passedQuizDesc = "IRAQuiz";
							if ((firstNameText.getText().length() > 0
									&& lastNameText.getText().length() > 0 && scoreEdText
									.getText().length() > 0)) {

								// save to parse function call
								saveClass saveToParse = new saveClass();
								saveToParse.saveToParse(passedScore,
										passedLastName, passedFirstName,
										passedQuizDesc);
								// reset fields
								lastNameText.setText("");
								firstNameText.setText("");
								scoreEdText.setText("");
							} else {

								// alert fields missing
								makeSomeToast(mContext);
							}

						}

					}
				});
				
				//IRA Display Button
				

				// Display Scores button
				Button scoreButton = (Button) findViewById(R.id.DisplayButton);

				scoreButton.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View arcV) {
						if (neConn.connectionStatus(mContext)) {
							// TODO Auto-generated method stub
							ParseQuery<ParseObject> query = ParseQuery
									.getQuery("IRAQuiz");
							query.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
							query.findInBackground(new FindCallback<ParseObject>() {
								public void done(
										List<ParseObject> quizScoreList,
										ParseException e) {
									if (e == null) {
										Log.d("score", "Retrieved "
												+ quizScoreList.size()
												+ " scores");

										for (ParseObject scoreInfo : quizScoreList) {
											final String firstname = scoreInfo
													.getString("firstname");
											String lastname = scoreInfo
													.getString("lastname");
											String score = Integer
													.toString(scoreInfo
															.getInt("score"));
											TextView finalTextView = (TextView) findViewById(R.id.scoresTextView);
											finalTextView.setText(lastname
													+ ", " + firstname
													+ "     " + score + "\n"
													+ finalTextView.getText());

										}

									} else {
										Log.d("score",
												"Error: " + e.getMessage());
									}
								}
							});
						} else {
							Toast.makeText(
									mContext,
									"No Network Connection.\nNetwork Connection Required.",
									Toast.LENGTH_LONG).show();
						}
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
				// ARC Save button
				Button Savebtn = (Button) findViewById(R.id.SaveButton);
				Savebtn.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub

						// check network status then save
						if (neConn.connectionStatus(mContext)) {

							EditText scoreEdText = (EditText) findViewById(R.id.Score);
							EditText lastNameText = (EditText) findViewById(R.id.lastNameField);
							EditText firstNameText = (EditText) findViewById(R.id.firstNameField);

							final String passedLastName = lastNameText
									.getText().toString();
							final String passedFirstName = firstNameText
									.getText().toString();
							final String passedScore = scoreEdText.getText()
									.toString();
							final String passedQuizDesc = "ARCQuiz";
							if ((firstNameText.getText().length() > 0
									&& lastNameText.getText().length() > 0 && scoreEdText
									.getText().length() > 0)) {

								// save to parse function call
								saveClass saveToParse = new saveClass();
								saveToParse.saveToParse(passedScore,
										passedLastName, passedFirstName,
										passedQuizDesc);
								// reset fields
								lastNameText.setText("");
								firstNameText.setText("");
								scoreEdText.setText("");
							} else {

								// alert fields missing
								makeSomeToast(mContext);
							}

						}

					}

				});
				// ARC display button
				
				// SF Display button

				// Display Scores button
				Button scoreButton = (Button) findViewById(R.id.DisplayButton);

				scoreButton.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View arcV) {
						if (neConn.connectionStatus(mContext)) {
							// TODO Auto-generated method stub
							ParseQuery<ParseObject> query = ParseQuery
									.getQuery("ARCQuiz");
							query.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
							query.findInBackground(new FindCallback<ParseObject>() {
								public void done(
										List<ParseObject> quizScoreList,
										ParseException e) {
									if (e == null) {
										Log.d("score", "Retrieved "
												+ quizScoreList.size()
												+ " scores");

										for (ParseObject scoreInfo : quizScoreList) {
											final String firstname = scoreInfo
													.getString("firstname");
											String lastname = scoreInfo
													.getString("lastname");
											String score = Integer
													.toString(scoreInfo
															.getInt("score"));
											TextView finalTextView = (TextView) findViewById(R.id.scoresTextView);
											finalTextView.setText(lastname
													+ ", " + firstname
													+ "     " + score + "\n"
													+ finalTextView.getText());

										}

									} else {
										Log.d("score",
												"Error: " + e.getMessage());
									}
								}
							});
						} else {
							Toast.makeText(
									mContext,
									"No Network Connection.\nNetwork Connection Required.",
									Toast.LENGTH_LONG).show();
						}
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

	// if (passedCourseString == (R.string.sound_foundations)) {
	// System.out.println("you chose sf");
	// }

	public void makeSomeToast(Context mContext) {
		Toast.makeText(mContext, "Please enter all fields.", Toast.LENGTH_LONG)
		.show();
	}

	public void passThisToast(String string) {
		Toast.makeText(mContext, string, Toast.LENGTH_LONG).show();
	}

}
