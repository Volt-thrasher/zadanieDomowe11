package z1;

import java.io.*;

public class EmployeeTest {
    public static void main(String[] args) {
        File file = new File("employee.txt");
        try {
            employeesArray(file);
            createStatistics(file);
        }catch (NullPointerException e){
            System.err.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void createStatistics(File file) throws IOException {
        File statFile = new File("statistics.txt");
            BufferedWriter bfw = new BufferedWriter(new FileWriter(statFile));
            bfw.write("Srednia wyplata: "+ mediumSalary(employeesArray(file)));
            bfw.newLine();
            bfw.write("Najwieksza wyplata: "+maxSalary(employeesArray(file)));
            bfw.newLine();
            bfw.write("Najmniejsza wyplata: "+minSalary(employeesArray(file)));
            bfw.newLine();
            bfw.write("Laczna liczba pracownikow: "+employeesArray(file).length);
            bfw.newLine();
            bfw.write(employeesInDept(employeesArray(file)));
    }

    private static String employeesInDept(Employee[] employees) {
        int sumIt=0;
        int sumManagement=0;
        int sumSupport=0;
        for (Employee emp:employees) {
            switch (emp.getDept()){
                case "it" :
                    sumIt++;
                    break;
                case "Management" :
                    sumManagement++;
                    break;
                case "Support":
                    sumSupport++;
                    break;
            }
        }
        return ("Liczba pracownikow dzialu IT: "+sumIt+"\n"
        +"Liczba pracownikow dzialu Management: "+sumManagement+"\n"
        +"Liczba pracownikow dzialu Support: "+sumSupport);
    }

    private static double minSalary(Employee[] employees) {
        double minSalary = maxSalary(employees);
        for (Employee emp : employees) {
            if (emp.getSalary() < minSalary) {
                minSalary = emp.getSalary();
            }
        }
        return minSalary;
    }

    private static double maxSalary(Employee[] employees) {
        double maxSalary = 0;
        for (Employee emp : employees) {
            if (emp.getSalary() > maxSalary) {
                maxSalary = emp.getSalary();
            }
        }
        return maxSalary;
    }

    private static Employee[] employeesArray(File file) throws IOException {
        Employee[] employees = new Employee[lineCounter(file)];
        BufferedReader bfr = new BufferedReader(new FileReader(file));
            String line;
            for (int i = 0; (line = bfr.readLine()) != null; i++){
                String[] info = line.split(";");
                employees[i]=new Employee(info[0],info[1],info[2],info[3],Double.parseDouble(info[4]));
            }
        return employees;
    }

    private static int lineCounter(File file) throws IOException {
        int counter = 0;
        BufferedReader bfr = new BufferedReader(new FileReader(file));
            if (bfr.readLine()==null){
                throw new  NullPointerException();
            }
            do {
               counter++;
            }
            while (bfr.readLine() != null );
        return counter;
    }

    private static double mediumSalary(Employee[] employees){
        double sum=0;
        for (Employee employee : employees) {
            sum += employee.getSalary();
        }
        return sum/employees.length;
    }
}
