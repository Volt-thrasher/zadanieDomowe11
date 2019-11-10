import java.io.*;

public class EmployeeTest {
    public static void main(String[] args) {
        File file = new File("employee.txt");
        Employee[] employees = new Employee[lineCounter(file)];
        employeesArray(file, employees);


        File statFile = new File("statistics.txt");
        try (BufferedWriter bfw = new BufferedWriter(new FileWriter(statFile))){
            bfw.write("Srednia wyplata: "+ mediumSalary(employees));
            bfw.newLine();
            bfw.write("Najwieksza wyplata: "+maxSalary(employees));
            bfw.newLine();
            bfw.write("Najmniejsza wyplata: "+minSalary(employees));
            bfw.newLine();
            bfw.write("Laczna liczba pracownikow: "+employees.length);
            bfw.newLine();
            bfw.write(employeesInDept(employees));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            if (emp.salaryValue() < minSalary) {
                minSalary = emp.salaryValue();
            }
        }
        return minSalary;
    }

    private static double maxSalary(Employee[] employees) {
        double maxSalary = 0;
        for (Employee emp : employees) {
            if (emp.salaryValue() > maxSalary) {
                maxSalary = emp.salaryValue();
            }
        }
        return maxSalary;
    }

    private static void employeesArray(File file, Employee[] employees) {
        try(BufferedReader bfr = new BufferedReader(new FileReader(file))) {
            String line;
            for (int i = 0; (line = bfr.readLine()) != null; i++){
                String[] info = line.split(";");
                employees[i]=new Employee(info[0],info[1],info[2],info[3],info[4]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int lineCounter(File file) {
        int counter = 0;
        try (BufferedReader bfr = new BufferedReader(new FileReader(file))) {
            while (bfr.readLine() != null ){
                counter++;
            }
        } catch (FileNotFoundException e) {
            e.getMessage();
        } catch (IOException e) {
            e.getMessage();
        }
        return counter;
    }

    private static double mediumSalary(Employee[] employees){
        double sum=0;
        for (Employee employee : employees) {
            sum += employee.salaryValue();
        }
        return sum/employees.length;
    }




}
