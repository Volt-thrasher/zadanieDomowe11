package z1;

import java.io.*;

public class EmployeeTest {
    public static void main(String[] args) {
        File file = new File("employee.txt");
        employeesArray(file);
        createStatistics(file);
    }

    private static void createStatistics(File file) {
        File statFile = new File("statistics.txt");
        try (BufferedWriter bfw = new BufferedWriter(new FileWriter(statFile))){
            bfw.write("Srednia wyplata: "+ mediumSalary(employeesArray(file)));
            bfw.newLine();
            bfw.write("Najwieksza wyplata: "+maxSalary(employeesArray(file)));
            bfw.newLine();
            bfw.write("Najmniejsza wyplata: "+minSalary(employeesArray(file)));
            bfw.newLine();
            bfw.write("Laczna liczba pracownikow: "+employeesArray(file).length);
            bfw.newLine();
            bfw.write(employeesInDept(employeesArray(file)));
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

    private static Employee[] employeesArray(File file) {
        Employee[] employees = new Employee[lineCounter(file)];
        try(BufferedReader bfr = new BufferedReader(new FileReader(file))) {
            String line;
            for (int i = 0; (line = bfr.readLine()) != null; i++){
                String[] info = line.split(";");
                employees[i]=new Employee(info[0],info[1],info[2],info[3],Double.parseDouble(info[4]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }

    private static int lineCounter(File file) {
        int counter = 0;
        try (BufferedReader bfr = new BufferedReader(new FileReader(file))) {
            if (bfr.readLine()==null){
                throw new  NullPointerException();
            }
            do {
               counter++;
            }
            while (bfr.readLine() != null );
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
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
