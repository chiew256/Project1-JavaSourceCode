import java.util.ArrayList;

public class Student {
    private String name;
    private int divingRate;
    private String lunchTime;
    private int lunchPeriod;
    ArrayList<Friend> friend = new ArrayList<>();

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, int divingRate, String lunchTime, int lunchPeriod){
        this.name = name;
        this.divingRate = divingRate;
        this.lunchTime = lunchTime;
        this.lunchPeriod = lunchPeriod;
    }

    public void addFriend(Student p2, int repPoints){
        Friend f = new Friend(p2, repPoints);
        friend.add(f);
    }


    /** ====================================== Getter and Setter ====================================*/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDivingRate() {
        return divingRate;
    }

    public void setDivingRate(int divingRate) {
        this.divingRate = divingRate;
    }

    public String getLunchTime() {
        return lunchTime;
    }

    public void setLunchTime(String lunchTime) {
        this.lunchTime = lunchTime;
    }

    public int getLunchPeriod() {
        return lunchPeriod;
    }

    public void setLunchPeriod(int lunchPeriod) {
        this.lunchPeriod = lunchPeriod;
    }

    public ArrayList<Friend> getFriend() {
        return friend;
    }

    public void setFriend(ArrayList<Friend> friend) {
        this.friend = friend;
    }
}