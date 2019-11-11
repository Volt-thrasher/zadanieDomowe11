package z2;

import java.io.*;

public class Test2 {
    public static void main(String[] args) {
        File file = new File("arithmetic.txt");
        calculateFromFile(file);
    }

    private static void calculateFromFile(File file) {
        String line;
        try (BufferedReader bfr = new BufferedReader(new FileReader(file))) {
            while ((line = bfr.readLine()) != null) {
                String[] signs = line.split(" ");
                double result = 0;
                String sign = null;
                switch (signs[1]) {
                    case "+":
                        result = Double.valueOf(signs[0]) + Double.valueOf(signs[2]);
                        sign = "+";
                        break;
                    case "-":
                        result = Double.valueOf(signs[0]) - Double.valueOf(signs[2]);
                        sign = "-";
                        break;
                    case "*":
                        result = Double.valueOf(signs[0]) * Double.valueOf(signs[2]);
                        sign = "*";
                        break;
                    case "/":
                        result = Double.valueOf(signs[0]) / Double.valueOf(signs[2]);
                        sign = "/";
                        break;
                }
                File file1 = new File("results.txt");
                BufferedWriter bfw = new BufferedWriter(new FileWriter(file1));
                bfw.write(signs[0] + sign + signs[2] + "=" + result);
                bfw.newLine();
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
