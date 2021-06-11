import java.util.ArrayList;
import java.util.Scanner;

public class Event2{
    private Student s1;
    private Student s2;
    private Student s3;


    Event2(Student s1, Student s2, Student s3) {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
    }


    public void runEvent2() {
        Scanner sc=new Scanner(System.in);
        System.out.println(s1.getName() + " talk to " + s3.getName() + ": Did you heard any bad message about me ? /Please enter 'TRUE' or 'FALSE'");
        String ans = sc.next();
        System.out.println("Reputation points " + s2.getName() + " to " + s1.getName() + " is " + getBToAPoint());

        s3.addFriend(s1,0);
        if(badMessageOrNot(ans)){
            s1.addFriend(s3, getBToAPoint()*-1);
            System.out.println("During chit-chatting, " + s2.getName() + " spread bad message about " + s1.getName() + " to " + s3.getName());
            System.out.println("Therefore, " + s3.getName() + " only give negative of the reputation points of (" + s2.getName() + " to " + s1.getName() + ") to " + s1.getName() + " which is " + (getBToAPoint()*-1));
        }
        else{
            s1.addFriend(s3, getBToAPoint()/2);
            System.out.println("During chit-chatting, " + s2.getName() + " didn't spread bad message about " + s1.getName() + " to " + s3.getName());
            System.out.println("Therefore, " + s3.getName() + " give half of the reputation points of (" + s2.getName() + " to " + s1.getName() + ") to " + s1.getName() + " which is " + (getBToAPoint()/2));
        }
    }

    public int getBToAPoint(){
        ArrayList<Friend> s1Friend = s1.getFriend();
        for(int i=0; i< s1Friend.size(); i++){
            if(s1Friend.get(i).getFriend() == s2){
                return s1Friend.get(i).getRepPoints();
            }
        }
        return 0;
    }

    public boolean badMessageOrNot(String ans){
        if(ans.equals("TRUE"))
            return true;
        else if (ans.equals("FALSE"))
            return false;
        else
            return false;
    }
}
