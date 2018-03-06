package game;

import java.util.Comparator;

public class SortArray implements Comparator {

	//To sort use: Collections.sort(nameOfList, new SortArray());
	public int compare(Marble m1, Marble m2) {

		if (m1.getColor() <= (m2.getColor())) {
			return m1.getColor();
		}
		if (m1.getAlpha() <= (m2.getAlpha())) {
			return m1.getAlpha();
		}
		if (m1.getNumeric() <= (m2.getNumeric())) {
			return m1.getNumeric();
		}
		return 0;
	}

	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		return 0;
	}
}