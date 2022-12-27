public class Month {
	private final String name;
	private float salary;
	private float maxSalary;

	public Month(final String name) {
		this.name = name;
		this.salary = 0;
		this.maxSalary = 0;
	}

	public String getName() {
		return name;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public float getMaxSalary() {
		return maxSalary;
	}

	public void setMaxSalary(float maxSalary) {
		this.maxSalary = maxSalary;
	}
}
