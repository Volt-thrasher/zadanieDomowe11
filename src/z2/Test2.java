package z2;

import z1.EmployeeTest;

import java.io.*;

public class Test2 {
    public static void main(String[] args) {
        File file = new File("arithmetic.txt");
        File file1 = new File("results.txt");
        String line;
        try (BufferedReader bfr = new BufferedReader(new FileReader(file))) {
            try (BufferedWriter bfw = new BufferedWriter(new FileWriter(file1))) {
                while ((line = bfr.readLine()) != null) {
                    String[] signs = line.split(" ");
                    double result = 0;
                    switch (signs[1]) {
                        case "+":
                            result = Double.valueOf(signs[0]) + Double.valueOf(signs[2]);
                            bfw.write(signs[0] + "+" + signs[2] + "=" + result);
                            bfw.newLine();
                            break;
                        case "-":
                            result = Double.valueOf(signs[0]) - Double.valueOf(signs[2]);
                            bfw.write(signs[0] + "-" + signs[2] + "=" + result);
                            bfw.newLine();
                            break;
                        case "*":
                            result = Double.valueOf(signs[0]) * Double.valueOf(signs[2]);
                            bfw.write(signs[0] + "*" + signs[2] + "=" + result);
                            bfw.newLine();
                            break;
                        case "/":
                            result = Double.valueOf(signs[0]) / Double.valueOf(signs[2]);
                            bfw.write(signs[0] + "/" + signs[2] + "=" + result);
                            bfw.newLine();
                            break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
