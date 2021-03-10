package MdxGrading;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/** Tests for All classes */
@DisplayName("Tests for all Classes")
class AllTests {

	/** Tests for Grade class */
	@Test 
	@DisplayName("Test 1: Test Below Valid Range")
	void testBelowRange() {
		assertThrows(IllegalArgumentException.class, () -> new Grade(0));
	}

	@Test
	@DisplayName("Test 2: Test Above Valid Range")
	void testAboveRange() {
		assertThrows(IllegalArgumentException.class, () -> new Grade(21));
	}

	@Test
	@DisplayName("Test 3: Test Valid Range")
	void testValidRange() {

		Grade grade = new Grade(10);
		assertEquals(10, grade.getPoints());
	}

	/** Parameterized test for grades classification */
	public static Stream<Arguments> classifyGrades() {
		return Stream.of(Arguments.of(1, Classification.First), Arguments.of(9, Classification.LowerSecond),
				Arguments.of(5, Classification.UpperSecond), Arguments.of(13, Classification.Third),
				Arguments.of(17, Classification.Fail)

		);
	}

	@DisplayName("Test 4-8: Test Grade Classify")
	@ParameterizedTest
	@MethodSource("classifyGrades")
	void testClassify(int x, Classification c) {
		Grade grade = new Grade(x);
		assertEquals(c, grade.classify());

	}

	@Test
	@DisplayName("Test 9: Test Below Valid Range of fromPercentage method")
	void testBelowRangeForPercentage() {

		assertThrows(IllegalArgumentException.class, () -> Grade.fromPercentage(-2));

	}

	@Test
	@DisplayName("Test 10: Test Above Valid Range of fromPercentage method")
	void testAboveRangeForPercentage() {

		assertThrows(IllegalArgumentException.class, () -> Grade.fromPercentage(101));

	}
	/** Parameterized test for fromPercentage  */
	public static Stream<Arguments> percentageValues() {

		return Stream.of(Arguments.of(82, 1), Arguments.of(78, 2), Arguments.of(75, 3), Arguments.of(72, 4),
				Arguments.of(69, 5), Arguments.of(66, 6), Arguments.of(64, 7), Arguments.of(61, 8), Arguments.of(59, 9),
				Arguments.of(56, 10), Arguments.of(54, 11), Arguments.of(51, 12), Arguments.of(49, 13),
				Arguments.of(46, 14), Arguments.of(44, 15), Arguments.of(41, 16), Arguments.of(39, 17),
				Arguments.of(34, 18), Arguments.of(29, 19), Arguments.of(-1, 20)

		);
	}

	@DisplayName("Test 10-30: Test fromPercentage() method")
	@ParameterizedTest
	@MethodSource("percentageValues")
	void testGetPercentage(int x, int p) {
		Grade grade;
		grade = Grade.fromPercentage(x);
		assertEquals(p, grade.getPoints());

	}

	/** Tests for Profile class */

	@Test
	@DisplayName("Test 31: Null list check for Profile constuctor")
	void testNullListInProfile() {
		List<Grade> gradeList = null;

		assertThrows(IllegalArgumentException.class, () -> new Profile(gradeList));

	}

	@ Test
	@DisplayName("Test 32: Empty list check for Profile constuctor")
	void testEmptyListInProfile() {
		List<Grade> gradeList = new ArrayList<Grade>();

		assertThrows(IllegalArgumentException.class, () -> new Profile(gradeList));

	}

	@Test
	@DisplayName("Test 33: Fail grade passed to Profile constuctor")
	void testFailGradeProfile() {
		List<Grade> gradeList = new ArrayList<Grade>();

		gradeList.add(Grade.fromPercentage(78));
		gradeList.add(Grade.fromPercentage(78));
		gradeList.add(Grade.fromPercentage(70));
		gradeList.add(Grade.fromPercentage(20));
		assertThrows(IllegalArgumentException.class, () -> new Profile(gradeList));

	}

	@Test
	@DisplayName("Test 34: Test first classification for Profile")
	void testFirstGradeProfile() {
		List<Grade> gradeList = new ArrayList<Grade>();

		gradeList.add(Grade.fromPercentage(78));
		gradeList.add(Grade.fromPercentage(78));
		gradeList.add(Grade.fromPercentage(80));
		gradeList.add(Grade.fromPercentage(80));
		Profile profile = new Profile(gradeList);
		assertEquals(Classification.First, profile.classify());

	}

