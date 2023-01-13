import java.util.ArrayList;

public class SelectionMenu {

	public Action selectAction(TextUI ui, Action[] options) {
		String[] optionStrings = changeActionToString(options);
		while (true) {
			ui.displayOptions("Hvad kunne du tænke dig at gøre?", optionStrings);
			try {
				ui.displayMessage("");
				int actionIndex = ui.getIntInput("Indtast tallet for den mulighed du ønsker") -1;
				return options[actionIndex];
			} catch(IndexOutOfBoundsException e) {
				ui.displayMessage("Whoops, det tal var enten for stort eller småt. Prøv igen.");
			}
		}
	}

	private String[] changeActionToString(Action[] options)
	{
		String[] out = new String[options.length];

		for (int i = 0; i < out.length; i++) {
			String actionString = options[i].toString();

			out[i] = actionString.replace("_", " ");
		}

		return out;
	}

	public Month selectNewMonth(ArrayList<Month> months, TextUI ui) {
		int index = 0;
		months = getMonths(months, false);

		ui.displayList("Hvilken måned vil du registrere til?", months);
		while (true) {
			ui.displayMessage("");

			index = ui.getIntInput("Indtast tallet for den måned du vil registrere.")-1;

			try {
				return months.get(index);
			} catch (IndexOutOfBoundsException e) {
				ui.displayMessage("Det tal var enten for stort eller småt. Prøv igen.");
			}
		}
	}

	public Month selectRegistreredMonth(ArrayList<Month> months, TextUI ui) {
		int index = 0;

		months = getMonths(months, true);

		ui.displayList("Hvilken måned vil du registrere til?", months);
		while (true) {
			ui.displayMessage("");

			index = ui.getIntInput("Indtast tallet for den måned du vil registrere.")-1;

			try {
				return months.get(index);
			} catch (IndexOutOfBoundsException e) {
				ui.displayMessage("Det tal var enten for stort eller småt. Prøv igen.");
			}
		}
	}

	private ArrayList<Month> getMonths(ArrayList<Month> originalList, boolean isRegistred) {
		ArrayList<Month> months = new ArrayList<>();
		
		for (Month m : originalList) {
			if (m.isRegistered() == isRegistred) {
				months.add(m);
			}
		}
		return months;
	}
}


