import java.util.ArrayList;

public class OverviewMenu
{
    public void displayOverview(ArrayList<Month> months, TextUI ui) {
        ui.displayDetailedList("Her er et overblik:", months);

        ui.displayMessage("");
        ui.displayMessage(getRemainingSalary(months));

        ui.getInput("", "ENTER");

        ui.clearConsole();
    }

    private String getRemainingSalary(ArrayList<Month> months)
    {
        float totalMaxSalary = 0;
        float totalSalary = 0;
        for (Month m : months) {
            totalMaxSalary += m.getMaxSalary();
            totalSalary += m.getSalary();
        }
        float salaryOverMax = totalSalary - totalMaxSalary;
        if (salaryOverMax < 0) {
            return "Du er " + Math.abs(salaryOverMax) + " under dit maximum, i dine registrerede måneder.";
        }

        return "Du er " + salaryOverMax + " over dit maximum, i dine registrerede måneder.";
    }
}
