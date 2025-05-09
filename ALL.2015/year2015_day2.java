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

class year2015_day2 {
	public static void main(String [] args) {
		System.out.println("		2015 Day2.1");
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	

		Pattern p = Pattern.compile("(\\d+)x(\\d+)x(\\d+)");
	//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
	//	month= m.group(2);

		//BigInteger tot =  BigInteger.valueOf((long)0);
		int tot = 0;
		for (int i = 0; i < blah.size(); i++) {
			//System.out.println(blah.get(i));
			Matcher m = p.matcher(blah.get(i));
			m.find();
			int length = Integer.valueOf(m.group(1));
			//System.out.println(length);
			int width = Integer.valueOf(m.group(2));
			//System.out.println(width);
			int height = Integer.valueOf(m.group(3));
			///System.out.println(height);
			
			int side1 = length*width;
			int side2 = length*height;
			int side3 = width*height;
			int mtmp = 0;
			if (side1 <= side2) {
				mtmp = side1;
			} else {
				mtmp = side2;
			}
			if (side3 <= mtmp) {
				mtmp = side3;
			}

			//thisNum.add(sz.multiply(num));

			//tot = tot.add((2*length*height) + (2*length*width) + 
			//	(2*height*width) + mtmp);
			tot += (2*length*height) + (2*length*width) + (2*height*width) + mtmp;


		}
		System.out.print("**j_ans: ");
		System.out.println(tot);
					
	}
}
