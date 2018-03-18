package game;

import java.util.Comparator;

public class SortArray implements Comparator<Marble> {

	//To sort use: Collections.sort(nameOfList, new SortArray());
	public int compare(Marble m1, Marble m2) {

	    int diff = (int)m1.getColor() - (int)m2.getColor();
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