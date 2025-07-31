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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Collectors;

// /java -Xmx2g year2019_day3.java *i1.txt



//                        grid = Arrays.stream(gridTmp).map(char[]::clone).toArray(char[][]::new);
// pe.sort(Comparator.comparingInt((TreTuple t) -> (int)t.third).thenComparingInt((TreTuple t) -> (int)t.second).thenComparingInt((TreTuple t) -> (int)t.first));///tuples.sort(Comparator.comparingInt(tuple -> tuple[0]));
//Scanner scanner = new Scanner(System.in); scanner.nextLine();
// int max = var_ints.stream().max(Integer::compare).orElseThrow();
// int position = var_ints.indexOf(max);

//                        for (var entry : mp.entrySet()) {
// System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//}
//// Arrays.stream(array).forEach(row -> Arrays.fill(row, 0));
@SuppressWarnings("unchecked")
class year2020_day22_2 {
	static int MAX_ROUNDS = 600;
	static int MAXIND = 500;
	static Deque<Integer> player1[] = new ArrayDeque[MAXIND];
	static Deque<Integer> player2[] = new ArrayDeque[MAXIND];
	static Set <Tuple <Deque<Integer>, Deque<Integer>>> mp[] = new HashSet[MAXIND];

	public static void main(String [] args) {
		out.println("		2020 Day22.2");
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
		//		PrintStream originalOut = System.out;
		//		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));
		for (int ii = 0; ii < MAXIND; ii++) {
			player1[ii] = new ArrayDeque<>();
			player2[ii] = new ArrayDeque<>();
			mp[ii] = new HashSet<>();
		}


		//	String firstpart = Pattern.quote("mul(");
		Pattern p = Pattern.compile("Player (\\d):");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		//BigInteger one =  new BigInteger("1");
		//BigInteger zero =  new BigInteger("0");
		int p2 = 0;
		for (int i = 0; i < blah.size(); i++) {
			if (blah.get(i).length() == 0) {continue;}
			Matcher m = p.matcher(blah.get(i));
			if (m.find()) {
				if (Integer.valueOf(m.group(1)) == 1) {
					p2 = 0;
				} else {
					p2 = 1;
				}
				continue;
			}
			if (p2 == 0) {
				player1[0].addLast(Integer.valueOf(blah.get(i)));
			} else {
				player2[0].addLast(Integer.valueOf(blah.get(i)));
			}

		}
		//out.println(player1[0].size()); out.println(player2[0].size());
		var pa = game();
		//		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(pa.second);
		out.println("");
	}
	/*
	static int save(Deque <Integer> player1, Deque <Integer> player2) {
		var tu = new Tuple(player1, player2);
		if (!mp[ind].contains(tu)) {
			mp[ind].add(tu);
			return 0;
		} else {
			return 1;
		}
	}
	*/
	static int ind = -1;
	static Tuple<Integer, Integer> game() {
		ind++;
		int round = 0;
		int x1 = 0, x2 = 0;
		Tuple <Integer, Integer> res = new Tuple(0, 0);
		res.first = -1;
after:
		while (true) {
			if (round > MAX_ROUNDS) {res.first = 22; break after;}
			res.first = -1;
			if (player1[ind].size() == 0 || player2[ind].size() == 0) { break; }
			x1 = player1[ind].getFirst(); x2 = player2[ind].getFirst();
			player1[ind].removeFirst();
			player2[ind].removeFirst();
			if (player1[ind].size() >= x1 && player2[ind].size() >= x2) {
				ArrayList<Integer> al = new ArrayList<>(player1[ind]);
				player1[ind+1].clear();
				for (var el: al.subList(0, x1)) {
					player1[ind+1].addLast(el);
				}
				ArrayList<Integer> al2 = new ArrayList<>(player2[ind]);
				player2[ind+1].clear();
				for (var el2: al2.subList(0, x2)) {
					player2[ind+1].addLast(el2);
				}
				res = game();
			}


			if (res.first == 1 || (x1 > x2 && res.first == -1)) {
				player1[ind].addLast(x1);
				player1[ind].addLast(x2);
			} else if (res.first == 2 || (x1 < x2 && res.first == -1)) {
				player2[ind].addLast(x2);
				player2[ind].addLast(x1);
			} else {
				out.println("ERR2");
			}

			round++;
		}

		if (res.first == 22 || player1[ind].size() != 0) {
			Tuple <Integer, Integer> pa = new Tuple(0, 0);
			pa.first = 1;
			if (ind == 0) {
				pa.second = score(player1[0]);
				return pa;
			} else {
				pa.second = 0;
				ind--;
				return pa;
			}
		} else {
			Tuple <Integer, Integer> pa = new Tuple(0, 0);
			pa.first = 2;
			if (ind == 0) {
				pa.second = score(player2[0]);
				return pa;
			} else {
				pa.second = 0;
				ind--;
				return pa;
			}

		}
	}


	static int score(Deque<Integer> pl) {
		int sc = 0;
		int size = pl.size();
		for (int i = 0; i < size; i++) {
			int val = pl.getLast();
			sc += (val * (i+1));
			pl.removeLast();
		}
		return sc;
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

