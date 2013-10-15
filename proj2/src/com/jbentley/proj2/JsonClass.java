package com.jbentley.proj2;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import TestScoresP.QuizScoresE;

public class JsonClass {
	ArrayList<String> _quizTags;

	public static JSONObject buildJson() {

		// create quiz json object
		JSONObject quizObject = new JSONObject();

		try {
			// create query json object
			JSONObject queryObject = new JSONObject();

			// create quiz object in query
			for (QuizScoresE quizScore : QuizScoresE.values()) {

				// create single quiz score
				JSONObject scoresObject = new JSONObject();

				// add scores to scores object
				scoresObject.put("firstname", quizScore.setFirstname());
				scoresObject.put("testDescription",quizScore.setTestDescription());
				scoresObject.put("testScore", quizScore.setTestscore());
				scoresObject.put("lastname", quizScore.setLastname());
				queryObject.put(quizScore.name().toString(), scoresObject);

			}

			quizObject.put("query", queryObject);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return quizObject;
	}

	public static String readJSON(String selected) {

		String result, firstname, testDescription, testScore, lastname;
		JSONObject qObject = buildJson();
		
		try {
			firstname = qObject.getJSONObject("query").getJSONObject(selected).getString("firstname");
			testDescription = qObject.getJSONObject("query").getJSONObject(selected).getString("testDescription");
			testScore = qObject.getJSONObject("query").getJSONObject(selected).getString("testScore");
			lastname = qObject.getJSONObject("query").getJSONObject(selected).getString("lastname");
			
			result = lastname + ", " + firstname + "  Quiz: " + testDescription + "  Score: "+  testScore;
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = e.toString();
		}
		
		
		return result;
	}

}
