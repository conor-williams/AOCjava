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
import java.util.*;
import java.io.*;
import java.lang.*;
// /java -Xmx2g year2019_day3.java *i1.txt


//                        grid = Arrays.stream(gridTmp).map(char[]::clone).toArray(char[][]::new);
// pe.sort(Comparator.comparingInt((TreTuple t) -> (int)t.third).thenComparingInt((TreTuple t) -> (int)t.second).thenComparingInt((TreTuple t) -> (int)t.first));///tuples.sort(Comparator.comparingInt(tuple -> tuple[0]));
//Scanner scanner = new Scanner(System.in); scanner.nextLine();
// int max = var_ints.stream().max(Integer::compare).orElseThrow();
// int position = var_ints.indexOf(max);

//                        for (var entry : mp.entrySet()) {
// System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//}

@SuppressWarnings("unchecked")
class year2023_day15_2 {
	//	        public static int maxPath = 0;
	//    public static int lenx = 0;
	//   public static int leny = 0;
	//  public static char grid [][] = new char[leny][lenx];
	//    public static int already [][] = new int[leny][lenx];

	public static int commandFound = 0;
	public static lens box[][] = new lens[256][30000];
	public static int boxes[] = new int[256];
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2023 Day15.2");
		out.flush();
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				//if (lenx == 0) {lenx = line.length();}
				blah.add(line);
				//leny++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));

		for (int qq = 0; qq < 256; qq++) {boxes[qq] = 0;}
		lens lens1 = new lens();
		for (int qq = 0; qq < 256; qq++) {
			for (int rr = 0; rr < 256; rr++) {
				box[qq][rr] = new lens();
			}
		}
		for (int i = 0; i < blah.size(); i++) {
			Scanner scanner = new Scanner(blah.get(i));
			scanner.useDelimiter("[,]");
			Vector <Integer> var_ints = new Vector<>();
			String tok = new String("");
			while (scanner.hasNext()) {
				int cur = 0;
				String ne = scanner.next();
				for (int ii = 0; ii < ne.length(); ii++) {
					if (ne.charAt(ii) == '-') {
						lens1.command = '-';
						tok = ne.substring(0, ii);
						commandFound = 1;
					} else if (ne.charAt(ii) == '=') {
						lens1.command = '=';
						tok = ne.substring(0, ii);
						commandFound = 1;
					}
					//out.println(tok);
					lens1.label = new String(tok);
					//out.println(lens1.label);
					if (commandFound==1 && lens1.command == '=') {
						lens1.focal = ne.charAt(ii+1) - 48;
						commandFound = 0;
					} else if (commandFound==1 && lens1.command == '-') {
						lens1.focal = -1;
						commandFound = 0;
					}

				}
				lens1.boxnumber = hashval(tok);
				out.print("boxnumber: "); out.println(lens1.boxnumber);
				if (lens1.command == '=') {
					//if not in box
					int pos = 0;
					int foundbox = 0;
					if (boxes[lens1.boxnumber] > 0) {
						for (int iii = 0; iii< boxes[lens1.boxnumber]; iii++) {
							if (box[lens1.boxnumber][iii].label.equals(lens1.label)) {
								foundbox = 1;
								pos = iii;
							}
						}
					}
					if (foundbox == 0) {
						out.print("lens1.label:"); out.println(lens1.label);
						out.println(lens1.boxnumber);
						out.println(boxes[lens1.boxnumber]);
						box[lens1.boxnumber][boxes[lens1.boxnumber]].label = new String(lens1.label);
						box[lens1.boxnumber][boxes[lens1.boxnumber]].command = lens1.command;
						box[lens1.boxnumber][boxes[lens1.boxnumber]].focal = lens1.focal;
						box[lens1.boxnumber][boxes[lens1.boxnumber]].boxnumber = lens1.boxnumber;
						boxes[lens1.boxnumber]++;
					} else {
						box[lens1.boxnumber][pos].focal = lens1.focal;

					}
				} else if (lens1.command == '-') {
					int pos = 0; int foundbox = 0;
					if (boxes[lens1.boxnumber] > 0) {
						for (int iii = 0; iii< boxes[lens1.boxnumber]; iii++) {
							if (box[lens1.boxnumber][iii].label.equals(lens1.label)) {
								foundbox = 1;
								pos = iii;
								break;
							}
						}
					}
					if (foundbox == 1) {
						for (int iii = pos; iii < boxes[lens1.boxnumber]; iii++) {
							if (iii+1 != boxes[lens1.boxnumber]) {
								box[lens1.boxnumber][iii].label = new String(box[lens1.boxnumber][iii+1].label);
								box[lens1.boxnumber][iii].command = box[lens1.boxnumber][iii+1].command;
								box[lens1.boxnumber][iii].focal = box[lens1.boxnumber][iii+1].focal;
								box[lens1.boxnumber][iii].boxnumber = box[lens1.boxnumber][iii+1].boxnumber;
							}
						}
						boxes[lens1.boxnumber]--;
					}
				}
			}
		}

