public class RegistrationMenu {
	public Month register(Month month, TextUI ui) {
		ui.clearConsole();
		
		StringBuilder string = new StringBuilder();
		string.append("Du har valgt at registrere til ")
			  .append(month.getName())
			  .append(".");

		ui.displayMessage(string.toString());
		ui.displayMessage("");

		boolean breakLoop = false;

		while (!breakLoop) {
			String suInput = ui.getInput("Har du valgt su fra i denne måned?", "J/N");
			switch (suInput) {
				case "j":
				case "J":
					month.setSkippedSu(true);
					month.setMaxSalary(20329);
					breakLoop = true;
					break;

				case "n":
				case "N":
					month.setSkippedSu(false);
					month.setMaxSalary(17876);
					breakLoop = true;
					break;

				default:
					ui.displayMessage("Det forstod jeg ikke, hold dig venligst til 'J' eller 'N'.");
					break;
			}
		}
		breakLoop = false;

		while (true) {
			String salaryInput = ui.getInput("Indtast din løn denne måned (Før am-bidrag og skat).", "DKK");

			try {
				if (salaryInput.contains(",")) {
					salaryInput = salaryInput.replace(",", ".");
				}

				float salary = Float.parseFloat(salaryInput);

				month.setSalary(salary);
				break;
			} catch (NumberFormatException e) {
				ui.displayMessage("Ups, der gik noget galt med formateringen af lønnen");
				ui.displayMessage("Brug venligst ikke tusindtalsseperatorer. Du kan dog bruge både '.' og ',' til at indikere decimaler");
			}
		}

		month.setRegistered(true);

		return month;
	}
}
