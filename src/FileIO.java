import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class FileIO {
	private final ArrayList<Month> emptyMonthList = new ArrayList<>();
	private final Month[] emptyMonthArray = new Month[] {new Month("Januar"), new Month("Februar"), new Month("Marts"), new Month("April"), new Month("Maj"), new Month("Juni"), new Month("Juli"), new Month("August"), new Month("September"), new Month("Oktober"), new Month("November"), new Month("December")};

	public File createFile(String fileName) {
		try {
			File file = new File(fileName);
			file.createNewFile();

			Collections.addAll(emptyMonthList, emptyMonthArray);
			writeToFile(file, emptyMonthList);
			return file;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public ArrayList<Month> readMonthData(File file) {
		ArrayList<Month> months = new ArrayList<>();
		
		try {
			Scanner scanner = new Scanner(file);
			scanner.nextLine(); //Reads header line
			while (scanner.hasNextLine()) {
				String monthStr = scanner.nextLine();
				String[] monthArr = monthStr.split(";");
				
				String name = monthArr[0];
				
				float salary = Float.parseFloat(monthArr[1]);

				boolean skippedSu = Boolean.parseBoolean(monthArr[2]);

				float maxSalary = Float.parseFloat(monthArr[3]);

				boolean isRegistered = Boolean.parseBoolean(monthArr[4]);

				Month m = new Month(name, salary, maxSalary, skippedSu, isRegistered);

				months.add(m);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("A file was not found!");
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new RuntimeException("File formatting is incorrect! Was not able to parse to float");
		}

		return months;
	}

	public void writeToFile(File file, ArrayList<Month> months) {
		try {
			FileWriter writer = new FileWriter(file);

			writer.write("month;salary;skippedSu?;maxSalary\n");

			for (Month m : months) {
				StringBuilder string = new StringBuilder();
				string.append(m.getName()).append(";")
					  .append(m.getSalary()).append(";")
					  .append(m.getSkippedSu()).append(";")
					  .append(m.getMaxSalary()).append(";")
					  .append(m.isRegistered()).append("\n");

				writer.write(string.toString());
			}

			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
