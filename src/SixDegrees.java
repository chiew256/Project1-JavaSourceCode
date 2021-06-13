import java.util.ArrayList;
import java.util.Random;

public class SixDegrees {
    private FriendshipGraph fGraph;
    private ArrayList<ArrayList<Student>> allPaths = new ArrayList<>();

    SixDegrees(FriendshipGraph fGraph){
        this.fGraph = fGraph;
    }

    public void runSixDegrees() {
        addKen();
        System.out.println(fGraph.toString());
        dfs(fGraph.getStudent("v1"), fGraph.getStudent(fGraph.getSize() - 1), new ArrayList<>());
        check();
    }

    public void check(){
        if (allPaths.size() == 0) {
            System.out.println("There is no connection !");
        } else {
            int length = allPaths.get(0).size();
            int index = 0;
            System.out.println("All possible paths : ");
            for (int i = 0; i < allPaths.size(); i++) {
                System.out.println(allPaths.get(i).toString());
                if (allPaths.get(i).size() < length) {
                    length = allPaths.get(i).size();
                    index = i;
                }
            }
            System.out.println("Shortest path : " + allPaths.get(index));
            System.out.println("hop: " + (length - 2));
            if (length - 2 > 6) {
                System.out.println("The six degrees of Ken Thompson theory is not proven");
            } else {
                System.out.println("The six degrees of Ken Thompson theory is proven");
            }
        }
    }

    //Ken is added
    public void addKen() {
        Random r = new Random();
        int r_num = r.nextInt(fGraph.getSize() - 1);
        System.out.println("Person connected with Ken Thompson : " + fGraph.getStudent(r_num).getName() + ".");
        fGraph.addStudent("Ken Thompson", 0, "1145", 15);
        Student Ken = fGraph.getStudent("Ken Thompson");
        fGraph.addFriend(Ken, fGraph.getStudent(r_num), 0, 0);
    }

    public void dfs(Student a, Student b, ArrayList<Student> list) {
        ArrayList<Friend> friendlist = a.friend;
        list.add(a);
        for (Friend friend : friendlist) {
            ArrayList<Student> newList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                newList.add(list.get(i));
            }
            if (friend.getFriend().equals(b)) {
                newList.add(b);
                allPaths.add(newList);
            } else {
                if (!list.contains(friend.getFriend())) {
                    dfs(friend.getFriend(), b, newList);
                }
            }
        }
    }
}