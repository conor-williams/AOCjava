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
// /java -Xmx2g year2019_day3.java *i1.txt


class year2023_day5 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2023 Day5.1");
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	

		//	String firstpart = Pattern.quote("mul(");
		Pattern seeds = Pattern.compile("seeds:([\\s\\d]+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		Vector <BigInteger> seedsVe = new Vector<>();
		Vector <Vector <TreTupleBig <BigInteger, BigInteger, BigInteger>>> tre = new Vector <> ();
		Vector <TreTupleBig <BigInteger, BigInteger, BigInteger>> t1 = new Vector <> ();
		for (int i = 0; i < blah.size(); i++) {
			String ne = blah.get(i);
			if (i == 0) {
				Matcher m = seeds.matcher(ne);
				m.find();

			   	Scanner scanner = new Scanner(m.group(1));
				scanner.useDelimiter("[\t\\s ]+");
			        while (scanner.hasNext()) {
			                String ne2 = scanner.next();
			   		seedsVe.add(new BigInteger(ne2));
			   	}
				continue;
			}
			if (ne.length() == 0) {
				if (t1.size() > 0) {
					Vector <TreTupleBig <BigInteger, BigInteger, BigInteger>> t2 =
						new Vector <>(t1);
					tre.add(t2);
					t1.clear();
				}
				//write vec...
				continue;
			} else if (Character.isDigit(ne.charAt(0))) {
			   	Scanner scanner = new Scanner(ne);
				scanner.useDelimiter("[\t\\s ]+");
			        String x1 = scanner.next();
				String x2 = scanner.next();
				String x3 = scanner.next();

				TreTupleBig <BigInteger, BigInteger, BigInteger> t3 = new TreTupleBig<>(new BigInteger(x1), new BigInteger(x2), new BigInteger(x3));
			   	t1.add(t3);
			}
		}
		Vector <TreTupleBig <BigInteger, BigInteger, BigInteger>> t3 =
			new Vector <>(t1);
		tre.add(t3);
		t1.clear();
		
		BigInteger minLocation = new BigInteger("99999999999");
		for (int ii = 0; ii < seedsVe.size(); ii++) {
			BigInteger value = seedsVe.get(ii);
			for (int jj = 0; jj < tre.size(); jj++) {
				Vector <TreTupleBig <BigInteger, BigInteger, BigInteger>> ve2 = tre.get(jj);
after:
				for (int kk = 0; kk < ve2.size(); kk++) {
					TreTupleBig <BigInteger, BigInteger, BigInteger> t4 = ve2.get(kk);
					
					//if (value >= t4.second && value <= t4.second+t4.third) {
					if (value.compareTo(t4.second) >=0 && value.compareTo(t4.second.add(t4.third)) <= 0) {
						BigInteger dif = value.subtract(t4.second);
						value = t4.first.add(dif);
						break after;
					}

				}
				//value = value;
			}
			//out.print(value); out.print(" ");
			//out.println(value);
			//if (value < minLocation) {minLocation = value;}
			if (value.compareTo(minLocation) == -1) {minLocation = value;}
		}
		//write vec
		out.print("**j_ans: ");
		out.print(minLocation);
		out.println("");
	}
}

class TreTupleBig<X,Y, Z> {
	public final X first;
	public final Y second;
	public final Z third;

	public TreTupleBig(X first, Y second, Z third) {
		this.first = first;
		this.second = second;
		this.third = third;
	}
	@Override
	public boolean equals(Object o) {
		TreTupleBig tu2 = (TreTupleBig) o;
		if (this == o) return true;
		if (!(o instanceof TreTupleBig)) return false;
		
		//BigInteger fir1 = ()first;
		/*
		int fir2 = (int)tu2.first;
		int sec1 = (int)second;
		int sec2 = (int)tu2.second;
		int thi1 = (int)third;
		int thi2 = (int)tu2.third;
		*/
		if (!first.equals(tu2.first)) {return false;}
		if (!second.equals(tu2.second)) {return false;}
		if (!third.equals(tu2.third)) {return false;}
		return true;
	}
	@Override
	public int hashCode() {
		return Objects.hash(first, second, third);
	}

}
class TreTuple<X,Y, Z> {
	public final X first;
	public final Y second;
	public final Z third;

	public TreTuple(X first, Y second, Z third) {
		this.first = first;
		this.second = second;
		this.third = third;
	}
	@Override
	public boolean equals(Object o) {
		TreTuple tu2 = (TreTuple) o;
		if (this == o) return true;
		if (!(o instanceof TreTuple)) return false;
		int fir1 = (int)first;
		int fir2 = (int)tu2.first;
		int sec1 = (int)second;
		int sec2 = (int)tu2.second;
		int thi1 = (int)third;
		int thi2 = (int)tu2.third;
		if (fir1 != fir2) {return false;}
		if (sec1 != sec2) {return false;}
		if (thi1 != thi2) {return false;}
		return true;
	}
	@Override
	public int hashCode() {
		return Objects.hash(first, second, third);
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
		if (fir1 != fir2) {return false;}
		if (sec1 != sec2) {return false;}
		return true;
	}
	@Override
	public int hashCode() {
		return Objects.hash(first, second);
	}

}

