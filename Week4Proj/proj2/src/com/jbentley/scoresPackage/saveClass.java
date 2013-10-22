package com.jbentley.scoresPackage;
import android.content.Context;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class saveClass {
	Context mContext;
	
	
	
	
	
	// Save to parse function
		public void saveToParse(String pScore, String pLname, String pFname,
				String pQDesc) {

			
				int pScoreInt = Integer.parseInt(pScore);
				ParseObject quizScore = new ParseObject(pQDesc);
				quizScore.put("score", pScoreInt);
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
							Toast.makeText(mContext, "Score saved!",
									Toast.LENGTH_LONG).show();

						}
					}

				});

			
		}

}
