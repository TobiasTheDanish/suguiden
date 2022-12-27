import java.io.File;

public class App {app
	public static String PROJECTPATH = null;
	private final TextUI ui = new TextUI();
	private final StartMenu startMenu = new StartMenu();
	private final Month[] months = new Month[] {new Month("Januar"), new Month("Februar"), new Month("Marts"), new Month("April"), new Month("Maj"), new Month("Juni"), new Month("Juli"), new Month("August"), new Month("September"), new Month("Oktober"), new Month("November"), new Month("December")};
	private File userFile;
	
	public App() {
		if (PROJECTPATH == null) {
			final String dir = System.getProperty("user.dir");
			PROJECTPATH = dir.substring(0, dir.lastIndexOf("/"));
		}
	}

	public void run() {
		ui.clearConsole();

		ui.displayMessage("Velkommen til SU-Guiden!");
		ui.displayMessage("Her kan du danne dig et overblik over hvor meget du må tjene før du får et su-smæk!");

		ui.displayMessage("Inden vi starter, skal jeg lige vide om du har været her før.");
		final String input = ui.getInput("Tast 'J' hvis du har.");

		userFile = startMenu.getUserFile(input.equalsIgnoreCase("J"), ui);

		ui.displayArray("Hvilken måned vil du registrere til?", months);
		final int monthIndex = ui.getIntInput("Indtast tallet for den måned du vil registrere")-1;


		ui.endParagraph("Vi ses!");
	}
}
