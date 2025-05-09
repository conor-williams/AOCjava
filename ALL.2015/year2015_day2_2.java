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

class year2015_day1_2 {
	public static void main(String [] args) {
		System.out.println("		2015 Day2.2");
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
		int tot = 0;
		for (int i = 0; i < blah.size(); i++) {
			//System.out.println(blah.get(i));
			Matcher m = p.matcher(blah.get(i));
			m.find();
			int length = Integer.valueOf(m.group(1));
			int width = Integer.valueOf(m.group(2));
			int height = Integer.valueOf(m.group(3));
			
			int ar[] = {length, width, height};
			Arrays.sort(ar);

			tot += ar[0]+ar[0]+ar[1]+ar[1] + (length*width*height);


		}
		System.out.print("**j_ans: ");
		System.out.println(tot);
					
	}
}
