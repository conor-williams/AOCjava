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
class year2020_day16_2 {
	//	        public static int maxPath = 0;
	//    public static int lenx = 0;
	public static int leny = 0;
	//  public static char grid [][] = new char[leny][lenx];
	//    public static int already [][] = new int[leny][lenx];

	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static what_s what[] = new what_s[100];
	public static int whatPos = 0;
	public static int lists[][] = new int[400][200];
	public static int mine[] = new int[200];
	public static void main(String [] args) {
		out.println("		2020 Day16.2");
		out.flush();
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				//if (lenx == 0) {lenx = line.length();}
				blah.add(line);
				leny++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));

		//	String firstpart = Pattern.quote("mul(");
		Pattern p = Pattern.compile("([a-z ]+): (\\d+)-(\\d+) or (\\d+)-(\\d+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		//BigInteger one =  new BigInteger("1");
		//BigInteger zero =  new BigInteger("0");
		for (int ii = 0; ii < 100; ii++) {
			what[ii] = new what_s();
		}
		int fields = 1;
		int yourticket = 0;
		int nearbyticket = 0;
		int listPos = 0;
		String yours;
		int ticket = 0;

		for (int i = 0; i < blah.size(); i++) {
			if (fields == 1 && blah.get(i).length() == 0) {
				fields = 0;
				yourticket = 1;
				continue;
			}
			if (fields == 1) {
				//char field[100];
				//char frm1[20], to1[20], frm2[20], to2[20];
				String field_1;
				String frm1_1, to1_1, frm2_1, to2_1;
				Matcher m = p.matcher(blah.get(i));
				m.find();
				field_1 = m.group(1);
				frm1_1 = m.group(2);
				to1_1 = m.group(3);
				frm2_1 = m.group(4);
				to2_1 = m.group(5);

				//sscanf(line1, "%[^:]: %[^-]-%[^ ] or %[^-]-%[^\n]\n", field, frm1, to1, frm2, to2);
				what[whatPos].field = new String(field_1);
				what[whatPos].frm1 = Integer.valueOf(frm1_1);
				what[whatPos].to1 = Integer.valueOf(to1_1);
				what[whatPos].frm2 = Integer.valueOf(frm2_1);
				what[whatPos].to2 = Integer.valueOf(to2_1);
				whatPos++;
				continue;
			}
			if (yourticket == 1) {
				if (blah.get(i).charAt(0) == 'y' && blah.get(i).charAt(1) == 'o') {
					continue;
				} else {
					//strcpy(yours, line1);
					yours = new String(blah.get(i));
					listPos = 0;

					Scanner scanner = new Scanner(blah.get(i));
					scanner.useDelimiter(",");

					while (scanner.hasNext()) {
						int var_num1 = Integer.valueOf(scanner.next());
						mine[listPos] = var_num1;
						lists[ticket][listPos++] = var_num1;
					}
					ticket++;
					yourticket = 0;
					nearbyticket = 1;
					continue;
				}
			}
			if (nearbyticket == 1 && blah.get(i).length() == 0) {
				continue;
			} else if (nearbyticket == 1) {
				if (blah.get(i).charAt(0) == 'n' && blah.get(i).charAt(1) == 'e') {
					continue;
				} else {
					listPos = 0;
					Scanner scanner = new Scanner(blah.get(i));
					scanner.useDelimiter(",");

					while (scanner.hasNext()) {
						lists[ticket][listPos++] = Integer.valueOf(scanner.next());
					}
					ticket++;
					continue;
				}

			}
			leny++;
		}
		int notsum = 0;
		int ti = ticket;
		Vector <Integer> bad_tickets = new Vector<>();
		for (int i = 0; i < ticket; i++) {//nearbytickets
			for (int j = 0; j < listPos; j++) {//within each ticket
				int found = 0;
				for (int k = 0; k < whatPos; k++) {//class row etc...
					if ((lists[i][j] >= what[k].frm1 && lists[i][j] <= what[k].to1) || (lists[i][j] >= what[k].frm2 && lists[i][j] <= what[k].to2)) {
						found = 1;
						break;
					}
				}
				if (found == 0) {
					notsum+=lists[i][j];
					lists[i][0] = -1;
					bad_tickets.add(i);
				} else {
				}
			}
		}

		Vector <Integer> vec [][] = new Vector[500][25];
		for (int ii = 0; ii < 500; ii++) {
			for (int jj = 0; jj < 25; jj++) {
				vec[ii][jj] = new Vector<>();
			}
		}
		for (int i = 0; i < ticket; i++) {//nearbytickets
			if (bad_tickets.indexOf(i) != -1) {continue;}
			for (int j = 0; j < listPos; j++) {//within each ticket
				if (lists[i][0] == -1) {continue;}
				for (int k = 0; k < whatPos; k++) {//class row etc...
					if ((lists[i][j] >= what[k].frm1 && lists[i][j] <= what[k].to1) || (lists[i][j] >= what[k].frm2 && lists[i][j] <= what[k].to2)) {
						vec[i][j].add(k);
					}
				}
			}
		}


		for (int ii = 0; ii < ticket; ii++) {
			if (bad_tickets.indexOf(ii) != -1) {continue;}
			for (int kk = 1; kk < ticket; kk++) {
				if (bad_tickets.indexOf(kk) != -1) {continue;}
				for (int jj = 0; jj < listPos; jj++) {
					var v1 = new Vector(vec[ii][jj]);
					var v2 = new Vector(vec[kk][jj]);
					//Vector <Integer> v3 = v1.retainAll(v2);
					v1.retainAll(v2);

					/*
					   sort(v1.begin(), v1.end());
					   sort(v2.begin(), v2.end());
					   set_intersection(v1.begin(), v1.end(), v2.begin(), v2.end(), back_inserter(v3));
					   */
					vec[kk][jj] = new Vector(v1);
				}
			}
		}

		Vector <Integer> done = new Vector<>();

		int answer[] = new int[30];
		int go;
		do {
			go = 0;
			for (int jj = 0; jj < listPos; jj++) {
				if ((int)vec[ti-1][jj].size() == 1) {
					out.println("size 1...");
					int removeInt = vec[ti-1][jj].get(0);
					out.println(removeInt);
					done.add(removeInt);
					answer[jj] = removeInt;
					for (int kk = 0; kk < listPos; kk++) {
						int ind1;
						if ((ind1 = vec[ti-1][kk].indexOf(removeInt)) != -1) {
							vec[ti-1][kk].remove(ind1);
							//break;
						}
						/*
						   for (int zzz = 0; zzz < (int)vec[ti-1][kk].size(); zzz++) {
						   if (vec[ti-1][kk].get(zzz) == removeInt) {
						   break;
						   }
						   }
						   */
					}
				}
			}
			for (int jj = 0; jj < listPos; jj++) {
				if (vec[ti-1][jj].size() != 0) {go = 1; break;}
			}
			//out.println("in while...");
		} while (go == 1);


		long ans1 = 1;
		for (int ii = 0; ii < listPos; ii++) {
			out.print(what[answer[ii]].field); out.print(":"); out.println(mine[ii]);
			if (what[answer[ii]].field.charAt(0) == 'd' && what[answer[ii]].field.charAt(1) == 'e' && what[answer[ii]].field.charAt(2) == 'p') {
				out.println(what[answer[ii]].field);
				ans1 *= (mine[ii]);
			}
		}


		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(ans1);
		out.println("");
	}
	public static class what_s {
		String field;
		int frm1;
		int to1;
		int frm2;
		int to2;
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

