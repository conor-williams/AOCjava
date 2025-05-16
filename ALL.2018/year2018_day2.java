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

class year2018_day2 {
	public static void main(String [] args) {
		out.println("		2018 Day2.1");
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	

		int twos = 0;
		int threes = 0;
		for (int i = 0; i < blah.size(); i++) {
			int [] ar = new int[27];
			String one = blah.get(i);
			for (int ii = 0; ii < one.length(); ii++) {
				ar[one.charAt(ii)-97]++;
			}
			int oncetwo = 1;
			int oncethree = 1;
after:
			for  (int jj = 0; jj < 26; jj++) {
				if (oncetwo == 0 && oncethree == 0) {break after;}
				if (ar[jj] == 2 && oncetwo == 1) {
					twos++;
					oncetwo = 0;
				} else if (ar[jj] == 3 && oncethree == 1) {
					threes++;
					oncethree = 0;
				}
			}
		}
		out.print("**j_ans: ");
		out.println(twos*threes);
	}
}
