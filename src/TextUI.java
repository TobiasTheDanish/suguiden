import java.util.Scanner;

public class TextUI {
	private Scanner scanner = new Scanner(System.in);

	public void startParagraph(String msg) {
		StringBuilder startString = new StringBuilder(80);

		for (int i = 1;  i < startString.capacity();  i++) {
			startString.append("_");
		}

		System.out.println(startString);
		displayMessage("");
		
		if (msg.length() > 0) {
			displayMessage(msg);
		}
	}

	public void endParagraph(String msg) {
		if (msg.length() > 0) {
			displayMessage(msg);
		}

		StringBuilder endString = new StringBuilder(80);
		endString.append("|");

		for (int i = 1;  i < endString.capacity();  i++) {
			endString.append("_");
		}

		System.out.println(endString);
	}

	public void displayMessage(String msg) {
		StringBuilder string = new StringBuilder();

		if (msg.length() <= 76) {
			string.append("|  ").append(msg);

			while (string.length() < 80) {
				string.append(" ");
			}
		} else {
			int lastSpace = msg.lastIndexOf(' ');
			StringBuilder subString = new StringBuilder(msg.substring(0, lastSpace));

			StringBuilder second = new StringBuilder();
			second.append("\n|  ").append(msg.substring(lastSpace+1));

			string.append("|  ").append(subString).append(second);
		}

		System.out.println(string);
	}

	public String getInput(String msg) {
		displayMessage(msg);
		String s = "|  Input: ";
		System.out.print(s);
		
		String input = scanner.next();

		StringBuilder sb = new StringBuilder();
		sb.append("\r|");

		System.out.println(sb);

		return input;
	}

	public int getIntInput(String msg) {
		while (true) {
			String input = getInput(msg);

			try {
				int i = Integer.parseInt(input);
				return i;
			} catch (NumberFormatException e) {
				displayMessage("Hovsa, det var vidst ikke et tal, prøv igen!");
			}
		}
	}

	public void displayArray(String msg, Month[] array) {
		displayMessage(msg);
		
		StringBuilder s = new StringBuilder();
		s.append("|");
		for (int i = 0; i < array.length; i++) {
			s.append("\n|  ").append(i+1).append(")  ").append(array[i].getName());
		}
		System.out.println(s);
	}

	public void clearConsole() {
		endParagraph("");

		for (int i = 0; i < 100; i++) {
			System.out.println("");
		}

		startParagraph("");
	}
}
