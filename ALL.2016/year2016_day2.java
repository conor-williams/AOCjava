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

class year2016_day2 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2016 Day2.1");
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
		int x = 1;
		int y = 1;
		int [] ar = new int[blah.size()];
		for (int i = 0; i < blah.size(); i++) {
			String ne = blah.get(i);
			for (int ii = 0; ii < ne.length(); ii++) { 
				switch (ne.charAt(ii)) {
					case 'U':
						if (y == 0) {continue;}
						y--;
						break;
					case 'R':
						if (x == 2) {continue;}
						x++;
						break;
					case 'D':
						if (y == 2) {continue;}
						y++;
						break;
					case 'L':
						if (x == 0) {continue;}
						x--;
						break;
				}

			}
			ar[i] = keypad[y][x];
			//	out.println(ar[i]);
			
		}
		out.print("**j_ans: ");
		for (int num: ar) {
			out.print(num);
		}
		out.println("");
	}
}
