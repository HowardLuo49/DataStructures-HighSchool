import java.util.*;
import java.io.*;

public class PasswordHandler {

	static final String specialChars = "!@#$%";
	static final String lowercase = "abcdefghijklmnopqrstuvwxyz";
	static final String uppercase = lowercase.toUpperCase();
	static final String numbers = "0123456789";
	static final String allChars = specialChars + lowercase + uppercase + numbers;
	private static boolean keepGoing = true;

	public static void main(String[] args) throws FileNotFoundException, IOException {

		Scanner s = new Scanner(System.in);

		while (keepGoing) {
			System.out.println("Choose an option: ");
			System.out.println("1. Create a password");
			System.out.println("2. Change password");
			System.out.println("3. Check to see if a password is valid");
			System.out.println("4. Get a suggested password");
			System.out.println("5. Quit");

			int choice = 0;
			choice = s.nextInt();
			s.nextLine();
			if (choice == 1) {
				try {
					createPassword(s);
				} catch (IllegalArgumentException para) {
					System.out.println(para);
				} catch (FileNotFoundException e) {
					System.out.println(e);
				} catch (IOException i) {
					System.out.println(i);
				}

			} else if (choice == 2) {
				try {
					changePassword(s);
				} catch (IllegalArgumentException para) {
					System.out.println(para);
				} catch (FileNotFoundException e) {
					System.out.println(e);
				} catch (IOException i) {
					System.out.println(i);
				}

			} else if (choice == 3) {

				System.out.println("Type your password: ");
				String p = s.nextLine();
				try {
					if (isValid(p)) {
						System.out.println("That password is valid.");
					}
				} catch (IllegalArgumentException para) {
					System.out.println(para);
				}

			}

			else if (choice == 4) {
				boolean goodPswd = false;
				while (!goodPswd) // keeps looping until no exception is thrown, loop can be broken, and password
									// is printed
				{
					try {
						goodPswd = true;
						System.out.println("Your suggested password is: " + suggestPassword());
					} catch (IllegalArgumentException para) {
						goodPswd = false;
					}

				}

			}
			if (choice == 5) {
				keepGoing = false;
			}
		}

	}

	public static boolean isValid(String str) throws IllegalArgumentException {

		boolean acceptable = true;
		for (int i = 0; i < str.length(); i++) {
			if (allChars.indexOf(str.substring(i, i + 1)) == -1) {
				acceptable = false;

			}
		}
		if (!acceptable) {
			System.out.println("Password contains unnaceptable characters, please use acceptable characters");

		}

		boolean badLength = str.length() > 10 || str.length() < 6;
		// Checks for length requirements
		if (badLength) {
			throw new IllegalArgumentException("Password does not meet length requirements, "
					+ "please make sure password is between 6-10 characters long.");

		}

		StringTokenizer st = new StringTokenizer(str, specialChars, true);
		// Checks for special characters
		boolean noSpecial = st.countTokens() < 2;
		if (noSpecial) {
			throw new IllegalArgumentException("Password does not contain any special characters");

		}

		boolean hasLower = false;
		boolean hasUpper = false;
		boolean hasNums = false;

		// Checks for lowercase
		if (new StringTokenizer(str, lowercase, true).countTokens() > 1) {
			hasLower = true;
		}
		// Checks for uppercase
		if (new StringTokenizer(str, uppercase, true).countTokens() > 1) {
			hasUpper = true;
		}
		// check for numbers
		if (new StringTokenizer(str, numbers, true).countTokens() > 1) {
			hasNums = true;
		}

		if (hasLower == false) {

			throw new IllegalArgumentException("Password has no lowercase letters");

		}
		if (hasUpper == false) {
			throw new IllegalArgumentException("Password has no uppercase letters");

		}
		if (hasNums == false) {
			throw new IllegalArgumentException("Password has no numbers");

		}
		return (hasLower && hasUpper && hasNums && !badLength && !noSpecial && acceptable);
	}

	public static void createPassword(Scanner s) throws FileNotFoundException, IOException, IllegalArgumentException {

		File f = new File("src/pwds.txt");

		if (f.exists()) {
			System.out.println("A password has already been created. You can not create another password.");
		} else {
			System.out.print("Type a password: ");
			String p = s.next();
			s.nextLine();

			if (isValid(p)) {
				f.createNewFile(); // creates a new file

				createPassword(p, s);
			} else {
				System.out.println("Your password is invalid.");
			}
		}
	}

	private static void createPassword(String str, Scanner s) throws FileNotFoundException, IOException {

		File file = new File("src/pwds.txt"); // creates a new file

		FileWriter fw = new FileWriter(file, true); // creates a filewriter that appends
		fw.write(str + "\n");
		fw.close();
		System.out.println("Password has been added\n");

	}

	public static void changePassword(Scanner s) throws IOException, FileNotFoundException, IllegalArgumentException {
		boolean duplicate = false; // checks to see if the password already exists
		// checks if password exists yet
		File f = new File("src/pwds.txt");
		if (!f.exists()) {
			System.out.println("You must create a password first");
		} else {
			Scanner pList = new Scanner(f);
			// checks for old password input
			System.out.print("Enter your old password: ");
			String inputOldPassword = s.next().trim();
			String oldPassword = pList.nextLine().trim();
			while (pList.hasNextLine()) {
				oldPassword = pList.nextLine().trim();
			}
			if (!oldPassword.equals(inputOldPassword)) {
				System.out.println("Incorrect password");
			} else {
				// takes in the user password confirmation A
				System.out.print("Enter a new valid password: ");
				String input = s.next().trim();
				s.nextLine();
				// twice to determine if input is allowed B
				if (isValid(input)) {
					Scanner sc = new Scanner(new File("src/pwds.txt"));
					while (sc.hasNextLine()) {
						if (sc.nextLine().equals(input)) {
							System.out.println("Password already exists");
							duplicate = true;
						}

					}
					if (!duplicate)
						createPassword(input, s);
				}
			}
		}
	}

	public static String suggestPassword() throws FileNotFoundException, IllegalArgumentException {
		String answer = "";

		int length = (int) (Math.random() * 5) + 6;
		for (int i = 0; i < length; i++) {

			int rando = (int) (Math.random() * allChars.length());
			answer += allChars.substring(rando, rando + 1);
		}
		isValid(answer); // just checks to see if it is valid, will throw error if it's not

		return answer;

	}

}

/*
 * public static String suggestPassword() throws FileNotFoundException { String
 * answer = ""; for(int i = 0; i < (int)(Math.random()*5) + 6; i++) { int rando
 * = (int)(Math.random()*allChars.length()); answer += allChars.substring(rando,
 * rando+1); } try {isValid(answer);} catch (IllegalArgumentException e) {answer
 * = suggestPassword();} return answer; }
 */
