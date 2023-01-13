import java.io.File;
import java.util.ArrayList;

public class App {
    public static String PROJECTPATH = null;
    private final TextUI ui = new TextUI();
    private final StartMenu startMenu = new StartMenu();
    private final SelectionMenu selectionMenu = new SelectionMenu();
    private final RegistrationMenu registrationMenu = new RegistrationMenu();
    private final OverviewMenu overviewMenu = new OverviewMenu();
    private final FileIO fileIO = new FileIO();

    public App() {
        if (PROJECTPATH == null) {
            final String dir = System.getProperty("user.dir");
            System.out.println(dir);
            PROJECTPATH = dir;
        }
    }

    public void run() {
        ui.getInput("","Start");
        ui.clearConsole();

        ui.displayMessage("Velkommen til SU-Guiden!");
        ui.displayMessage("Her kan du danne dig et overblik over hvor meget du må tjene før du får et su-smæk!");

        String input = ui.getInput("Inden vi starter, skal jeg lige vide om du har været her før.", "J/N");

        File userFile = startMenu.getUserFile(input.equalsIgnoreCase("J"), ui);

        boolean breakLoop = false;

        while (!breakLoop) {
            ArrayList<Month> months = fileIO.readMonthData(userFile);

            Action[] validActions = getValidActions(months);
            Action selectedAction = selectionMenu.selectAction(ui, validActions);
            ui.clearConsole();

            Month selectedMonth = null;
            switch (selectedAction) {
                case Registrer_en_ny_måned:
                    ui.displayMessage("Du valgte at registrere en ny måned.");
                    selectedMonth = selectionMenu.selectNewMonth(months, ui);
                    break;

                case Omregistrer_en_måned:
                    ui.displayMessage("Du valgte at omregistrere en måned.");
                    selectedMonth = selectionMenu.selectRegistreredMonth(months, ui);
                    break;

                case Vis_overblik:
                    ArrayList<Month> registeredMonths = getRegisteredMonths(months);
                    overviewMenu.displayOverview(registeredMonths, ui);
                    break;

                case Afslut_program:
                    breakLoop = true;
                    break;

                default:
                    break;
            }
            if (selectedMonth == null) continue;

            selectedMonth = registrationMenu.register(selectedMonth, ui);

            for (Month m : months) {
                if (m.getName().equalsIgnoreCase(selectedMonth.getName())) {
                    months.set(months.indexOf(m), selectedMonth);
                }
            }

            fileIO.writeToFile(userFile, months);
        }

        ui.endParagraph("Vi ses!");
    }

    private ArrayList<Month> getRegisteredMonths(ArrayList<Month> months)
    {
        ArrayList<Month> monthList = new ArrayList<>();

        for (Month m : months) {
            if (m.isRegistered()) {
                monthList.add(m);
            }
        }

        return monthList;
    }

    private Action[] getValidActions(ArrayList<Month> months)
    {
        ArrayList<Action> actionList = new ArrayList<>();

        if (canRegisterNew(months)) {
            actionList.add(Action.Registrer_en_ny_måned);
        }
        if (canShowOverview(months)) {
            actionList.add(Action.Omregistrer_en_måned);
            actionList.add(Action.Vis_overblik);
        }
        actionList.add(Action.Afslut_program);

        return actionList.toArray(new Action[0]);
    }

    private boolean canRegisterNew(ArrayList<Month> months)
    {
        for (Month m : months) {
            if (!m.isRegistered()) {
                return true;
            }
        }

        return false;
    }

    private boolean canShowOverview(ArrayList<Month> months)
    {
        for (Month m : months) {
            if (m.isRegistered()) {
                return true;
            }
        }
        return false;
    }
}
