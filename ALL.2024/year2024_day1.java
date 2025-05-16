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


class year2024_day1 {
	public static void main(String [] args) {
		out.println("		2024 Day1.1");
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
		//	m.find();
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		Vector <Integer> veleft = new Vector<>();
		Vector <Integer> veright = new Vector<>();

		for (int i = 0; i < blah.size(); i++) {
			//out.println(blah.get(i));
			Scanner scanner = new Scanner(blah.get(i));
                        scanner.useDelimiter("[\\s]+");
                        veleft.add(Integer.valueOf(scanner.next()));
                        veright.add(Integer.valueOf(scanner.next()));
                }
                Collections.sort(veleft);
                Collections.sort(veright);
		Vector <Integer> diffs = new Vector<>();
		for (int iii = 0; iii < veleft.size(); iii++) {
			diffs.add(Math.abs(veleft.get(iii) - veright.get(iii)));
		}

		int tot = 0;
		for (int jjj = 0; jjj < diffs.size(); jjj++) {
			tot += diffs.get(jjj);
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

