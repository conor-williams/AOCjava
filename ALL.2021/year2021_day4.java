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
// /java -Xmx2g year2019_day3.java *i1.txt


class year2021_day4 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2021 Day4.1");
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	

		//	String firstpart = Pattern.quote("mul(");
		//Pattern p = Pattern.compile("(L|R)(\\d+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		int [][] board = new int[5][5];
		Vector <int [][]> boards = new Vector<>();
		Vector <Integer> moves = new Vector<>();

		int whichBoard = -1;
		int boardYPos = 0;
		for (int i = 0; i < blah.size(); i++) {
			if (i == 0) {
				Scanner scanner = new Scanner(blah.get(0));
				scanner.useDelimiter("[,]");
				while (scanner.hasNext()) {
					moves.add(Integer.valueOf(scanner.next()));
				}
				continue;
			}
			if (blah.get(i).length() == 0) {
				if (whichBoard != -1) {
					int[][] board2 = Arrays.stream(board).map(int[]::clone).toArray(int[][]::new);
					boards.add(board2);
				}
				whichBoard++;
				boardYPos = 0;
				continue;
			}

			Scanner scanner = new Scanner(blah.get(i));
			scanner.useDelimiter("[\\s]+");
			int ii = 0;
			while (scanner.hasNext()) {
				board[boardYPos][ii] = Integer.valueOf(scanner.next());
				//out.print(board[boardYPos][ii]); out.print(" ");
				ii++;
			}
			///out.println();
			boardYPos++;
		}
		int [][]board3 = Arrays.stream(board).map(int[]::clone).toArray(int[][]::new);
		boards.add(board3);

		//for (int ii = 0; ii < moves.size(); ii++) { out.print(moves.get(ii)); out.print(" "); } out.println("\n");

		//out.print("boards size: "); out.println(boards.size());

		for (int qq = 0; qq < moves.size(); qq++) {
			int zzz = moves.get(qq);
			for (int ii = 0; ii < boards.size(); ii++) {
				int [][] b4 = boards.get(ii);
				for (int yy = 0; yy < 5; yy++) {
					for (int xx = 0; xx < 5; xx++) {
						//out.print(b4[yy][xx]); out.print(" ");
						if (zzz == b4[yy][xx]) {
							b4[yy][xx] = -1;
						}
					}
				}
				for (int yy = 0; yy < 5; yy++) {
					int found = 0;
					for (int xx = 0; xx < 5; xx++) {
						if (b4[yy][xx] != -1) {
							found = 1;
						}
					}
					if (found == 0) {
						//out.println("horizontal"); out.println(zzz);
						int tot = 0;
						for (int yy1 = 0; yy1 < 5; yy1++) {
							for (int xx1 = 0; xx1 < 5; xx1++) {
								if (b4[yy1][xx1] != -1) {
									tot += b4[yy1][xx1];
								}
							}
						}
						out.print("**j_ans: ");
						out.print(tot*zzz);
						out.println("");

						Runtime.getRuntime().halt(0);
						//we have a winner horizontal
					}
				}

				
				for (int xx = 0; xx < 5; xx++) {
					int found = 0;
					for (int yy = 0; yy < 5; yy++) {
						if (b4[yy][xx] != -1) {
							found = 1;
						}
					}
					if (found == 0) {
						int tot = 0;
						for (int yy1 = 0; yy1 < 5; yy1++) {
							for (int xx1 = 0; xx1 < 5; xx1++) {
								if (b4[yy1][xx1] != -1) {
									tot += b4[yy1][xx1];
								}
							}
						}
						out.print("**j_ans: ");
						out.print(tot*zzz);
						out.println("");
						//out.println("vertical"); out.println(zzz);
						Runtime.getRuntime().halt(0);
						//we have a winner vertical
					}
				}
				boards.set(ii, b4);
			}
		}
	}
}

public class Tuple<X,Y > {
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

