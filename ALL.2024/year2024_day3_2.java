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


class year2024_day3_2 {
	public static Vector <Integer> dos = new Vector<>();
	public static Vector <Integer> donts = new Vector<>();
	public static void main(String [] args) {
		out.println("		2024 Day3.2");
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			StringBuilder bigline = new StringBuilder();
			while ((line = br.readLine()) != null) {
				bigline.append(line);
				//blah.add(line);
			}
			blah.add(bigline.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}	

		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);

		String fp1 = Pattern.quote("do()");
		Pattern p1 = Pattern.compile(fp1);
		dos.add(0);
		for (int i = 0; i < blah.size(); i++) {
			String ne = blah.get(i);
			Matcher m = p1.matcher(ne);
			while (m.find()) {
				dos.add(m.start());
			}
		}
		dos.add(40000);

		String fp2 = Pattern.quote("don't()");
		Pattern p2 = Pattern.compile(fp2);
		for (int i = 0; i < blah.size(); i++) {
			String ne = blah.get(i);
			Matcher m = p2.matcher(ne);
			while (m.find()) {
				//out.println(m.start());
				donts.add(m.start());
			}
		}
		donts.add(40000);

		String firstpart = Pattern.quote("mul(");
		String secondpart = Pattern.quote(")");
		Pattern p = Pattern.compile(firstpart + "(\\d+),(\\d+)" + secondpart);
		int tot = 0;
		for (int i = 0; i < blah.size(); i++) {
			String ne = blah.get(i);
			Matcher m = p.matcher(ne);
			while (m.find()) {
				if (dosactive(m.start())) {
					int fir = Integer.valueOf(m.group(1));
					int sec = Integer.valueOf(m.group(2));
					int mul = fir * sec;
					//out.print(fir); out.print(" * "); out.println(sec);
					tot += mul;
				}
			}
		}
		
		out.print("**j_ans: ");
		out.print(tot);
		out.println("");
	}
	public static boolean dosactive(int pos) {
		int lastdo = 0;
		for (int ii = 0; ii < dos.size()-1; ii++) {
			if (dos.get(ii) < pos && dos.get(ii+1) > pos) {
				lastdo = dos.get(ii);
				break;
			}
		}
		int lastdont = -1;
		for (int ii = 0; ii < donts.size()-1; ii++) {
			if (donts.get(ii) < pos && donts.get(ii+1) > pos) {
				lastdont = donts.get(ii);
				break;
			}
		}
		//out.print(lastdo); out.print("  (lastdo) V (lastdont)  "); out.print(lastdont); out.print(" pos: "); out.println(pos);
		if (lastdo > lastdont) {
			return true;
		} else {
			return false;
		}
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

