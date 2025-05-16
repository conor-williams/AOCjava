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


class year2023_day4_2 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2023 Day4.2");
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	


		//Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
		//	String firstpart = Pattern.quote("mul(");
		Pattern p = Pattern.compile("Card[\\s]+(\\d+): ([\\s\\d]+) \\| ([\\s\\d]+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);

		Vector<String> blahOrig = new Vector<>(blah);
		Map <Integer, Integer> mp = new HashMap<>();

		
		int maxCardNum = 0;
		for (int i = 0; i < blah.size(); i++) {
			Matcher m = p.matcher(blah.get(i));
			m.find();
			int cardNum = Integer.valueOf(m.group(1));
			mp.put(cardNum, 1);
			maxCardNum = cardNum;
		}
		for (int i = 0; i < blah.size(); i++) {
			Vector <Integer> var_wins = new Vector<>();
			Vector <Integer> var_play = new Vector<>();

			Matcher m = p.matcher(blah.get(i));
			m.find();
			int cardNum = Integer.valueOf(m.group(1));

			//out.println(cardNum);
			String winningNums = m.group(2);
			//out.println(winningNums);
			String playNums = m.group(3);
			//out.println(playNums);

			{
				Scanner scanner = new Scanner(winningNums);
				scanner.useDelimiter("[\t\\s ]+");
				while (scanner.hasNext()) {
					String ne = scanner.next();
					var_wins.add(Integer.valueOf(ne));
				}
				//Collections.sort(var_ints);
			}
			{
				Scanner scanner = new Scanner(playNums);
				scanner.useDelimiter("[\t\\s ]+");
				while (scanner.hasNext()) {
					String ne = scanner.next();
					var_play.add(Integer.valueOf(ne));
				}
				//Collections.sort(var_play);
			}
			var_wins.retainAll(var_play);
			if (var_wins.size() != 0) {
				int wi = var_wins.size();
				//out.print("wins: "); out.println(wi);
				for (int zz = cardNum+1; zz < cardNum+1+wi; zz++) {
					if (zz > /*blah.size()*/maxCardNum) {
						break;
					} else {
						//out.print("carnum (each?): "); out.println(mp.get(cardNum));
						//out.print("mp.get("); out.print(zz); out.print(" ((winner): "); out.println(mp.get(zz));
						mp.put(zz, mp.get(cardNum)+mp.get(zz));

					}
				}
			}

		}
		int tot = 0;
		for (Integer key : mp.keySet()) {
			//System.out.println("Key: " + key + ", Value: " + mp.get(key));
			tot += mp.get(key);
		}

		out.print("**j_ans: ");
		out.print(tot);
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
		if (fir1 != fir2) {return false;}
		if (sec1 != sec2) {return false;}
		return true;
	}
	@Override
	public int hashCode() {
		return Objects.hash(first, second);
	}

}

