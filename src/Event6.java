import java.util.ArrayList;
import java.util.Scanner;

public class Event6{
    private static FriendshipGraph g = new FriendshipGraph();
    private static int friendship_number = 0;
    private static ArrayList<ArrayList<Student>> allfriendship = new ArrayList<>();

    public void runEvent6() {
        //Input Part
        promptInput();
        Student[] allstudent = g.toArray();
        for (int i = 0; i < allstudent.length; i++) {
            for (int j = i+1; j < allstudent.length; j++) {
                discoverFriendships(allstudent[i], allstudent[j], new ArrayList<>());
            }
        }
        insertionSort(allfriendship);
        for (int i = 0; i < allfriendship.size(); i++) {
            System.out.println(i+1 + ". " + allfriendship.get(i).toString());
        }
        System.out.println("Total possible friendship : " + friendship_number);
    }

    public static void promptInput(){
        Scanner s = new Scanner(System.in);
        System.out.println("Enter numbers of existing friendships : ");
        int input1 = s.nextInt();
        s.nextLine();
        for (int i = 0; i < input1; i++) {
            String source = s.next();
            String destination = s.next();
            g.addStudent(source);
            g.addStudent(destination);
            g.addFriend(g.getStudent(source), g.getStudent(destination),1 , 1);
        }
    }

    public static void discoverFriendships(Student a, Student b, ArrayList<Student> tempArr){
        tempArr.add(a);
        ArrayList<Friend> friendlist = a.friend;
        for (int i = 0; i < friendlist.size(); i++) {
            if(friendlist.get(i).getFriend().equals(b)){    // matched student b
                ArrayList<Student> newArr = new ArrayList<>();
                for (int j = 0; j < tempArr.size(); j++) {
                    newArr.add(tempArr.get(j));
                }
                newArr.add(b);
                friendship_number++;
                allfriendship.add(newArr);
            }
            else{
                if(!tempArr.contains(friendlist.get(i).getFriend())){
                    ArrayList<Student> newArr = new ArrayList<>();
                    for (int j = 0; j < tempArr.size(); j++) {
                        newArr.add(tempArr.get(j));
                    }
                    discoverFriendships(friendlist.get(i).getFriend(), b, newArr);
                }
            }
        }
    }

    public static void insertionSort(ArrayList<ArrayList<Student>> arrlist){
        // First Sort from shortest length to longest length
        for (int i = 1; i < arrlist.size(); i++) {
            ArrayList<Student> current = arrlist.get(i);
            int k;
            for (k = i-1; k >= 0 && arrlist.get(k).size() > current.size(); k--) {
                arrlist.set(k+1, arrlist.get(k));
            }
            arrlist.set(k+1, current);
        }
        // Second Sort for contents one-by-one
        for (int i = 1; i < arrlist.size(); i++) {
            ArrayList<Student> current = arrlist.get(i);
            int k;
            for (k= i-1; k >=0 ; k--) {        // k low than current
                if(arrlist.get(k).size()==current.size()){
                    for (int j = 0; j < arrlist.get(k).size(); j++) {
                        if(arrlist.get(k).get(j).compareTo(current.get(j))==0)
                            continue;
                        else if(arrlist.get(k).get(j).compareTo(current.get(j)) > 0) {
                            ArrayList<Student> temp = arrlist.get(k+1);
                            arrlist.set(k + 1, arrlist.get(k));
                            arrlist.set(k, temp);
                        }else
                            break;
                    }
                }
            }
        }
    }
}
