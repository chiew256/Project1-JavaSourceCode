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
    public Student getF() {
        return f;
    }

    public void setF(Student f) {
        this.f = f;
    }

    public int getRepPoints() {
        return repPoints;
    }

    public void setRepPoints(int repPoints) {
        this.repPoints = repPoints;
    }
}