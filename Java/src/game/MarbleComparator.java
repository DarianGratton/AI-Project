package game;

import java.util.Comparator;

public class MarbleComparator implements Comparator<Marble> {

	//To sort use: Collections.sort(nameOfList, new MarbleComparator());
	public int compare(Marble m1, Marble m2) {
	    
	    int diff = m2.intColour() - m1.intColour();
		if (diff != 0) {
			return diff;
		}
		diff = m1.getAlpha() - m2.getAlpha();
		if (diff != 0) {
			return diff;
		}
		diff = m1.getNumeric() - m2.getNumeric();
		return diff;
		
	}


}