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
class year2021_day21_2 {
	//	        public static int maxPath = 0;
	//    public static int lenx = 0;
	//   public static int leny = 0;
	//  public static char grid [][] = new char[leny][lenx];
	//    public static int already [][] = new int[leny][lenx];

	static int startP1 = 0;
	static int startP2 = 0;
	static class State {
		int p1_score;
		int p2_score;
		int p1_place;
		int p2_place;
		boolean p1_turn;
		@Override
		public boolean equals(Object o) {
			State tu2 = (State) o;
			if (this == o) return true;
			if (!(o instanceof State)) return false;

			if (p1_score != tu2.p1_score) {return false;}
			if (p2_score != tu2.p2_score) {return false;}
			if (p1_place != tu2.p1_place) {return false;}
			if (p2_place != tu2.p2_place) {return false;}
			if (p1_turn != tu2.p1_turn) {return false;}
			return true;
		}
		@Override
		public int hashCode() {
			return Objects.hash(p1_score, p2_score, p1_place, p2_place, p1_turn);
		}

	}
	static Map <State, Tuple<Long, Long>> cache = new HashMap<>();
	static int ar[] = new int[10];

	public static void main(String [] args) {
		out.println("		2021 Day21.2");
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

		//	String firstpart = Pattern.quote("mul(");
		Pattern p1 = Pattern.compile("Player (\\d+) starting position: (\\d+)");
		for (int i = 0; i < blah.size(); i++) {
			Matcher m1 = p1.matcher(blah.get(i));
			m1.find();

			if (m1.group(1).equals("1")) {
				startP1 = Integer.valueOf(m1.group(2));
			} else {
				startP2 = Integer.valueOf(m1.group(2));
			}
		}
		//
		var pa = take_turn(0, 0, startP1, startP2, true);
		var result = pa.first > pa.second ? pa.first : pa.second;
		//		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(result);
		out.println("");
	}

	static Tuple <Long, Long> take_turn(int scoreP1, int scoreP2, int posP1, int posP2, boolean turnP1) {
		if (scoreP1 >= 21) {
			return new Tuple(1L, 0L);
		} else if (scoreP2 >= 21) {
			return new Tuple(0L, 1L);
		}
		//State current {scoreP1, scoreP2, posP1, posP2, turnP1};
		State current = new State();
		current.p1_score = scoreP1; current.p2_score = scoreP2; current.p1_place = posP1;
		current.p2_place = posP2; current.p1_turn = turnP1;

		if (cache.get(current) != null) {
			return cache.get(current);
		}

		long totP1 = 0L;
		long totP2 = 0L;

		for (int roll1 = 1; roll1 <= 3; ++roll1) {
			for (int roll2 = 1; roll2 <= 3; ++roll2) {
				for (int roll3 = 1; roll3 <= 3; ++roll3) {
					int ii = roll1 + roll2 + roll3;
					if (turnP1) {
						int amt = ii;
						int newposP1 = ((posP1 - 1 + amt) % 10) + 1;
						int newScoreP1 = scoreP1 + newposP1;
						var res = take_turn(newScoreP1, scoreP2, newposP1, posP2, !turnP1);
						totP1 += res.first;
						totP2 += res.second;
					} else {
						int amt = ii;
						int newposP2 = ((posP2 - 1 + amt) % 10) + 1;
						int newScoreP2 = scoreP2 + newposP2;
						var res = take_turn(scoreP1, newScoreP2, posP1, newposP2, !turnP1);
						totP1 += Long.valueOf(res.first);
						totP2 += Long.valueOf(res.second);
					}
				}
			}
		}

		cache.put(current, new Tuple(totP1, totP2));

		return cache.get(current);
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

