import java.util.ArrayList;
import java.util.Arrays;


public class RadixSort {
    
    public static float decimalPlaces = 0;

    static void radixSortInt(int[] input) {

        ArrayList<Integer>[] arrayOne = new ArrayList[10];
        ArrayList<Integer>[] arrayTwo = new ArrayList[10];
    
        for (int i = 0; i < 10; i++) {
            arrayOne[i] = new ArrayList<Integer>();
            arrayTwo[i] = new ArrayList<Integer>();
        }
    
        int tmp, placement = 1;
        int length = String.valueOf(Arrays.stream(input).max().getAsInt()).length();
        boolean turn = true;
    
        for(int i : input){
            tmp = i / placement;
            arrayOne[tmp % 10].add(i);
        }
        placement *= 10;
        printList(arrayOne);
        
        for(int i = 1; i < length; i++){
            if(turn == true){
            for (int x = 0; x < 10; x++) {
                for (int y : arrayOne[x]) {
                    tmp = y / placement;
                    arrayTwo[tmp % 10].add(y);
                }
                arrayOne[x].clear();
                }
                printList(arrayTwo);
            } else {
                for (int x = 0; x < 10; x++) {
                    for (int y : arrayTwo[x]) {
                        tmp = y / placement;
                        arrayOne[tmp % 10].add(y);
            
                    }
                    arrayTwo[x].clear();
                }
                printList(arrayOne);
            }
            placement *= 10;
            turn = (!turn);
        }
    
        if(turn == false){ 
            int y = 0;
            for (int x = 0; x < 10; x++) {
                for (int i : arrayTwo[x]) {
                    input[y] = i;
                    y++;
        
                }
            } 
        } else {
            int y = 0;
            for (int x = 0; x < 10; x++) {
                for (int i : arrayOne[x]) {
                    input[y] = i;
                    y++;
        
                }
            } 
        }
    }

    static void radixSortFloat(float[] input){

        int[] tempArr = new int[input.length];
        for (float i : input) {
            String FloatStr = Float.toString(i);
            String[] strArr = FloatStr.split("\\.");
            if (strArr[1].length() > decimalPlaces) {
              decimalPlaces = strArr[1].length();
          }
        }
        for (int x = 0; x < input.length; x++) {
          input[x] *= Math.pow(10, decimalPlaces);
          tempArr[x] = (int) input[x];
        }
        radixSortInt(tempArr);
    
        for (int i = 0; i < input.length; i++) {
            input[i] = tempArr[i];
            input[i] /= (Math.pow(10, decimalPlaces));
        }
    }
    
    static void printList(ArrayList<Integer>[] array){
        for(int x = 0; x < 10; x++){
            for(int i : array[x]) {
                System.out.print( i + " ");
            }
        }
        System.out.print("\n");
    }

    public static void main(String[] args) {
        
        int[] input_int = { 275, 87, 426, 061, 409, 170, 677, 503 };
        System.out.println("Unsorted array : " + Arrays.toString(input_int));
        radixSortInt(input_int);
        System.out.println("Sorted array : " + Arrays.toString(input_int) + "\n");

        float[] input_float = {25.921f, 87.3f, 87.1f, 87.22f, 419.1f, 0.1f};
        System.out.println("Unsorted array: " + Arrays.toString(input_float));
        radixSortFloat(input_float);
        System.out.println("Sorted array: " + Arrays.toString(input_float));
    }
}