import java.util.ArrayList;
import java.util.Scanner;

public class TextUI {
    private final Scanner scanner = new Scanner(System.in);

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
            String subString = msg.substring(0, lastSpace);

            string.append("|  ").append(subString).append("\n|  ").append(msg.substring(lastSpace + 1));
        }

        System.out.println(string);
    }

    public String getInput(String msg, String expectedInput) {
        displayMessage(msg);
        StringBuilder s = new StringBuilder();
        s.append("|  ");

        if (expectedInput != null && !expectedInput.trim().isEmpty()) {
            s.append("(")
             .append(expectedInput)
             .append(") ");
        }

        s.append("> ");

        System.out.print(s.toString());

        String input = scanner.nextLine();

        System.out.println("\r|");

        return input;
    }

    public int getIntInput(String msg) {
        while (true) {
            String input = getInput(msg, "#");

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                displayMessage("Hovsa, det var vidst ikke et tal, prøv igen!");
            }
        }
    }

    public void displayList(String msg, ArrayList<Month> array) {
        displayMessage(msg);

        StringBuilder s = new StringBuilder();
        s.append("|");
        for (int i = 0; i < array.size(); i++) {
            s.append("\n|  ").append(i+1).append(")  ").append(array.get(i).getName());
        }
        System.out.println(s);
    }

    public void displayDetailedList(String msg, ArrayList<Month> array) {
        displayMessage(msg);

        StringBuilder s = new StringBuilder();
        s.append("|");
        for (Month month : array)
        {
            s.append("\n|  ").append(month);
        }

        System.out.println(s);
    }

    public void displayOptions(String msg, String[] options) {
        displayMessage(msg);

        StringBuilder s = new StringBuilder();
        s.append("|");
        for (int i = 0; i < options.length; i++) {
            s.append("\n|  ").append(i+1).append(")  ").append(options[i]);
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
