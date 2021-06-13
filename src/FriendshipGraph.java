import java.util.ArrayList;

public class FriendshipGraph {
    private ArrayList<Student> vertex;
    private int size = 0;

    public FriendshipGraph(){
        vertex = new ArrayList<>();
    }

    public void addStudent(String name, int divingRate, String lunchTime,int lunchPeriod){
        Student p = new Student(name, divingRate, lunchTime, lunchPeriod);
        vertex.add(p);
        size++;
    }

    public boolean addStudent(String name){
        if (!hasStudent(name))	{
            Student newStudent = new Student(name);
            vertex.add(newStudent);
            size++;
            return true;
        }
        else
            return false;
    }

    public boolean hasStudent(String name)	{
        for (int i = 0; i < vertex.size(); i++) {
            if(vertex.get(i).getName().equals(name)){
                return true;
            }
        }
        return false;
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
        if(!hasFriend(p1,p2)) {
            p1.addFriend(p2, repPointsP2ToP1);
            p2.addFriend(p1, repPointsP1ToP2);
        }
    }

    // EXTRA FOR EVENT 6
    public Student[] toArray(){
        Student[] s = new Student[vertex.size()];
        for (int i = 0; i < vertex.size(); i++) {
            s[i]= vertex.get(i);
        }
        return s;
    }

    // Ask whether need check has friend
    public boolean hasFriend(Student source, Student destination){
        for (int i = 0; i < vertex.size(); i++) {
            if(vertex.get(i).equals(source)){
                if(vertex.get(i).friend.contains(destination))
                    return true;
            }
        }
        return false;
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

    public Student getStudent(int index){
        for (int i = 0; i < vertex.size(); i++) {
            if(i == index){
                return vertex.get(i);
            }
        }
        return null;
    }

    /** ====================================== Getter and Setter ====================================*/
    public ArrayList<Student> getVertex() {
        return vertex;
    }

    public void setVertex(ArrayList<Student> vertex) {
        this.vertex = vertex;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
