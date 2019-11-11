package z3;

import java.io.*;
import java.util.Arrays;

public class ArrayTest {
    public static void main(String[] args) {
        int[] array = {1,2,3,4,5,6,7};
        System.out.println(Arrays.toString(divideArray(array,4)));
    }

    private static int[] divideArray (int[] array, int n){
        int[] sums = new int[array.length/n];
        if (n>1 && n<=array.length/2){
            File file = new File("array.txt");
            try (BufferedWriter bfw = new BufferedWriter(new FileWriter(file))){
                BufferedReader bfr = new BufferedReader(new FileReader(file));
                int counter = array.length/n;
                for (int i = 0; i<array.length;i++) {
                    while (i<counter-i) {
                        bfw.write(array[i]);
                        i++;
                        counter*=2;
                    }
                    bfw.newLine();
                }
                String line;
                while ((line=bfr.readLine())!=null){
                    String[] numbers = line.split(";");
                    for (String number:numbers) {
                        int sum = 0;
                        sum += Integer.valueOf(number);
                        while (counter>0){
                            sums[sums.length-counter]=sum;
                            counter--;
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sums;
    }
}
