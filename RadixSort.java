import java.util.ArrayList;
import java.util.Arrays;

public class RadixSort { 
  private static int counter = 0;

  public static void radixSorting(int[] input) {
    
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
        counter++;
    }
    placement *= 10;
    printList(arrayOne);
    
    for(int i = 1; i < length; i++){
        ContinueSorting(arrayOne, arrayTwo, turn, placement);
        placement *= 10;
        turn = (!turn);
        counter++;
    }

    if(turn == false){ 
        int y = 0;
        for (int x = 0; x < 10; x++) {
            for (int i : arrayTwo[x]) {
                input[y] = i;
                y++;
                counter++;
            }
        } 
    } else {
        int y = 0;
        for (int x = 0; x < 10; x++) {
            for (int i : arrayOne[x]) {
                input[y] = i;
                y++;
                counter++;
            }
        } 
    }
  }

  public static void ContinueSorting(ArrayList<Integer>[] array1, ArrayList<Integer>[] array2, boolean turn, int placement){
    int tmp;
    if(turn == true){
        for (int x = 0; x < 10; x++) {
            for (int i : array1[x]) {
                tmp = i / placement;
                array2[tmp % 10].add(i);
                counter++;
            }
            array1[x].clear();
        }
        printList(array2);
    } else {
        for (int x = 0; x < 10; x++) {
            for (int i : array2[x]) {
                tmp = i / placement;
                array1[tmp % 10].add(i);
                counter++;
            }
            array2[x].clear();
        }
        printList(array1);
    }
  }

  public static void printList(ArrayList<Integer>[] array){
    for(int x = 0; x < 10; x++){
        for(int i : array[x]) {
            System.out.print( i + " ");
        }
    }
    System.out.print("\n");
  }
 
  public static void main(String[] args) {
    int[] input = { 275, 87, 261, 666 };

    System.out.println("Unsorted array : " + Arrays.toString(input));
    radixSorting(input);
    System.out.println("Sorted array : " + Arrays.toString(input));
    System.out.println("Number of operations performed: " + counter);
  }
};