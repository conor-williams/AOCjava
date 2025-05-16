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

class year2022_day1_2 {
	public static void main(String [] args) {
		out.println("		2022 Day1.2");
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
		//	month= m.group(2);

		//BigInteger tot =  BigInteger.valueOf((long)0);
		Vector<Integer> tots = new Vector<>();
		int curTot = 0;
		for (int i = 0; i < blah.size(); i++) {
			String ne = blah.get(i);
			if (ne.trim().isEmpty()) {
				tots.add(curTot);
				curTot = 0;
			} else {
				curTot += Integer.valueOf(ne);
			}
		}
		tots.add(curTot);
		Collections.sort(tots);

		out.print("**j_ans: ");
		out.print(tots.get(tots.size()-1)+tots.get(tots.size()-2)+tots.get(tots.size()-3));
		out.println("");
	}
}
