package TestScoresP;


//enum for courses
public enum QuizScoresE {
	Advanced_Recording("Jason", "ARCSSL", 95, "Bentley"),
	Intro_to_Recording("Joel", "IRAQuiz1", 90, "Numa"),
	Sound_Foundations("Kevin", "SFQuiz2", 100, "Reaves"),
	Music_Production("Brian", "MPDuality", 100, "Warwick");

	private final String firstname;
	private final String testDescription;
	private final int testscore;
	private final String lastname;
	
	//method to set names of fields
	private QuizScoresE(String firstname, String testDescription, int testscore, String lastname){
		
		this.firstname = firstname;
		this.testDescription = testDescription;
		this.testscore = testscore;
		this.lastname = lastname;
		
	}
	
	public String setFirstname(){
		return firstname;	
	}
	
	public String setTestDescription(){
		return testDescription;
	}
	
	public int setTestscore(){
		return testscore;
	}
	
	public String setLastname(){
		return lastname;
	}
	
}
