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
class year2023_day16_2 {
	//	        public static int maxPath = 0;
	public static int lenx = 0;
	public static int leny = 0;
	public static char puzzle [][] = new char[leny][lenx];
	public static char puzzleCopy [][] = new char[leny][lenx];
	//public static inst myinstarray[] = new inst[150000];
	public static Vector <inst> myinstarray = new Vector<>();
	//public static int myinstarrayLen;
	public static int tot;

	//    public static int already [][] = new int[leny][lenx];

	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2023 Day16.2");
		out.println("	SLOW ~2:30");
		out.flush();
		Vector<String> blah = new Vector<>();
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
		puzzle = new char[leny][lenx];
		puzzleCopy = new char[leny][lenx];
		for (int i = 0; i < blah.size();i++) {
			puzzle[i] = blah.get(i).toCharArray();
			puzzleCopy[i] = blah.get(i).toCharArray();
		}
		/*
		   myinstarrayLen = 0;
		   for (int ii = 0; ii < 150000; ii++) {
		   myinstarray[ii] = new inst();
		   }
		   */
		tot = 0;
		int ret123 = doit(3, 0, 2);
		int maxtot = 0;
		int ret;
		for (int x = 0; x < lenx; x++) {
			int y = 0;
			int dir = 2;
			ret = doit(x, y, dir);
			if (ret > maxtot) {maxtot = ret;}
		}
		for (int x = 0; x < lenx; x++) {
			int y = leny-1;
			int dir = 0;
			ret = doit(x, y, dir);
			if (ret > maxtot) {maxtot = ret;}
		}

		//left row x is 0 // dir right = 1
		for (int y = 0; y < leny; y++)
		{

			int x = 0;
			int dir = 1;
			ret = doit(x, y, dir);
			if (ret > maxtot) {maxtot = ret;}

		}
		//right row // dir = 3
		for (int y = 0; y < leny; y++)
		{
			int x = lenx - 1;
			int dir = 3;
			ret = doit(x, y, dir);
			if (ret > maxtot) {maxtot = ret;}
		}

