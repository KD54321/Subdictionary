import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Part1 {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		Scanner sc = null;
		PrintWriter pw = null;
		boolean valid;
		int numentries = 0;

		String num[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		String alphabet[] = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
				"S", "T", "U", "V", "W", "X", "Y", "Z" };

		ArrayList<String> list = new ArrayList<>();
		ArrayList<String> reallist = new ArrayList<>();

		String Filename;
		System.out.print("Please enter a file name: ");
		Filename = keyboard.nextLine();
		try {
			sc = new Scanner(new FileInputStream(Filename));

			while (sc.hasNext()) {
				list.add(sc.next());
			}
			// checks if word is single character
			for (String s : list) {
				valid = true;
				if (s.length() == 1) {
					if (!(s.equals("a") || s.equals("A") || s.equals("i") || s.equals("I"))) {
						valid = false;
					}
				}
				// checks if word contains "'" and splits it
				if (s.contains("’")) {
					s = s.substring(0, s.indexOf("’"));
				}

				// checks if word contains "." "," "?" "!" ":" ";"
				if (s.endsWith("?") || s.endsWith(".") || s.endsWith(",") || s.endsWith("!") || s.endsWith(":")
						|| s.endsWith(";")) {
					s = s.substring(0, s.length() - 1);
				}
				// checks if word contains numbers
				for (String numbers : num) {
					if (s.contains(numbers)) {
						valid = false;
						break;
					}
				}
				// checks for duplicate words
				for (String words : reallist) {
					if (words.equals(s.toUpperCase())) {
						valid = false;
						break;
					}
				}

				// adds string to list if valid
				if (valid) {
					reallist.add(s.toUpperCase());
				}
			}
			reallist.sort(String.CASE_INSENSITIVE_ORDER);
			numentries = (reallist.size());
			
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("The file " + Filename + " was not found.");
		}
		//creating subdictionary file
		try {
			pw = new PrintWriter(new FileOutputStream("SubDictionary.txt"));
			pw.println("The document produced this sub-dictionary, which includes " + numentries + " entries.\n");

			int i = 0;
			int j = 0;
			boolean done = false;
			boolean found;

			while (!done) {
				do {
					found = false;
					if (reallist.get(i).startsWith(alphabet[j])) {
						//prints the 1st letter if word with that letter is found
						pw.println("\n"+alphabet[j] + "\n==");
						found = true;
					} else {
						j++;
					}
				} while (!found);

				//writes all words starting with the letter
				while (reallist.get(i).startsWith(alphabet[j])) {
					pw.println(reallist.get(i));
					if (i < numentries-1) {
						i++;
					} else {
						done = true;
						break;
					}
				}
			}

			pw.close();
		} catch (FileNotFoundException e) {
			System.out.println("The file " + Filename + " was not found.");
		}
	}

}