		long tot = 0;
		for (int i = 0; i < 256; i++) {
			for (int j = 0; j < boxes[i]; j++) {
				tot += (i + 1) * (j+1) * box[i][j].focal;
			}

		}

		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(tot);
		out.println("");
	}
	public static int hashval(String token) {
		int ascV = 0;
		int ans = 0;
		for (int ii = 0; ii < token.length(); ii++) {
			ascV = (int)token.charAt(ii);
			ans = ((ans + ascV) * 17) % 256;
		}
		return ans;
	}
	public static class lens {
		String label;
		int focal;
		char command;
		int boxnumber;
	}
}


class Tuple<X,Y > {
	public X first;
	public Y second;

	public Tuple(X first, Y second) {
		this.first = first;
		this.second = second;
	}
	@Override
	public boolean equals(Object o) {
		Tuple tu2 = (Tuple) o;
		if (this == o) return true;
		if (!(o instanceof Tuple)) return false;
		if (!this.first.equals(tu2.first)) {return false;}
		if (!this.second.equals(tu2.second)) {return false;}

		return true;
	}
	@Override
	public int hashCode() {
		return Objects.hash(first, second);
	}

}

@SuppressWarnings("unchecked")
class TreTuple<X,Y, Z> {
	public X first;
	public Y second;
	public Z third;

	public TreTuple(Object o) {
		TreTuple tu2 = (TreTuple) o;
		this.first = (X)tu2.first;
		this.second = (Y)tu2.second;
		this.third = (Z)tu2.third;
	}
	public TreTuple(X first, Y second, Z third) {
		this.first = first;
		this.second = second;
		this.third = third;
	}
	@Override
	public boolean equals(Object o) {
		TreTuple tu2 = (TreTuple) o;
		if (this == o) return true;
		if (!(o instanceof TreTuple)) return false;
		if (!this.first.equals(tu2.first)) {return false;}
		if (!this.second.equals(tu2.second)) {return false;}
		if (!this.third.equals(tu2.third)) {return false;}
		return true;
	}
	@Override
	public int hashCode() {
		return Objects.hash(first, second, third);
	}

}

@SuppressWarnings("unchecked")
class QuadTuple<X,Y, Z, W> {
	public X first;
	public Y second;
	public Z third;
	public W fourth;

	public QuadTuple(X first, Y second, Z third, W fourth) {
		this.first = first;
		this.second = second;
		this.third = third;
		this.fourth = fourth;
	}
	@Override
	public boolean equals(Object o) {
		QuadTuple tu2 = (QuadTuple) o;
		if (this == o) return true;
		if (!(o instanceof QuadTuple)) return false;

		if (!first.equals(tu2.first)) {return false;}
		if (!second.equals(tu2.second)) {return false;}
		if (!third.equals(tu2.third)) {return false;}
		if (!fourth.equals(tu2.fourth)) {return false;}
		return true;
	}
	@Override
	public int hashCode() {
		return Objects.hash(first, second, third, fourth);
	}

}

@SuppressWarnings("unchecked")
class CinqTuple<X,Y, Z, V, W> {
	public X first;
	public Y second;
	public Z third;
	public V fourth;
	public W fifth;

	public CinqTuple(X first, Y second, Z third, V fourth, W fifth) {
		this.first = first;
		this.second = second;
		this.third = third;
		this.fourth = fourth;
		this.fifth = fifth;
	}
	@Override
	public boolean equals(Object o) {
		CinqTuple tu2 = (CinqTuple) o;
		if (this == o) return true;
		if (!(o instanceof CinqTuple)) return false;

		if (!first.equals(tu2.first)) {return false;}
		if (!second.equals(tu2.second)) {return false;}
		if (!third.equals(tu2.third)) {return false;}
		if (!fourth.equals(tu2.fourth)) {return false;}
		if (!fifth.equals(tu2.fifth)) {return false;}
		return true;
	}
	@Override
	public int hashCode() {
		return Objects.hash(first, second, third, fourth, fifth);
	}

}

