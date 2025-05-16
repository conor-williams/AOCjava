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

class year2021_day2_2 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2021 Day2.2");
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	

		Pattern p = Pattern.compile("(forward|down|up) (\\d+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	month= m.group(2);

		//BigInteger tot =  BigInteger.valueOf((long)0);
		int horiz = 0;
		int updown = 0;
		int aim = 0;
		for (int i = 0; i < blah.size(); i++) {
			String ne = blah.get(i);
			Matcher m = p.matcher(ne);
			m.find();
			String dirIt = m.group(1);
			int val = Integer.valueOf(m.group(2));
			if (dirIt.equals("forward")) {
				horiz += val;
				updown += val*aim;
			} else if (dirIt.equals("up")) {
				aim -= val;
			} else if (dirIt.equals("down")) {
				aim += val;
			} else {
				out.println("["+dirIt+"]");
			}
			
		}
		out.print("**j_ans: ");
		out.print(horiz*updown);
		out.println("");
	}
}
