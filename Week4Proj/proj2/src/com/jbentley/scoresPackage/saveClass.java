package com.jbentley.scoresPackage;

import com.jbentley.proj2.MainActivity;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class saveClass {

	// Save to parse function
	public void saveToParse(String pScore, String pLname, String pFname,
			String pQDesc) {
//		 final MainActivity newMain =  new MainActivity();

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

					// System.out.println("error saving!!!!!");
					// newMain.passThisToast("Sorry, there was an error saving.");
					// Toast.makeText(saveContext,
					// "Sorry, the score did NOT save.",
					// Toast.LENGTH_LONG).show();
				} else {
					// System.out.println("success saving!!");
					// // Toast.makeText(saveContext, "yep",
					// Toast.LENGTH_LONG).show();
//					 newMain.passThisToast();
				}
			}

		});

	}

}
