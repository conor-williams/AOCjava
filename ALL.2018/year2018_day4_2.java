import java.math.BigInteger;
import java.util.Scanner;
import java.util.Arrays;
import java.math.BigInteger;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import static java.lang.System.out;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import java.util.Objects;
import java.util.Collections;
import java.lang.Character;
import java.util.HashMap;
import java.util.Map;
import java.util.*;
import java.util.stream.*;

// /java -Xmx2g year2019_day3.java *i1.txt


class year2018_day4_2 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2018 Day4.2");
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
		Collections.sort(blah);


		String fp = Pattern.quote("[");
		String sp = Pattern.quote("]");
		Pattern p = Pattern.compile(fp+"(\\d{4})-(\\d{2})-(\\d{2}) (\\d{2}):(\\d{2})"+sp+ " ([A-Za-z#0-9 ]+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		Pattern g = Pattern.compile("Guard #(\\d+) begins shift");
		int timeFallAsleep = 0;
		int guardNum = 0;
		Map <Integer, Integer> mp = new HashMap<>();
		Map <Integer, int[]> mpMins = new HashMap<>();
		for (int i = 0; i < blah.size(); i++) {
			//out.println(blah.get(i));
			Matcher m = p.matcher(blah.get(i));
			m.find();
			String six = m.group(6);
			//out.println(six);
			if (six.charAt(0) == 'G') {
				Matcher mG = g.matcher(six);
				mG.find();
				guardNum = Integer.valueOf(mG.group(1));
				//out.print("Guard: "); out.println(guardNum);
			} else if (six.charAt(0) == 'f') {
				timeFallAsleep = Integer.valueOf(m.group(5));
				//out.print("Fall: "); out.println(timeFallAsleep);
			} else if (six.charAt(0) == 'w') {
				int timeWake = Integer.valueOf(m.group(5));
				int timeAsleep = timeWake - timeFallAsleep;
				//out.print(guardNum); out.print(" Wake-Fall: "); out.println(timeAsleep);
				int [] mins = new int[60];

				if (mpMins.containsKey(guardNum)) {
					mins = mpMins.get(guardNum);
				}
				for (int ii = timeFallAsleep; ii < timeWake; ii++) {
					mins[ii]++;
				}
				mpMins.put(guardNum, mins);

				int val = 0;
				if (mp.containsKey(guardNum)) {
					val = mp.get(guardNum);
					//	out.print("val: "); out.println(val);
				}
				mp.put(guardNum, val+timeAsleep);
			}
		}
		Iterator <Map.Entry<Integer, int[]>> iterator = mpMins.entrySet().iterator();
		int guardG = 0;
		int max = 0;
		int themaxmin = 0; 
		while (iterator.hasNext()) {
			Map.Entry<Integer, int[]> entry = iterator.next();
			//out.print("Guard: "); out.print(entry.getKey()); out.print(" mins ");
			//out.println(entry.getValue());
			int gg = entry.getKey();
			int [] xx = entry.getValue();

			for (int ii = 0; ii < 60; ii++) {
				if (xx[ii] > max) {max = xx[ii]; themaxmin = ii; guardG = gg;}
			}
		}
		out.print("**j_ans: ");
		out.print(guardG*themaxmin);
		out.println("");
	}
}

public class Tuple<X,Y > {
	public final X first;
	public final Y second;

	public Tuple(X first, Y second) {
		this.first = first;
		this.second = second;
	}
	@Override
	public boolean equals(Object o) {
		Tuple tu2 = (Tuple) o;
		if (this == o) return true;
		if (!(o instanceof Tuple)) return false;
		int fir1 = (int)first;
		int fir2 = (int)tu2.first;
		int sec1 = (int)second;
		int sec2 = (int)tu2.second;
		if (fir1 != fir2) {return false;}
		if (sec1 != sec2) {return false;}
		return true;
	}
	@Override
	public int hashCode() {
		return Objects.hash(first, second);
	}

}

