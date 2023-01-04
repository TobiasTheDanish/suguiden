import java.io.File;
import java.util.ArrayList;

public class App {
	public static String PROJECTPATH = null;
	private final TextUI ui = new TextUI();
	private final StartMenu startMenu = new StartMenu();
	private final SelectionMenu selectionMenu = new SelectionMenu();
	private final RegistrationMenu registrationMenu = new RegistrationMenu();
	private final FileIO fileIO = new FileIO();

	public App() {
		if (PROJECTPATH == null) {
			final String dir = System.getProperty("user.dir");
			System.out.println(dir);
			PROJECTPATH = dir;
		}
	}

	public void run() {
		ui.getInput("Start", "");
		ui.clearConsole();

		ui.displayMessage("Velkommen til SU-Guiden!");
		ui.displayMessage("Her kan du danne dig et overblik over hvor meget du må tjene før du får et su-smæk!");

		String input = ui.getInput("Inden vi starter, skal jeg lige vide om du har været her før.", "J/N");

		File userFile = startMenu.getUserFile(input.equalsIgnoreCase("J"), ui);

		boolean breakLoop = false;

		while (!breakLoop) {
			ArrayList<Month> months = fileIO.readMonthData(userFile);

			Action selectedAction = selectionMenu.selectAction(ui);
			ui.clearConsole();

			Month selectedMonth = null;
			switch (selectedAction) {
				case Register_new:
					ui.displayMessage("Du valgte at registrere en ny måned.");
					selectedMonth = selectionMenu.selectNewMonth(months, ui);
					break;

				case Register_again:
					ui.displayMessage("Du valgte at omregistrere en måned.");
					selectedMonth = selectionMenu.selectRegistreredMonth(months, ui);

				default:
					break;
			}

			selectedMonth = registrationMenu.register(selectedMonth, ui);

			for (Month m : months) {
				if (m.getName().equalsIgnoreCase(selectedMonth.getName())) {
					months.set(months.indexOf(m), selectedMonth);
				}
			}

			fileIO.writeToFile(userFile, months);

			String continueInput = ui.getInput("Ønsker du at registrere igen?", "J/N");
			breakLoop = !continueInput.equalsIgnoreCase("j");
		}

		ui.endParagraph("Vi ses!");
	}
}
