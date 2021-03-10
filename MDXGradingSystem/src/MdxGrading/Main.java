
package MdxGrading;

import java.util.*;
public class Main {

	public static void main(String[] args) {
		List<Grade> profileFive = new ArrayList<Grade>();
		List<Grade> profileSix = new ArrayList<Grade>();
 
		profileFive.add(Grade.fromPercentage(50));
		profileFive.add(Grade.fromPercentage(59));
		profileFive.add(Grade.fromPercentage(72));
		profileFive.add(Grade.fromPercentage(62));
		
		profileSix.add(Grade.fromPercentage(64));
		profileSix.add(Grade.fromPercentage(66));
		profileSix.add(Grade.fromPercentage(68)); 
		profileSix.add(Grade.fromPercentage(68));
		Degree degree = new Degree(profileFive,profileSix);
		System.out.print(" and classification is/will be "+degree.classify());
 
	} 

}
