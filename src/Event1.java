import java.util.Scanner;

public class Event1 {
    private Student s1;
    private Student s2;

    Event1(Student s1, Student s2){
        this.s1 = s1;
        this.s2 = s2;
    }


    public void runEvent1() {
        Scanner sc = new Scanner(System.in);
        System.out.println(s1.getName() + " talk to " + s2.getName() + ": Do you have a pleasant learning experience ? /Please enter 'TRUE' or 'FALSE'");
        String ans = sc.next();

        s1.addFriend(s2, 0);
        if(goodImpression(ans)){
            s2.addFriend(s1,10);
            System.out.println(s2.getName() + " become " + s1.getName() + " friends and give reputation points to his/her is 10");
        }
        else{
            s2.addFriend(s1,2);
            System.out.println(s2.getName() + " become " + s1.getName() + " friends and give reputation points to his/her is 2");
        }
    }

    public boolean goodImpression(String ans){
        if(ans.equals("TRUE"))
            return true;
        else if (ans.equals("FALSE"))
            return false;
        else
            return false;
    }
}