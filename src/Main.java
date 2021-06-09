public class Main {
    private static FriendshipGraph graph = new FriendshipGraph();
    public static void main(String[]args){
        initializationFriendshipGraph();

        // ================================= Event 3 =============================
        System.out.println("------------------------Event 3-----------------------");
        Event3 e3 = new Event3(graph.getStudent("v10"), graph.getVertex(), false);
        graph.getStudent("v10").getFriend().stream().forEach(friend -> System.out.println(friend.getFriend().getName() + " " + friend.getRepPoints()));

        // ================================= Event 5 =============================
        Event5 event5 = new Event5();
        System.out.println("------------------------Event 5-----------------------");
        event5.checKRumour(graph.getStudent("v2"), graph.getStudent("v5"));
    }

    public static void initializationFriendshipGraph(){
        graph.addStudent("v1",10, "1145", 15);
        graph.addStudent("v2",15, "1315", 30);
        graph.addStudent("v3",20, "1200", 30);
        graph.addStudent("v4",25, "1215", 15);
        graph.addStudent("v5",30, "1215", 25);
        graph.addStudent("v6",35, "1100", 15);
        graph.addStudent("v7",40, "1200", 30);
        graph.addStudent("v8",45, "1230", 20);
        graph.addStudent("v9",50, "1215", 25);
        graph.addStudent("v10",5, "1215", 30);


        graph.addFriend(graph.getStudent("v1"), graph.getStudent("v2"), 8, 10);
        graph.addFriend(graph.getStudent("v1"), graph.getStudent("v7"), 4, 3);
        graph.addFriend(graph.getStudent("v2"), graph.getStudent("v3"), 5, 6);
        graph.addFriend(graph.getStudent("v2"), graph.getStudent("v5"), 4, 8);
        graph.addFriend(graph.getStudent("v2"), graph.getStudent("v6"), 7, 9);
        graph.addFriend(graph.getStudent("v4"), graph.getStudent("v8"),7,3);
        graph.addFriend(graph.getStudent("v4"), graph.getStudent("v10"), 5, 1);
        graph.addFriend(graph.getStudent("v9"), graph.getStudent("v10"), 3, 2);
    }
}
