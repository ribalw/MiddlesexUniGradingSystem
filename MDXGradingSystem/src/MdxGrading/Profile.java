package MdxGrading;

import java.util.*;

public class Profile {

	// Your additions/changes below this line 

	// Variables to make decide which class to award for this profile.
	private boolean first = false;
	private boolean upperSecond = false;
	private boolean lowerSecond = false; 

	// Variables to count percentage for class of each grade.
	private double percentageFirst = 0.0;
	private double percentageUpperSecond = 0.0;
	private double percentageLowerSecond = 0.0;
	private double percentageThird = 0.0;

	public Profile(List<Grade> g) {
		// Variable to find if there is any fail in list of grades.
		boolean isFail = false;
		if (g == null || g.isEmpty())
			throw new IllegalArgumentException();

		/** To check if there is any fails in Grades */
		isFail = g.stream().anyMatch((grade) ->
		grade.classify() == (Classification.Fail));// to stream 

		if (isFail)
			throw new IllegalArgumentException();

		// To get number off occurrence and frequency for each class.
		getFrequency(g);
	}

	/** To get a how frequency for each classification in each profile */
	private void getFrequency(List<Grade> g) {

		// To count number of occurrence of classification of each grade.
		percentageFirst = g.stream().filter(c -> c.classify() == Classification.First).count();
		percentageUpperSecond = g.stream().filter(c -> c.classify() == Classification.UpperSecond).count();
		percentageLowerSecond = g.stream().filter(c -> c.classify() == Classification.LowerSecond).count();
		percentageThird = g.stream().filter(c -> c.classify() == Classification.Third).count();

		// To get total percentage of of each class in profile.
		percentageFirst = ((percentageFirst / g.size()) * 100);
		percentageUpperSecond = ((percentageUpperSecond / g.size()) * 100);
		percentageLowerSecond = ((percentageLowerSecond / g.size()) * 100);
		percentageThird = ((percentageThird / g.size()) * 100);
		getClassification();
	}

	private void getClassification() {
		// To decide class for profile
		if (percentageFirst >= 50.0)
			first = true;
		else if ((percentageUpperSecond + percentageFirst) >= 50.0)
			upperSecond = true;
		else if ((percentageLowerSecond + percentageUpperSecond + percentageFirst) >= 50.0)
			lowerSecond = true;
	}

	public Classification classify() {
		if (first)
			return Classification.First;
		else if (upperSecond)
			return Classification.UpperSecond;
		else if (lowerSecond)
			return Classification.LowerSecond;
		else
			return Classification.Third;

	}

	public boolean isClear() {

		if ((first || upperSecond) && (percentageThird > 25.0))
			return false;// Returning Borderline
		else
			return true;// Returning Clear

	}

}
