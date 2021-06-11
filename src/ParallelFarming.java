import java.util.ArrayList;

public class ParallelFarming extends Event3 {

    public ParallelFarming(Student student, ArrayList<Student> students) {
        super(student, students);
    }

////===================================Additional Challenge 4 -- Parallel Farming=============================

    //modify based on event 3, and will use the method inside event 3
    //main idea : use array to track who is having lunch with me, when their lunch end, next student join
    public void start() {
        students = sortReliability();
        System.out.println("Sorted student list: ");
        System.out.println(students.toString());
        if (students.size() == 0) {
            System.out.println("No one having lunch at this time");
            return;
        }

        //situation to consider
        //case 1 : every student has different lunch end
        //case 2 : 2 students have same lunch end
        //case 3 : 3 students have same lunch end
        int totalRepGain = 0;
        int[] selfLunchTime = calLunchTime(student); //[start, end]
        Student[] havingLunch = new Student[3]; //students who are having lunch
        //the time when student leave (time used to update lunch time)
        int lunchEnd;
        //end time for students in seat, prevent frequently calling calLunchTime
        int[] timeEnd = new int[3];

        //skipping student like in event 3 will take up very long time so not implement here
        outer:
        for(int i = 0; i < students.size() && selfLunchTime[0] < selfLunchTime[1]; i++) {
            int[] otherLunchTime = calLunchTime(students.get(i));


            ////if the lunch time does not match
            //*my start time is later than the student's
            if (selfLunchTime[0] >= otherLunchTime[1]) {
                continue;
            }

            ////check if there is any empty seat
            for (int j = 0; j < havingLunch.length; j++) {
                if (havingLunch[j] == null) {
                    havingLunch[j] = students.get(i);
                    System.out.println("Have lunch with: " + havingLunch[j].getName());
                    //increase rep point
                    IncreaseRepPoints(student, havingLunch[j]);
                    totalRepGain++;

                    continue outer;
                }
            }
            System.out.println("Table fulled");
            ////if all the seat is full
            //calculate lunchEnd
            for (int j = 0; j < timeEnd.length; j++) {
                timeEnd[j] = calLunchTime(havingLunch[j])[1];
            }

            //find the nearest end time
            lunchEnd = Math.min((Math.min(timeEnd[0], timeEnd[1])), timeEnd[2]);

            //student who end his lunch at lunchEnd leave
            for (int j = 0; j < havingLunch.length; j++) {
                if (timeEnd[j] == lunchEnd) {
                    System.out.println(havingLunch[j].getName() + " Leave");
                    havingLunch[j] = null;
                }
            }

            //update lunch time
            selfLunchTime[0] = lunchEnd;

            //i-1 so the student in this loop will not be skipped
            i--;

        }

        System.out.println("Total reputation gained: " + totalRepGain);
    }
}
