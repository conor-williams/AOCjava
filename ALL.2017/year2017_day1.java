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

class year2017_day1 {
	public static void main(String [] args) {
		out.println("		2017 Day1.1");
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	

		int tot = 0;
		for (int i = 0; i < blah.size(); i++) {
			String original = blah.get(i);
			char toAdd = original.charAt(0);
			String ne = String.format("%s%c", original, toAdd);
			for (int ii = 0; ii < ne.length()-1; ii++) { 
				if (ne.charAt(ii) == ne.charAt(ii+1)) {
					tot+= ne.charAt(ii)-48;
				}
			}
			
		}
		out.print("**j_ans: ");
		out.println(tot);
	}
}
