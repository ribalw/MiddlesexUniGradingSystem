package MdxGrading;

import java.util.*;

public class Degree { 
	// Your additions/changes below this line

	// Variables to store values if there is any null or fail in any year.
	private boolean isFail = false;
	private boolean isNull = false;

	// To store classification for degree class
	private Classification classification = Classification.Discretion;

	private int levelFiveClass = 0; 
	private int levelSixClass = 0; 

	public Degree(List<Grade> year2, List<Grade> year3) {

		/** To check if there is any null year 2 and year3 */
		if (year2 == null)  
			isNull = true; 
		if (year3 == null)
			isNull = true;
		if (isNull) {
			throw new IllegalArgumentException();
		}

		/** To check if both lists got less or more than 4 elements each */
		if ((year3.size() + year2.size()) != 8)
			throw new IllegalArgumentException();
		// Combining grades of year 2 and year 3 for profile 5.
		year2.addAll(year3);

		/** To check if there is any fails in profile5 */
		year2.forEach((grade) -> {
			if (grade.classify().equals(Classification.Fail)) {
				isFail = true;
			}
		});
		if (isFail)
			throw new IllegalArgumentException();

		Profile profileFive = new Profile(year2);
		Profile profileSix = new Profile(year3);

		// Getting classification of profile 5 and 6.
		levelFiveClass = profileFive.classify().ordinal();
		levelSixClass = profileSix.classify().ordinal();
		
		// To get classification for degree
		getClassification(profileFive, profileSix);

	}

	private void getClassification(Profile profileFive, Profile profileSix) {
		if(profileSix.isClear()&& profileFive.isClear())
			System.out.print("Final degree is/will be clear");
		else
			System.err.print("Final degree is/will be borderline");

		// if level 6 profile is better, clear and no more than level up(b)
		if (profileSix.isClear() && Math.abs((levelSixClass - levelFiveClass)) == 1 && levelSixClass > levelFiveClass) {
			classification = profileSix.classify();
			
		// if level 5 profile is better, clear and no more than level up(c)
		}
		if (profileFive.isClear() && Math.abs((levelFiveClass - levelSixClass)) == 1 && levelFiveClass > levelSixClass) {
			classification = profileFive.classify();
		}
		// if both classifications are same(a)
		if (profileFive.classify().equals(profileSix.classify())) {
			classification = profileFive.classify();
		}

	}

	public Classification classify() {
		if (classification.equals(Classification.First))
			return Classification.First;
		else if (classification.equals(Classification.UpperSecond))
			return Classification.UpperSecond;
		else if (classification.equals(Classification.LowerSecond))
			return Classification.LowerSecond;
		else if (classification.equals(Classification.Third))
			return Classification.Third;
		else
			return Classification.Discretion;
	}

}
