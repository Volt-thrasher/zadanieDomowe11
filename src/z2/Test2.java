package z2;

import java.io.*;

public class Test2 {
    public static void main(String[] args) {
        File file = new File("arithmetic.txt");
        try {
            calculateFromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void calculateFromFile(File file) throws IOException {
        String line;
            BufferedReader bfr = new BufferedReader(new FileReader(file));
            while ((line = bfr.readLine()) != null) {
                String[] signs = line.split(" ");
                double result = getResult(signs);
                File file1 = new File("results.txt");
                BufferedWriter bfw = new BufferedWriter(new FileWriter(file1));
                bfw.write(signs[0] + signs[1] + signs[2] + "=" + result);
                bfw.newLine();
            }
    }

    private static double getResult(String[] signs) {
        double result = 0;
        switch (signs[1]) {
            case "+":
                result = Double.valueOf(signs[0]) + Double.valueOf(signs[2]);
                break;
            case "-":
                result = Double.valueOf(signs[0]) - Double.valueOf(signs[2]);
                break;
            case "*":
                result = Double.valueOf(signs[0]) * Double.valueOf(signs[2]);
                break;
            case "/":
                result = Double.valueOf(signs[0]) / Double.valueOf(signs[2]);
                break;
        }
        return result;
    }
}
