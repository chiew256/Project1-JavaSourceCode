public class Main {
    private static FriendshipGraph r = new FriendshipGraph();
    public static void main(String[]args){
        initializationFriendshipGraph();
    }

    public static void initializationFriendshipGraph(){
        r.addStudent("v1",10, "1145", 15);
        r.addStudent("v2",15, "1315", 30);
        r.addStudent("v3",20, "1200", 30);
        r.addStudent("v4",25, "1215", 15);
        r.addStudent("v5",30, "1215", 25);
        r.addStudent("v6",35, "1100", 15);
        r.addStudent("v7",40, "1200", 30);
        r.addStudent("v8",45, "1230", 20);
        r.addStudent("v9",50, "1215", 25);
        r.addStudent("v10",5, "1215", 30);


        r.addFriend(r.getStudent("v1"), r.getStudent("v2"), 8, 10);
        r.addFriend(r.getStudent("v1"), r.getStudent("v7"), 4, 3);
        r.addFriend(r.getStudent("v2"), r.getStudent("v3"), 5, 6);
        r.addFriend(r.getStudent("v2"), r.getStudent("v5"), 4, 8);
        r.addFriend(r.getStudent("v2"), r.getStudent("v6"), 7, 9);
        r.addFriend(r.getStudent("v4"), r.getStudent("v8"),7,3);
        r.addFriend(r.getStudent("v4"), r.getStudent("v10"), 5, 1);
        r.addFriend(r.getStudent("v9"), r.getStudent("v10"), 3, 2);
    }
}
