import java.util.ArrayList;
import java.util.Arrays;


public class Radix {

  public static void printList(ArrayList<Integer>[] array){
    for(int x = 0; x < 10; x++){
        for(int i : array[x]) {
            System.out.print( i + " ");
        }
    }
    System.out.print("\n");
  }

  public static void radixSorting(int[] input) {
    
    ArrayList<Integer>[] arrayOne = new ArrayList[10];
    ArrayList<Integer>[] arrayTwo = new ArrayList[10];

    for (int i = 0; i < 10; i++) {
        arrayOne[i] = new ArrayList<Integer>();
        arrayTwo[i] = new ArrayList<Integer>();
    }

    for (int i = 0; i < input.length; i++){
        arrayOne[i].add(input[i]);
    }

    long int tmp, placement = 1, pass = 1;
    boolean sorted = false;

    while (!sorted) {

        sorted = true;
        for (int x = 0; x < 10; x++) {
            for (int i : arrayOne[x]) {

                tmp = i / placement;
                arrayTwo[tmp % 10].add(i);
                    
                if (tmp >= 1) {
                    sorted = false;
                }
            }
            if (sorted == true) { break; }
            arrayOne[x].clear();
        }
        if (sorted == true) { break; }
        printList(arrayTwo);
        placement *= 10;

        for (int x = 0; x < 10; x++) {
            for (int i : arrayTwo[x]) {

                tmp = i / placement;
                arrayOne[tmp % 10].add(i);
    
                if (tmp >= 1) {
                    sorted = false;
                }
            }
            if (sorted == true) { break; }
            arrayTwo[x].clear();
        }
        if (sorted == true) { break; }
        printList(arrayOne);
        placement *= 10;
    }
    
    int y = 0;
    for (int x = 0; x < 10; x++) {
        for (int i : arrayOne[x]) {
            input[y] = i;
            y++;
        }
    }
 }

  public static void main(String[] args) {

    int[] input = { 275, 87, 4261, 61, 409, 170, 677, 503 };

    System.out.println(Arrays.toString(input));
    radixSorting(input);
    System.out.println(Arrays.toString(input));

  }

};

