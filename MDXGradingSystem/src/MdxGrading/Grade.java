package MdxGrading;

public class Grade {
	private final int points;

	public int getPoints() {
		return points;
	}

	public Grade(int p) throws IllegalArgumentException {
		if (p < 1 || p > 20)
			throw new IllegalArgumentException();
		points = p;
	} 

	// Your additions/changes below this line

	public Classification classify() {
		if (points <= 4)
			return Classification.First;
		else if (points <= 8)
			return Classification.UpperSecond;
		else if (points <= 12)
			return Classification.LowerSecond;
		else if (points <= 16)
			return Classification.Third;
		else
			return Classification.Fail;
 
	}

	public static Grade fromPercentage(int g) throws IllegalArgumentException {
 
		Grade grade = new Grade(20); // In a case if value of g is -1.
		if (g > 100 || g < -1)
			throw new IllegalArgumentException();
		if (g >= 79)
			return grade = new Grade(1);
		else if (g >= 76)
			return grade = new Grade(2);
		else if (g >= 73) 
			return grade = new Grade(3);
		else if (g >= 70)
			return grade = new Grade(4);
		else if (g >= 67)
			return grade = new Grade(5);
		else if (g >= 65)
			return grade = new Grade(6);
		else if (g >= 62)
			return grade = new Grade(7);
		else if (g >= 60)
			return grade = new Grade(8);
		else if (g >= 57)
			return grade = new Grade(9);
		else if (g >= 55)
			return grade = new Grade(10);
		else if (g >= 52)
			return grade = new Grade(11);
		else if (g >= 50)
			return grade = new Grade(12);
		else if (g >= 47)
			return grade = new Grade(13);
		else if (g >= 45)
			return grade = new Grade(14);
		else if (g >= 42)
			return grade = new Grade(15);
		else if (g >= 40)
			return grade = new Grade(16);
		else if (g >= 35)
			return grade = new Grade(17);
		else if (g >= 30)
			return grade = new Grade(18);
		else if (g >= 0)
			return grade = new Grade(19);
		else
			return grade;

	}

}
