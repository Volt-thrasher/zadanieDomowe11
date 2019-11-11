package z3;

import java.io.*;
import java.util.Arrays;

public class ArrayTest {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8};
        int n = 3;
        int[] newArray = divideArray(array, n);
        System.out.println(Arrays.toString(newArray));
    }

    private static int[] divideArray(int[] array, int n) {
        int[] sums = new int[n];
        int limit;
        boolean even = array.length % 2 == 0;
        if (even) {
            limit = array.length / 2;
        } else {
            limit = 1 + array.length / 2;
        }
        if (n > 1 && n <= limit) {
            int counter = 1 + array.length / n;
            File file = new File("array.txt");
            try (BufferedWriter bfw = new BufferedWriter(new FileWriter(file))) {
                for (int i = 0; i < array.length; ) {
                    while (i < counter && i < array.length) {
                        bfw.write(array[i] + ";");
                        i++;
                    }
                    counter += 1 + array.length / n;
                    bfw.newLine();
                }
                bfw.close();
                BufferedReader bfr = new BufferedReader(new FileReader(file));
                String line;
                counter = n;
                while ((line = bfr.readLine()) != null) {
                    String[] numbers = line.split(";");
                    int sum = 0;
                    while (counter > 0) {
                        for (String number : numbers) {
                            sum += Integer.parseInt(number);
                        }
                        sums[sums.length - counter] = sum;
                        counter--;
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sums;
    }
}