package z3;

import javax.naming.LimitExceededException;
import java.io.*;
import java.util.Arrays;

public class ArrayTest {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6,7,8,9};
        int n = 4;
        int[] newArray = new int[0];
        try {
            newArray = divideArray(array, n);
            System.out.println(Arrays.toString(newArray));
        } catch (LimitExceededException | ArrayIndexOutOfBoundsException e) {
            System.err.println(e.getMessage());
        }
    }

    private static int[] divideArray(int[] array, int n) throws LimitExceededException {
        if (n>array.length){
            throw new  ArrayIndexOutOfBoundsException("Dlugosc tabeli mniejsza od wartosci n.");
        }
        int[] sums = new int[n];
        int limit = getLimit(array);
        if (n > 1 && n <= limit) {
            int counter = getCounter(array, n);
            File file = new File("array.csv");
            try (BufferedWriter bfw = new BufferedWriter(new FileWriter(file))) {
                arrayToCsv(array, n, counter, bfw);
                BufferedReader bfr = new BufferedReader(new FileReader(file));
                String line;
                counter = n;
                fillSumsArray(sums, counter, bfr);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            throw new LimitExceededException("Podzial tabeli na n czesci jest niemozliwy. \n " +
                    "Wartosc n powinna byc mniejsza od polowy dlugosci tabeli.");
        }
        return sums;
    }

    private static void fillSumsArray(int[] sums, int counter, BufferedReader bfr) throws IOException {
        String line;
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
    }

    private static void arrayToCsv(int[] array, int n, int counter, BufferedWriter bfw) throws IOException {
        for (int i = 0; i < array.length; ) {
            while (i < counter && i < array.length) {
                bfw.write(array[i] + ";");
                i++;
            }
            if (array.length%n==0){
                counter+=array.length/n;
            }else {
                counter+=1+array.length/n;
            }
            bfw.newLine();
        }
        bfw.close();
    }

    private static int getCounter(int[] array, int n) {
        int counter;
        if (array.length%n!=0) {
            counter = 1 + array.length / n;
        }else {
            counter = array.length/n;
        }
        return counter;
    }

    private static int getLimit(int[] array) {
        int limit;
        boolean even = array.length % 2 == 0;
        if (even) {
            limit = array.length / 2;
        } else {
            limit = 1 + array.length / 2;
        }
        return limit;
    }
}