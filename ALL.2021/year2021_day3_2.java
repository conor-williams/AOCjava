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


class year2021_day3_2 {
	public static void main(String [] args) {
		out.println("		2021 Day3.2");
		Vector<String> blah = new Vector<>();
		int leny = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
			leny++;
		} catch (IOException e) {
			e.printStackTrace();
		}	

		char [] digitsG = new char[20];
		char [] digitsE = new char[20];
		int lent = blah.get(0).length();
		/*
		for (iii = 0; iii < blah.get(0).length(); iii++) {
			int count0 = 0;
			int count1 = 1;
			for (int i = 0; i < blah.size(); i++) {
				String ne = blah.get(i);
				if (ne.charAt(iii) == '0') {
					count0++;
				} else {
					count1++;
				}
			}
			if (count0 > count1) {
				digitsG[iii] = '0';
				digitsE[iii] = '1';
			} else {
				digitsE[iii] = '0';
				digitsG[iii] = '1';
			}

		}
		*/

		String string1 = getit(blah, lent, true);
		String dGs = Integer.toString(Integer.parseInt(string1, 2), 10);
		//out.println(dGs);

		//out.println("------");
		String string2 = getit(blah, lent, false);
		String dEs = Integer.toString(Integer.parseInt(string2, 2), 10);
		//out.println(dEs);

		int ans = Integer.valueOf(dGs) * Integer.valueOf(dEs);
		out.print("**j_ans: ");
		out.print(ans);
		out.println("");
	}

	public static String getit(Vector <String> blah, int size, boolean flip) 
	{
		Vector <String> poses = new Vector<>(blah);
		Vector <String> posesTmp = new Vector<>();
		Vector <String> tmpones = new Vector<>();
		Vector <String> tmpzeroes = new Vector<>();
		for (int ii = 0; ii < size; ii++) {
			for (int zz = 0; zz < poses.size(); zz++) {
				if (poses.get(zz).charAt(ii) == '0') {
					tmpzeroes.add(poses.get(zz));
				} else {
					tmpones.add(poses.get(zz));
				}
			}
			if (tmpones.size() == tmpzeroes.size()) {
				if (flip) {
					poses = new Vector<>(tmpones);
				} else {
					poses = new Vector<>(tmpzeroes);
				}
			} else if (tmpones.size() > tmpzeroes.size()) {
				if (flip) {
					poses = new Vector<>(tmpones);
				} else {
					poses = new Vector<>(tmpzeroes);
				}
			} else {
				if (flip) {
					poses = new Vector<>(tmpzeroes);
				} else {
					poses = new Vector<>(tmpones);
				}
			}

			posesTmp.clear();
			tmpones.clear();
			tmpzeroes.clear();
			if (poses.size() == 1) {
				return new String(poses.get(0));
			}
			//for (int rrr = 0; rrr < poses.size(); rrr++) {out.println(poses.get(rrr)); }
		}
		return "";
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

/*
   if (poses.size() == 1) {
   string1 = poses.get(0); break;
   } else {
   out.println(poses.size());
   for (int qqq = 0; qqq < poses.size(); qqq++) {
   if (poses.get(qqq).charAt(ii) == '1') {
   string1 = poses.get(qqq);
   break after1;
   }
   }
   }
   */