		//
		//		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(maxtot);
		out.println("");
	}

	public static class inst {
		int stop;
		int x;
		int y;
		int direction;

		public inst(int stop, int x, int y, int direction) {
			this.stop = stop;
			this.x = x;
			this.y = y;
			this.direction = direction;
		}
		public inst() {
			this.stop = 0;
			this.x = 0;
			this.y = 0;
			this.direction = 0;
		}
		public inst(inst other) {
			this.stop = other.stop;
			this.x = other.x;
			this.y = other.y;
			this.direction = other.direction;

		}
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (!(o instanceof inst)) return false;
			inst in2 = (inst)o;
			if (this.stop != in2.stop) {return false;}
			if (this.x != in2.x) {return false;}
			if (this.y != in2.y) {return false;}
			if (this.direction != in2.direction) {return false;}

			return true;
		}
		//public int hashCode() {
		//	return Objects.hash(stop, x, y, direction);
		//}

	}

	public static int doit(int x, int y, int direction) {
		tot = 0;
		//myinstarrayLen = 0;
		myinstarray.clear();
		puzzleCopy = Arrays.stream(puzzle).map(char[]::clone).toArray(char[][]::new);

		{
			///first MOVE
			inst amyinst = new inst();
			amyinst.direction = direction;
			amyinst.x = x;
			amyinst.y = y;
			amyinst.stop = 0;
			amyinst = mymove(amyinst);
		}

		tot = 0;

		for (int y2 = 0; y2 < leny; y2++) {
			for (int x2 = 0; x2 < lenx; x2++) {
				if (puzzleCopy[y2][x2] == 'Q') {
					tot++;
				}
			}
		}
		return tot;
	}
	public static void saveit(inst myinst) {
		if (!myinstarray.contains(myinst)) {
			myinstarray.add(new inst(myinst));
			//out.println("saving");
		} else {
			//out.println("not saving");
		}
		/*
		   int found = 0;
		   for (int i = 0; i < myinstarrayLen; i++) {
		   if (myinst.stop == myinstarray[i].stop
		   && myinst.x == myinstarray[i].x
		   && myinst.y == myinstarray[i].y
		   && myinst.direction == myinstarray[i].direction) {
		   found = 1;
		   break;
		   }
		   }
		   if (found == 0) {
		   myinstarray[myinstarrayLen].stop = myinst.stop;
		   myinstarray[myinstarrayLen].x = myinst.x;
		   myinstarray[myinstarrayLen].y = myinst.y;
		   myinstarray[myinstarrayLen].direction = myinst.direction;
		   myinstarrayLen++;
		   }
		   */
	}
	public static int isthereP(inst myinst)
	{
		//int found = 0;
		if (myinstarray.contains(myinst)) {
			//out.println("contains");
			return 1;
		} else {
			//out.println("not contains");
			//out.println(myinstarray.size());
			return 0;
		}
		/*
		   for (int i = 0; i < myinstarrayLen; i++) {
		   if (myinst.stop == myinstarray[i].stop
		   && myinst.x == myinstarray[i].x
		   && myinst.y == myinstarray[i].y
		   && myinst.direction == myinstarray[i].direction) {
		   found = 1;
		   break;
		   }
		   }
		   return found;
		   */
	}

	public static inst mymove(inst myinst)
	{
		myinst.direction = myinst.direction % 4;
		if (myinst.stop == 0) {
			puzzleCopy[myinst.y][myinst.x] = 'Q';

			switch (puzzle[myinst.y][myinst.x]) {
				case '|':
					if (myinst.direction == 0 || myinst.direction == 2) {
					} else if (myinst.direction == 1 || myinst.direction == 3) {
						{
							inst myinst2 = new inst();;
							myinst2.stop = 0;
							myinst2.x = myinst.x;
							myinst2.y = myinst.y;
							myinst2.direction = 2;
							myinst2 = getNextXY(myinst2); 
						}
						myinst.direction = 0;
					}
					break;
				case '-':
					if (myinst.direction == 1 || myinst.direction == 3) {
					} else if (myinst.direction == 0 || myinst.direction == 2) {
						{
							inst myinst3 = new inst();
							myinst3.stop = 0;
							myinst3.x = myinst.x;
							myinst3.y = myinst.y;
							myinst3.direction = 1;
							myinst3 = getNextXY(myinst3);
						}
						myinst.direction = 3;
					}
					break;
				case '\\':
					if (myinst.direction == 2) {
						myinst.direction = 1;
					} else if (myinst.direction == 3) {
						myinst.direction = 0;
					} else if (myinst.direction == 0) {
						myinst.direction = 3;
					} else if (myinst.direction == 1) {
						myinst.direction = 2;
					}
					break;
				case '/':
					if (myinst.direction == 2) {
						myinst.direction = 3;
					} else if (myinst.direction == 3) {
						myinst.direction = 2;
					} else if (myinst.direction == 0) {
						myinst.direction = 1;
					} else if (myinst.direction == 1) {
						myinst.direction = 0;
					}
					break;
				case '.':
					break;
				default:
					out.println("ERR1");
					break;
			}
			myinst = getNextXY(myinst); //CONOR
		} else {
		}
		return myinst;
	}

	public static inst getNextXY(inst myinst)
	{
		if (isthereP(myinst) != 1) {saveit(myinst);} else {myinst.stop = 1;}
		if (myinst.stop != 1) {
			switch (myinst.direction) {
				case 0:
					if (myinst.y -1 == -1) {myinst.stop = 1;} else {myinst.y--;}
					break;
				case 1:
					if (myinst.x + 1== lenx) {myinst.stop = 1;} else {myinst.x++;}
					break;
				case 2:
					if (myinst.y + 1 == leny) {myinst.stop = 1;} else {myinst.y++;}
					break;
				case 3:
					if (myinst.x - 1 == -1) {myinst.stop = 1;} else {myinst.x--;}
					break;
				default:
					out.println("ERR2");
					break;

			}
			myinst = mymove(myinst);
		}
		return myinst;
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

