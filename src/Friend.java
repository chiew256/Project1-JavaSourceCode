public class Friend {
    private Student f ;
    private int repPoints;

    public Friend(Student f, int repPoints) {
        this.f = f;
        this.repPoints = repPoints;
    }
    public String toString(){
        return f.getName() +" Reputation Points  : " + repPoints;
    }

    /** ====================================== Getter and Setter ====================================*/
    public Student getFriend() {
        return f;
    }

    public void setFriend(Student f) {
        this.f = f;
    }

    public int getRepPoints() {
        return repPoints;
    }

    public void setRepPoints(int repPoints) {
        this.repPoints = repPoints;
    }
}