import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Event5 {
    private ArrayList<ArrayList<Student>> path = new ArrayList<>();
    private HashMap<Student,Integer> blocked = new HashMap<>();
    private int day = 1;

    public  void checkRumour(Student source, Student crush) {
        path.clear();

        System.out.println(source.getName() + " is the source to spread the rumour");
        System.out.println(crush.getName() + " is the crush" + "\n");
        getAllPath(source, crush, new ArrayList<Student>());
        if(path.size()==0){
            System.out.println("Source " + source.getName() + " and crush " + crush.getName() + " are both in different cluster and there is no connection");
            System.out.println("Impossible to arrive at crush");
            return;
        }

        System.out.println("Path of spreading the rumour");
        for (int i=0; i<path.size(); i++) {
            System.out.print("Path " + (i+1) + " : ");
            for (int j=0; j<path.get(i).size(); j++) {
                if(j == path.get(i).size()-1){
                    System.out.print(path.get(i).get(j).getName());
                }else{
                    System.out.print(path.get(i).get(j).getName()+" --> ");
                }

            }
            System.out.println("");
        }
        System.out.println();

        while(!path.isEmpty()){
            int min_index = 0;
            int min = path.get(min_index).size();
            for (int i = 0; i < path.size(); i++) {
                if(path.get(i).size() < min){
                    min_index = i;
                    min = path.get(i).size();
                }
                if(path.get(i).get(day).equals(crush)){
                    System.out.println();
                    System.out.println(path.get(i).get(day-1).getName() + " will spread to " + crush.getName() + " on Day " + day);
                    System.out.println("Impossible to block the rumor !");
                    return;
                }
            }
            if(!blocked.containsKey(path.get(min_index).get(day))){
                blocked.put(path.get(min_index).get(day), (min_index+1));
            }
            for (int i = 0; i < path.size(); i++) {
                if(blocked.containsKey(path.get(i).get(day))){
                    path.remove(i);
                    i--;
                }
            }
            day++;
        }
        System.out.println("The person convinced are : ");
        Iterator<Map.Entry<Student, Integer>> itr = blocked.entrySet().iterator();
        int dayCounter = 1;
        while (itr.hasNext()) {
            Map.Entry<Student, Integer> set = (Map.Entry<Student, Integer>) itr.next();
            System.out.println(set.getKey().getName() + " convinced on Day " + dayCounter);
            System.out.println("Path " + set.getValue() +  " terminated\n");
            dayCounter++;
        }
        System.out.println("The rumor is blocked successful");
    }


    public void getAllPath(Student source, Student crush, ArrayList<Student> arr){
        ArrayList<Friend> f = source.friend;
        arr.add(source);

        for (int i = 0; i < f.size(); i++) {
            if(f.get(i).getFriend().equals(crush)){
                ArrayList<Student> temp = new ArrayList<>();
                for (int j = 0; j < arr.size(); j++) {
                    temp.add(arr.get(j));
                }
                temp.add(crush);
                path.add(temp);
            }

            else{
                if(!arr.contains(f.get(i).getFriend())){
                    ArrayList<Student> temp = new ArrayList<>();
                    for (int j = 0; j < arr.size(); j++) {
                        temp.add(arr.get(j));
                    }
                    // === Recursive the method to get all the friend on the current student ===
                    getAllPath(f.get(i).getFriend(), crush, temp);
                }
            }
        }
    }
}
