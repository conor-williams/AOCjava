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


///tuples.sort(Comparator.comparingInt(tuple -> tuple[0]));
//Scanner scanner = new Scanner(System.in); scanner.nextLine();
// int max = var_ints.stream().max(Integer::compare).orElseThrow();
// int position = var_ints.indexOf(max);

//                        for (Map.Entry<Character, Vector<Character>> entry : mp.entrySet()) {
// System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//}

@SuppressWarnings("unchecked")
class year2018_day13_2 {
	public static int sx = 0;
	public static int sy = 0;
	public static char grid [][] = new char[sy][sx];
	//	        public static int maxPath = 0;
	//    public static int already [][] = new int[sy][sx];

	public static void main(String [] args) {
		out.println("		2018 Day13.2");
		out.flush();
		Vector<String> blah = new Vector<>();
		int leny = 0;
		int lenx = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (lenx == 0) {lenx = line.length();}
				blah.add(line);
				leny++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
		//		PrintStream originalOut = System.out;
		//		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));
		sx = lenx;
		sy = leny;
		grid = new char[sy][sx];
		//already = new int[sy][sx];
		for (int i = 0; i < blah.size();i++) {
			grid[i] = blah.get(i).toCharArray();
		}


		Vector <CinqTuple <Integer, Integer, Integer, Integer, Boolean>> ve = new Vector<>();

		for (int yy = 0; yy < leny; yy++) {
			for (int xx = 0; xx < lenx; xx++) {
				switch (grid[yy][xx]) {
					case '^':
						ve.add(new CinqTuple(xx, yy, 0, 0, true));
						grid[yy][xx] = '|';
						break;
					case '>':
						ve.add(new CinqTuple(xx, yy, 1, 0, true));
						grid[yy][xx] = '-';
						break;
					case 'v':
						ve.add(new CinqTuple(xx, yy, 2, 0, true));
						grid[yy][xx] = '|';
						break;
					case '<':
						ve.add(new CinqTuple(xx, yy, 3, 0, true));
						grid[yy][xx] = '-';
						break;
				}
			}
		}
		ve.sort(Comparator.comparingInt((CinqTuple t) -> (int)t.second).thenComparingInt((CinqTuple t) -> (int)t.first));
after:
		while (1 == 1) {
			int count2 = 0;
			for (int qqq = 0; qqq < ve.size(); qqq++) {
				var carX = ve.get(qqq);
				if (carX.fifth == true) {
					count2++;
				}
				if (count2 > 1) {break;}
			}
			if (count2 == 1) {break after;}
			for (int ii = 0; ii < ve.size(); ii++) {
				//int cr = 0;
after2:
				for (int jj = 0; jj < ve.size()-1; jj++) {
					var car1 = ve.get(jj);
					if (car1.fifth == false) {continue;}
					for (int kk = jj+1; kk < ve.size(); kk++) {
						var car2 = ve.get(kk);
						if (car2.fifth == false) {continue;}
						if (car1.first == car2.first && car1.second == car2.second) {
							car1.fifth = false;
							ve.set(jj, car1);

							car2.fifth = false;
							ve.set(kk, car2);

							/*
							int count = 0;
							for (int qqq = 0; qqq < ve.size(); qqq++) {
								var carX = ve.get(qqq);
								if (carX.fifth == true) {
									count++;
								}
								if (count > 1) {break;}
							}
							if (count == 1) {
								break after;
							} else {
		 						break after2;
							}
							*/
						}

					}
				}

				var t1 = ve.get(ii);
				if (t1.fifth == false) {continue;}

				int xx = t1.first;
				int yy = t1.second;
				switch (t1.third) {
					case 0:
						t1.second--;
						if (grid[yy-1][xx] == '|') {
						} else if (grid[yy-1][xx] == '\\') {
							t1.third = 3;
						} else if (grid[yy-1][xx] == '/') {
							t1.third = 1;
						} else if (grid[yy-1][xx] == '+') {
							switch (t1.fourth) {
								case 0:
									t1.third--;
									t1.third+=4;
									t1.third%=4;
									break;
								case 1:
									break;
								case 2:
									t1.third++;
									t1.third+=4;
									t1.third%=4;
									break;
								default:
									out.println("ERR1");
									break;

							}
							t1.fourth++;
							t1.fourth %= 3;
						} else {
							out.println("ERR11");
						}

						break;
					case 1:
						t1.first++;
						if (grid[yy][xx+1] == '-') {
						} else if (grid[yy][xx+1] == '\\') {
							t1.third = 2;
						} else if (grid[yy][xx+1] == '/') {
							t1.third = 0;
						} else if (grid[yy][xx+1] == '+') {
							switch (t1.fourth) {
								case 0:
									t1.third--;
									t1.third+=4;
									t1.third%=4;
									break;
								case 1:
									break;
								case 2:
									t1.third++;
									t1.third+=4;
									t1.third%=4;
									break;
								default:
									out.println("ERR2");
									break;
							}
							t1.fourth++;
							t1.fourth %= 3;
						} else {
							out.println("ERR22");
							out.println(grid[yy][xx+1]);
						}
						break;
					case 2:
						t1.second++;
						if (grid[yy+1][xx] == '|') {
						} else if (grid[yy+1][xx] == '\\') {
							t1.third = 1;
						} else if (grid[yy+1][xx] == '/') {
							t1.third = 3;
						} else if (grid[yy+1][xx] == '+') {
							switch (t1.fourth) {
								case 0:
									t1.third--;
									t1.third+=4;
									t1.third%=4;
									break;
								case 1:
									break;
								case 2:
									t1.third++;
									t1.third+=4;
									t1.third%=4;
									break;
								default:
									out.println("ERR3");
									break;
							}
							t1.fourth++;
							t1.fourth %= 3;
						} else {
							out.println("ERR33");
						}
						break;
					case 3:
						t1.first--;
						if (grid[yy][xx-1] == '-') {
						} else if (grid[yy][xx-1] == '\\') {
							t1.third = 0;
						} else if (grid[yy][xx-1] == '/') {
							t1.third = 2;
						} else if (grid[yy][xx-1] == '+') {
							switch (t1.fourth) {
								case 0:
									t1.third--;
									t1.third+=4;
									t1.third%=4;
									break;
								case 1:
									break;
								case 2:
									t1.third++;
									t1.third+=4;
									t1.third%=4;
									break;
								default:
									out.println("ERR4");
									break;
							}
							t1.fourth++;
							t1.fourth %= 3;
						} else {
							out.println("ERR44");
						}

						break;
					default:
						out.println("ERR5");
						break;
				}
				ve.set(ii, t1);
			}
			ve.sort(Comparator.comparingInt((CinqTuple t) -> (int)t.second).thenComparingInt((CinqTuple t) -> (int)t.first));
		}

		int pos1 = 0;
		for (int ii = 0; ii < ve.size(); ii++) {
			if (ve.get(ii).fifth == true) {
				pos1 = ii;
			}
		}


		//		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(ve.get(pos1).first);
		out.print(",");
		out.print(ve.get(pos1).second);
		out.println("");
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
class CinqTuple<X,Y, Z, W, V> {
	public X first;
	public Y second;
	public Z third;
	public W fourth;
	public V fifth;

	public CinqTuple(Object o) {
		CinqTuple tu2 = (CinqTuple) o;
		this.first = (X)tu2.first;
		this.second = (Y)tu2.second;
		this.third = (Z)tu2.third;
		this.fourth = (W)tu2.fourth;
		this.fifth = (V)tu2.fifth;
	}
	public CinqTuple(X first, Y second, Z third, W fourth, V fifth) {
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
		if (!this.first.equals(tu2.first)) {return false;}
		if (!this.second.equals(tu2.second)) {return false;}
		if (!this.third.equals(tu2.third)) {return false;}
		if (!this.fourth.equals(tu2.fourth)) {return false;}
		if (!this.fifth.equals(tu2.fifth)) {return false;}
		return true;
	}
	@Override
	public int hashCode() {
		return Objects.hash(first, second, third, fourth, fifth);
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

