import java.io.File;

public class StartMenu {
	FileIO fileIO = new FileIO();

	public File getUserFile(boolean isCurrentUser, TextUI textUI) {
		while (true) {
			String welcome = isCurrentUser ? "Velkommen tilbage!" : "Velkommen til";
			String findOrCreate = isCurrentUser ? "finder" : "opretter";

			String username = textUI.getInput("Indtast dit navn, så " + findOrCreate + " jeg dig i systemet!");
			String userFileName = App.PROJECTPATH + "/data/" + username + ".csv";

			textUI.clearConsole();

			if (isCurrentUser) {
				File f = new File(userFileName);
				if (f.exists()) {
					textUI.displayMessage("Jeg fandt en fil med dit navn!");
					return f;
				} else {
					textUI.displayMessage("Jeg fandt ikke en fil med dit navn... Prøv igen.");
				}
			} else {
				File f = fileIO.createFile(userFileName);
				textUI.displayMessage("Jeg har oprettet en fil til dig!");
				return f;
			}
		}	
	}
}