	@Test
	@DisplayName("Test 35: Test upperSecond classification for Profile")
	void testUpperSecondGradeProfile() {
		List<Grade> gradeList = new ArrayList<Grade>();

		gradeList.add(Grade.fromPercentage(67));
		gradeList.add(Grade.fromPercentage(67));
		gradeList.add(Grade.fromPercentage(50));
		gradeList.add(Grade.fromPercentage(60));
		Profile profile = new Profile(gradeList);
		assertEquals(Classification.UpperSecond, profile.classify());

	}

	@Test
	@DisplayName("Test 36: Expecting lowerSecond  classification for Profile")
	void testLowerSecondGradeProfile() {
		List<Grade> gradeList = new ArrayList<Grade>();

		gradeList.add(Grade.fromPercentage(55)); 
		gradeList.add(Grade.fromPercentage(57));
		gradeList.add(Grade.fromPercentage(50));
		gradeList.add(Grade.fromPercentage(40));
		Profile profile = new Profile(gradeList);
		assertEquals(Classification.LowerSecond, profile.classify());

	}

	@Test
	@DisplayName("Test 37: Expecting third classification for Profile")
	void testThirdGradeProfile() {
		List<Grade> gradeList = new ArrayList<Grade>();

		gradeList.add(Grade.fromPercentage(42));
		gradeList.add(Grade.fromPercentage(47));
		gradeList.add(Grade.fromPercentage(40));
		gradeList.add(Grade.fromPercentage(60));
		Profile profile = new Profile(gradeList);
		assertEquals(Classification.Third, profile.classify());

	}

	@Test
	@DisplayName("Test 38: Expecting true(Clear) from isClear method")
	void testIsClear() {
		List<Grade> gradeList = new ArrayList<Grade>();

		gradeList.add(Grade.fromPercentage(88));
		gradeList.add(Grade.fromPercentage(77));
		gradeList.add(Grade.fromPercentage(65));
		gradeList.add(Grade.fromPercentage(44));
		Profile profile = new Profile(gradeList);
		assertTrue(profile.isClear());

	}

	@Test
	@DisplayName("Test 39: Expecting false(Borderline) from isClear method")
	void testIsBorderline() {
		List<Grade> gradeList = new ArrayList<Grade>();

		gradeList.add(Grade.fromPercentage(88));
		gradeList.add(Grade.fromPercentage(77));
		gradeList.add(Grade.fromPercentage(45));
		gradeList.add(Grade.fromPercentage(44));
		Profile profile = new Profile(gradeList);
		assertFalse(profile.isClear());

	}

	/** Tests for Degree class */
	@Test
	@DisplayName("Test 40: Test null list for Degree constuctor")
	void testNullListInDegreee() {
		List<Grade> profileFive = null;
		List<Grade> profileSix = null;

		assertThrows(IllegalArgumentException.class, () -> new Degree(profileFive, profileSix));

	}

	@Test
	@DisplayName("Test 41: Test less than four grades check for Degree constuctor")
	void testLessGradesInDegreee() {
		List<Grade> profileFive = new ArrayList<>();
		List<Grade> profileSix = new ArrayList<>();
		profileFive.add(Grade.fromPercentage(78));
		profileFive.add(Grade.fromPercentage(78));
		profileFive.add(Grade.fromPercentage(70));
		profileFive.add(Grade.fromPercentage(40));
		profileSix.add(Grade.fromPercentage(78));
		profileSix.add(Grade.fromPercentage(78));
		profileSix.add(Grade.fromPercentage(70));

		assertThrows(IllegalArgumentException.class, () -> new Degree(profileFive, profileSix));

	}

	@Test
	@DisplayName("Test 42: Test fail grade passed to Degree constuctor")
	void testFailGradeDegree() {
		List<Grade> profileFive = new ArrayList<Grade>();
		List<Grade> profileSix = new ArrayList<Grade>();

		profileFive.add(Grade.fromPercentage(78));
		profileFive.add(Grade.fromPercentage(78));
		profileFive.add(Grade.fromPercentage(70));
		profileFive.add(Grade.fromPercentage(20));
		profileSix.add(Grade.fromPercentage(78));
		profileSix.add(Grade.fromPercentage(78));
		profileSix.add(Grade.fromPercentage(70));
		profileSix.add(Grade.fromPercentage(20));

		assertThrows(IllegalArgumentException.class, () -> new Degree(profileFive, profileSix));
		
	}

