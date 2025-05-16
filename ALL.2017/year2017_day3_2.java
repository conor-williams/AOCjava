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


class year2017_day3_2 {
	public static void main(String [] args) {
		out.println("		2017 Day3.2");
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
		int sz = 30;
		int [][] grid = new int[sz][sz];
		int x = sz/2;
		int y = sz/2;
		for (int i = 0; i < blah.size(); i++) {
			int END = Integer.valueOf(blah.get(i));

			grid[y][x] = 1;
			grid[y][x+1] = 1;
			grid[y-1][x+1] = 2;
			grid[y-1][x] = 4;
			grid[y-1][x-1] = 5;
			grid[y][x-1] = 10;
			grid[y+1][x-1] = 11;
			grid[y+1][x] = 23;
			grid[y+1][x+1] = 25;
			grid[y+1][x+2] = 26;

			x = x+2;
			y = y+1;
			do {
				if        (checkGrid(grid, x, y, 1, 1, 0, 0, 0, 0, 0, 1)) { //btmright
					x++;
					int v = doCalc(grid, x, y);
					grid[y][x] = v;
				} else if (checkGrid(grid, x, y, 0, 0, 0, 0, 0, 1, 1, 0)) { //top right
					x--;
					int v = doCalc(grid, x, y);
					grid[y][x] = v;
				} else if (checkGrid(grid, x, y, 0, 0, 0, 1, 1, 0, 0, 0)) { //top left
					y++;
					int v = doCalc(grid, x, y);
					grid[y][x] = v;
				} else if (checkGrid(grid, x, y, 0, 1, 1, 0, 0, 0, 0, 0)) { //btm left
					x++;
					int v = doCalc(grid, x, y);
					grid[y][x] = v;
				} else if (checkGrid(grid, x, y, 1, 0, 0, 0, 0, 0, 0, 1)) { //26 
					y--;
					int v = doCalc(grid, x, y);
					grid[y][x] = v;
				} else if (checkGrid(grid, x, y, 1, 0, 0, 0, 0, 1, 1, 1)) { //54
					y--;
					int v = doCalc(grid, x, y);
					grid[y][x] = v;
				} else if (checkGrid(grid, x, y, 0, 0, 0, 0, 0, 1, 1, 1)) { //57
					y--;
					int v = doCalc(grid, x, y);
					grid[y][x] = v;
				} else if (checkGrid(grid, x, y, 0, 0, 0, 1, 1, 1, 1, 0)) { //122 and 133
					x--;
					int v = doCalc(grid, x, y);
					grid[y][x] = v;
				} else if (checkGrid(grid, x, y, 0, 0, 0, 1, 1, 1, 0, 0)) { //142
					x--;
					int v = doCalc(grid, x, y);
					grid[y][x] = v;
				} else if (checkGrid(grid, x, y, 0, 1, 1, 1, 1, 0, 0, 0)) { //304 and 330
					y++;
					int v = doCalc(grid, x, y);
					grid[y][x] = v;
				} else if (checkGrid(grid, x, y, 0, 1, 1, 1, 0, 0, 0, 0)) { //351
					y++;
					int v = doCalc(grid, x, y);
					grid[y][x] = v;
				} else if (checkGrid(grid, x, y, 1, 1, 1, 0, 0, 0, 0, 1)) { //747 and 806 and ne
					x++;
					int v = doCalc(grid, x, y);
					grid[y][x] = v;
				}


			} while (grid[y][x] < END);
			//out.println(grid[y][x]);
		}
		out.print("**j_ans: ");
		out.print(grid[y][x]);
		out.println("");
	}
	public static boolean checkGrid(int [][] grid, int x, int y, int one, int two, int three, int four, int five, int six, int seven, int eight) {
		
		int zone = 1, ztwo = 1, zthree = 1, zfour = 1, zfive = 1;
		int zsix = 1, zseven = 1, zeight = 1;
		if (grid[y-1][x-1] == 0) {zone = 0; }
		if (grid[y-1][x] == 0) {ztwo = 0;}
		if (grid[y-1][x+1] == 0) {zthree = 0;}
		if (grid[y][x+1] == 0) {zfour = 0;}
		if (grid[y+1][x+1] == 0) {zfive = 0;}
		if (grid[y+1][x] == 0) {zsix = 0;}
		if (grid[y+1][x-1] == 0) {zseven = 0;} 
		if (grid[y][x-1] == 0) {zeight = 0;}

		if (zone == one && ztwo == two && zthree == three && zfour == four && zfive == five &&
				zsix == six && zseven == seven && zeight == eight) {
			return true;
		} else {
			return false;
		}
	}
	public static int doCalc(int [][] grid, int x, int y) {

		int val =  grid[y-1][x-1] + grid[y-1][x] + grid[y-1][x+1] + grid[y][x+1] + grid[y+1][x+1] +
			grid[y+1][x] + grid[y+1][x-1] + grid[y][x-1];
		//out.print(val); out.print(" << val >> x "); out.print(x); out.print(" << y "); out.println(y);
		return val;
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

