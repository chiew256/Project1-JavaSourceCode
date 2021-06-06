import java.util.ArrayList;

public class FriendshipGraph {
    private ArrayList<Student> vertex;

    public FriendshipGraph(){
        vertex = new ArrayList<>();
    }

    public void addStudent(String name, int divingRate, String lunchTime,int lunchPeriod){
        Student p = new Student(name, divingRate, lunchTime, lunchPeriod);
        vertex.add(p);
    }

    public Student getStudent(String name){
        for (int i = 0; i < vertex.size(); i++) {
            if(vertex.get(i).getName().equals(name)){
                return vertex.get(i);
            }
        }
        return null;
    }

    public void addFriend(Student p1 ,Student p2, int repPointsP1ToP2, int repPointsP2ToP1){
        p1.addFriend(p2, repPointsP2ToP1);
        p2.addFriend(p1, repPointsP1ToP2);
    }

    public String toString(){
        String str = "";
        for(int i=0; i<vertex.size(); i++){
            str += vertex.get(i).getName() + "\n";
        }
        return str;
    }

    public void getFriend(String name){
        for (int i = 0; i < vertex.size(); i++) {
            if(vertex.get(i).getName().equals(name)){
                ArrayList<Friend> friend = vertex.get(i).getFriend();
                System.out.println(friend.toString());
            }
        }
    }

    /** ====================================== Getter and Setter ====================================*/
    public ArrayList<Student> getVertex() {
        return vertex;
    }

    public void setVertex(ArrayList<Student> vertex) {
        this.vertex = vertex;
    }
}