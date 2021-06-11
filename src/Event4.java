import java.util.Scanner;
import java.util.Stack;


public class Event4 {
    private static Stack<Integer> bookRow = new Stack<>();
    private static Stack<Integer> temp = new Stack<>();
    private static String input1;
    private static String[] input2;

    public void runEvent4() {
        Scanner s = new Scanner(System.in);

        boolean flag = false;
        while(!flag) {
            /* First input for determining the number of books
             *  Make sure no negative number for input1
             * */
            System.out.println("Enter number of books followed by height of each book: ");
            input1 = s.next();
            if(!checkInput1(input1))
                continue;
            /* Second input is to assign the heights of the books
             *  Make sure no zero and negative number for input2
             * */
            input2 = new String[Integer.parseInt(input1)];
            for (int i = 0; i < Integer.parseInt(input1); i++) {
                input2[i] = s.next();
            }
            if(!checkInput2(input2))
                continue;

            flag = true;
        }
        /* Push everything in array into stack
         */
        int num = 0;
        while(num < input2.length){
            bookRow.push(Integer.parseInt(input2[num]));
            num++;
        }


        /* Start calculating round
         *  Tracking books
         *  Use another temp stack to store temporary elements, and then put back into original stack
         */
        int round = 0;
        System.out.println(bookRow);    // show original bookRow stack
        while(!completeArranged(bookRow)) {
            while (!bookRow.isEmpty()) {
                temp.push(bookRow.pop());   //  All elements pushed into temp so that we can arrange books from left to the right
            }
            compare();
            round++;
            System.out.println(bookRow);    // show bookRow stack after compare method
        }
        System.out.println("Number of rounds needed to make the height of the books in non-increasing order : " + round);
    }


    /* Methods Parts */
    /** Create method compare : to compare book by book
     *  involve 2 stacks
     *  check whether the heights meet the requests by using 3 VARIABLES ( x & y variables for comparison, z for storing height does not meet requests
     *  if YES pushed into bookRow
     *  if NO store into variable z, and using z for the heights onwards
     */
    public static void compare(){
        int x,y;
        int z = 0;
        while(temp.size() > 1){
            if(z == 0) {
                x = temp.pop();
                y = temp.pop();
                if (x >= y) {
                    bookRow.push(x);
                    temp.push(y);
                } else  {
                    bookRow.push(x);
                    z = y;
                }
            }else if(z != 0){
                x = temp.pop();
                y = temp.pop();
                if(z >= x){
                    bookRow.push(x);
                    if(x >= y){
                        temp.push(y);
                        z = 0;
                    }else{
                        z = y;
                    }
                }else{  // z < x
                    if(x >= y) {
                        temp.push(y);
                        z = x;
                    }
                    else
                        z = y;
                }
            }
        }
        if(temp.size()==1){
            if((z != 0 && z >= temp.peek()) || z == 0){
                bookRow.push(temp.pop());
            }else if(z != 0 && z < temp.peek()){
                temp.pop();
            }
        }
    }

    public static boolean completeArranged(java.util.Stack<Integer> obj){
        Stack<Integer> tempo = (Stack<Integer>) obj.clone();
        while(tempo.size() > 1){
            if(tempo.pop() > tempo.peek())
                return false;
        }
        return true;
    }

    public static boolean checkInput1(String input){
        if(Integer.parseInt(input) < 0) {
            System.out.println("Invalid input!");
            return false;
        }
        return true;
    }

    public static boolean checkInput2(String[] arr){
        for (int i = 0; i < arr.length; i++) {
            if(Integer.parseInt(arr[i]) <= 0) {
                System.out.println("Impossible book height is zero or negative!");
                return false;
            }
        }
        return true;
    }
}
