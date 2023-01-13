public class Month {
	private final String name;
	private float salary;
	private float maxSalary;
	private boolean skippedSu;
	private boolean isRegistered;


	public Month(String name) {
		this.name = name;
		this.salary = 0;
		this.maxSalary = 0;
		this.skippedSu = false;
		this.isRegistered = false;
	}

	public Month(String name, float salary, float maxSalary, boolean skippedSu, boolean isRegistered) {
		this.name = name;
		this.salary = salary;
		this.maxSalary = maxSalary;
		this.skippedSu = skippedSu;
		this.isRegistered = isRegistered;
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

	public boolean getSkippedSu() {
		return skippedSu;
	}

	public void setSkippedSu(boolean skippedSu) {
		this.skippedSu = skippedSu;
	}
	
	public boolean isRegistered() {
		return isRegistered;
	}

	public void setRegistered(boolean isRegistered) {
		this.isRegistered = isRegistered;
	}

	@Override
	public String toString()
	{
		return getName() + ":" +
				"\n|  -  " + getSalary() + "/" + getMaxSalary();
	}
}
