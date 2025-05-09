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
	public static char [][] keypad = {{'0', '0', '0', '0', '0', '0', '0'},
		{'0', '0', '0', '1', '0', '0', '0'}, {'0', '0', '2', '3', '4', '0', '0'},
		{'0', '5', '6', '7', '8', '9', '0'}, {'0', '0', 'A', 'B', 'C', '0', '0'},
		{'0', '0', '0', 'D', '0', '0', '0'}, {'0', '0', '0', '0', '0', '0', '0'} };
	public static void main(String [] args) {
		out.println("		2016 Day2.2");
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	

		int x = 1;
		int y = 3;
		char [] ar = new char[blah.size()];
		for (int i = 0; i < blah.size(); i++) {
			String ne = blah.get(i);
			for (int ii = 0; ii < ne.length(); ii++) { 
				switch (ne.charAt(ii)) {
					case 'U':
						if (keypad[y-1][x] == '0') {continue;}
						y--;
						break;
					case 'R':
						if (keypad[y][x+1] == '0') {continue;}
						x++;
						break;
					case 'D':
						if (keypad[y+1][x] == '0') {continue;}
						y++;
						break;
					case 'L':
						if (keypad[y][x-1] == '0') {continue;}
						x--;
						break;
				}

			}
			ar[i] = keypad[y][x];
			//	out.println(ar[i]);
			
		}
		out.print("**j_ans: ");
		for (char num: ar) {
			out.print(num);
		}
		out.println("");
	}
}