	@Test
	@DisplayName("Test 43: Test first classification from Degree")
	void testFirstGradeDegree() {
		List<Grade> profileFive = new ArrayList<Grade>();
		List<Grade> profileSix = new ArrayList<Grade>();

		profileFive.add(Grade.fromPercentage(65));
		profileFive.add(Grade.fromPercentage(58));
		profileFive.add(Grade.fromPercentage(50));
		profileFive.add(Grade.fromPercentage(40));
		
		profileSix.add(Grade.fromPercentage(85));
		profileSix.add(Grade.fromPercentage(78));
		profileSix.add(Grade.fromPercentage(65));
		profileSix.add(Grade.fromPercentage(40));
		Degree degree = new Degree(profileFive, profileSix);
		assertEquals(Classification.First, degree.classify());

	}

	@Test
	@DisplayName("Test 44: Test upperSecond classification for Degree")
	void testUpperSecondGradeDegree() {
		List<Grade> profileFive = new ArrayList<Grade>();
		List<Grade> profileSix = new ArrayList<Grade>();

		profileFive.add(Grade.fromPercentage(44));
		profileFive.add(Grade.fromPercentage(64));
		profileFive.add(Grade.fromPercentage(68));
		profileFive.add(Grade.fromPercentage(40));
		profileSix.add(Grade.fromPercentage(68));
		profileSix.add(Grade.fromPercentage(55));
		profileSix.add(Grade.fromPercentage(60));
		profileSix.add(Grade.fromPercentage(45));
		Degree degree = new Degree(profileFive, profileSix);
		assertEquals(Classification.UpperSecond, degree.classify());

	}

	@Test
	@DisplayName("Test 45: Test lowerSecond classification for Degree")
	void testLowerSecondGradeDegree() {
		List<Grade> profileFive = new ArrayList<Grade>();
		List<Grade> profileSix = new ArrayList<Grade>();

		profileFive.add(Grade.fromPercentage(54));
		profileFive.add(Grade.fromPercentage(54));
		profileFive.add(Grade.fromPercentage(58));
		profileFive.add(Grade.fromPercentage(50));
		profileSix.add(Grade.fromPercentage(68));
		profileSix.add(Grade.fromPercentage(45));
		profileSix.add(Grade.fromPercentage(40));
		profileSix.add(Grade.fromPercentage(45));
		Degree degree = new Degree(profileFive, profileSix);
		assertEquals(Classification.LowerSecond, degree.classify());

	}

	@Test
	@DisplayName("Test 46: Test third classification for Degree class")
	void testThirdGradeDegree() {
		List<Grade> profileFive = new ArrayList<Grade>();
		List<Grade> profileSix = new ArrayList<Grade>();

		profileFive.add(Grade.fromPercentage(74));
		profileFive.add(Grade.fromPercentage(44));
		profileFive.add(Grade.fromPercentage(48));
		profileFive.add(Grade.fromPercentage(40));
		profileSix.add(Grade.fromPercentage(48));
		profileSix.add(Grade.fromPercentage(45));
		profileSix.add(Grade.fromPercentage(50));
		profileSix.add(Grade.fromPercentage(45));
		Degree degree = new Degree(profileFive, profileSix);
		assertEquals(Classification.Third, degree.classify());

	}

	@Test
	@DisplayName("Test 47: Test discretion classification for Degree class")
	void testDiscretionGradeDegree() {
		List<Grade> profileFive = new ArrayList<Grade>(); 
		List<Grade> profileSix = new ArrayList<Grade>();

		profileFive.add(Grade.fromPercentage(54));
		profileFive.add(Grade.fromPercentage(64));
		profileFive.add(Grade.fromPercentage(58));
		profileFive.add(Grade.fromPercentage(50));
		profileSix.add(Grade.fromPercentage(78));
		profileSix.add(Grade.fromPercentage(75));
		profileSix.add(Grade.fromPercentage(40));
		profileSix.add(Grade.fromPercentage(45));
		Degree degree = new Degree(profileFive, profileSix);
		assertEquals(Classification.Discretion, degree.classify());

	}

}
