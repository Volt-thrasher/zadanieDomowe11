package z1;

public class Employee {
    private String firstName;
    private String lastName;
    private String pesel;
    private String dept;
    private double salary;

    public Employee(String firstName, String lastName, String pesel, String dept, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.dept = dept;
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String salaryToString() {
        return String.valueOf(salary);
    }

    @Override
    public String toString() {
        return "z1.Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", pesel='" + pesel + '\'' +
                ", dept='" + dept + '\'' +
                ", salary='" + salary + '\'' +
                '}';
    }
}
