import java.util.ArrayList;

public class Main {
    private static FriendshipGraph graph = new FriendshipGraph();
    public static void main(String[]args){
        initializationFriendshipGraph();

        /**System.out.println("================================= Event 1 =============================");
        Event1 e1 = new Event1(graph.getStudent("v1"), graph.getStudent("v4"));
        e1.runEvent1();*/

        /**System.out.println("\n================================= Event 2 =============================");
        Event2 e2 = new Event2(graph.getStudent("v1"), graph.getStudent("v2"), graph.getStudent("v3"));
        e2.runEvent2();

        Event2 test = new Event2(graph.getStudent("v1"), graph.getStudent("v3"), graph.getStudent("v4"));
        test.runEvent2();*/

        /**System.out.println("\n================================= Event 3 =============================");
        Event3 e3 = new Event3(graph.getStudent("v10"), graph.getVertex());
        e3.start();*/

        /**System.out.println("\n================================= Event 3 =============================");
        Event4 e4 = new Event4();
        e4.runEvent4();*/

        /**System.out.println("\n================================= Event 5 =============================");
        Event5 event5 = new Event5();
        event5.checkRumour(graph.getStudent("v2"), graph.getStudent("v5"));*/

        /**System.out.println("\n================================= Event 6 =============================");
        Event6 e6 = new Event6();
        e6.runEvent6();*/

        /**System.out.println("\n================================= Parallel Farming (Extra Features) =============================");
        ParallelFarming parallelFarming = new ParallelFarming(graph.getStudent("v10"), graph.getVertex());
        parallelFarming.start();

        System.out.println("\n================================= Herd Immunity (Extra Features) =============================");
        HerdImmunity herdImmunity = new HerdImmunity(graph.getVertex(), 3);
        herdImmunity.start();*/
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
