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


class year2023_day2_2 {
	public static void main(String [] args) {
		out.println("		2023 Day2.2");
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	

		Pattern p = Pattern.compile("Game (\\d+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//	month= m.group(2);

		//BigInteger tot =  BigInteger.valueOf((long)0);
		int tot = 0;
		for (int i = 0; i < blah.size(); i++) {
			Scanner scanner = new Scanner(blah.get(i));
			scanner.useDelimiter(":");
			String gameidstr = scanner.next();
			Matcher m = p.matcher(gameidstr);
			m.find();
			int id = Integer.valueOf(m.group(1));

			String ne = scanner.next();
			Scanner scanner2 = new Scanner(ne);
			scanner2.useDelimiter(";");
			Vector<Vector <Tuple <Integer, String>>> veOverAll = new Vector<>();
			while (scanner2.hasNext()) {
				String game = scanner2.next();

				Scanner sg = new Scanner(game);
				Vector <Tuple <Integer, String>> vegame = new Vector<>();
				sg.useDelimiter("[\t ,\\s]+");
				while (sg.hasNext()) {
					String one = sg.next();
					//out.println("["+one+"]");
					Integer fir = Integer.valueOf(one);
					String sec = sg.next();
					Tuple <Integer, String> tu = new Tuple<>(fir, sec);
					vegame.add(tu);
				}
				veOverAll.add(vegame);
			}

			int maxRed = 0;
			int maxGreen = 0;
			int maxBlue = 0;
			for (int lll = 0; lll < veOverAll.size(); lll++) {
				Vector <Tuple <Integer, String>> vegame = veOverAll.get(lll);

				for (int ii = 0; ii < vegame.size(); ii++) {
					Tuple <Integer, String> tu = vegame.get(ii);
					if (tu.second.equals("red")) {
						if (tu.first > maxRed) {
							maxRed = tu.first;
						}
					}
					if (tu.second.equals("green")) {
						if (tu.first > maxGreen) {
							maxGreen = tu.first;
						}
					}
					if (tu.second.equals("blue")) {
						if (tu.first > maxBlue) {
							maxBlue = tu.first;
						}
					}
				}
			}
			tot += maxRed*maxGreen*maxBlue;

		}
		out.print("**j_ans: ");
		out.print(tot);
		out.println("");
	}
}

class Tuple<X, Y> {
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

