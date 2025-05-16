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


class year2015_day3_2 {
	public static void main(String [] args) {
		out.println("		2015 Day3.2");
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	

		//Pattern p = Pattern.compile("(L|R)(\\d+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		@SuppressWarnings({"unchecked"})
		Map <Tuple <Integer, Integer>, Integer> [] mp = new HashMap[2];
		for (int i = 0; i < 2; i++) {
			mp[i] = new HashMap<>();
		}
		for (int i = 0; i < blah.size(); i++) {
			String ne = blah.get(i);
			int [] xS = new int[2];
			int [] yS = new int[2];
			Tuple <Integer, Integer> tu = new Tuple<>(0, 0);
			mp[0].put(tu, 1);
			mp[1].put(tu, 1);

			//out.println(ne);
			for (int ii = 0; ii < ne.length(); ii++) { 
				int mo = ii%2;
				switch (ne.charAt(ii)) {
					case '^':
						yS[mo]--;
						break;
					case '>':
						xS[mo]++;
						break;
					case 'v':
						yS[mo]++;
						break;
					case '<':
						xS[mo]--;
						break;
				}
				Tuple <Integer, Integer> tu2 = new Tuple<>(xS[mo], yS[mo]);
				if (!mp[0].containsKey(tu2)) {
					//out.print(xS[mo]); out.print(" "); out.println(yS[mo]);
					mp[0].put(tu2, 1);
				} else {
					//out.print("already:"); out.print(xS[mo]); out.print(" "); out.println(yS[mo]);
					
					mp[0].put(tu2, mp[0].get(tu2)+1);
				}
			}
		}

		out.print("**j_ans: ");
		out.print(mp[0].size());
		out.println("");
	}
}

class Tuple<X,Y > {
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
		//if (!first.equals(tu2.first)) {return false;}
		//if (!second.equals(tu2.second)) {return false;}
		if (fir1 != fir2) {return false;}
		if (sec1 != sec2) {return false;}
		return true;
	}
	@Override
	public int hashCode() {
		return Objects.hash(first, second);
	}

}

