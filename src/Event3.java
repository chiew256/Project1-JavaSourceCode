import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Event3 {

    private Student student;
    private ArrayList<Student> students;

    //boolean parallelFarming = true then run parallel farming
    public Event3(Student student, ArrayList<Student> students, boolean parallelFarming) {
        this.student = student;
        this.students = students;

        if (parallelFarming) {
            ParallelFarming();
        }
        else {
            main();
        }
    }

    private void main() {
        //preprocessing
        students = sortReliability();

        System.out.println("Sorted student list with matched lunch time: ");
        System.out.println(students.toString());
        if (students.size() == 0) {
            System.out.println("No one having lunch at this time");
            return;
        }

        //implementation of event 3
        int totalRepGain = 0;
        int[] selfLunchTime = calLunchTime(student); //[start, end]
        int[] otherLunchTime, skippedLunchTime;
        Student[] skippedStudents = new Student[students.size()];
        int indexSkipped = 0, indexHandled = 0;

        for (int i = 0; i < students.size() && selfLunchTime[0] < selfLunchTime[1]; i++) {
            otherLunchTime = calLunchTime(students.get(i));

            ////if the lunch time does not match
            //*my start time is later than the student's
            if (selfLunchTime[0] >= otherLunchTime[1]) {
                continue;
            }

            //handle skippedStudent person if there is any
            if (skippedStudents[indexHandled] != null) {
                skippedLunchTime = calLunchTime(skippedStudents[indexHandled]);
                //check if the next person lunch end after this skipped student lunch end, then proceed with this student
                //check if the next person will take all my remaining time then proceed with this skipped student
                //check for case 2
                if (	otherLunchTime[1] >= skippedLunchTime[1] ||
                        otherLunchTime[1] >= selfLunchTime[1] ||
                        !(selfLunchTime[0] < skippedLunchTime[0])) {
                    System.out.println("Lunch with: " + skippedStudents[indexHandled].getName());

                    //increase rep point
                    IncreaseRepPoints(student, skippedStudents[indexHandled]);
                    totalRepGain++;
                    //update lunch time
                    selfLunchTime[0] = skippedLunchTime[1];
                    //update indexes
                    i--;
                    indexHandled++;
                    continue;
                }
            }

            //case 1 = this student lunch time start after me, go to next student first
            if (selfLunchTime[0] < otherLunchTime[0]) {
                skippedStudents[indexSkipped++] = students.get(i);
                continue;
            }

            //case 2 = this student is having lunch, i can join him.
            else {
                //increase rep point
                IncreaseRepPoints(student, students.get(i));
                totalRepGain++;
                //update lunch time
                selfLunchTime[0] = otherLunchTime[1];
                System.out.println("Lunch with: " + students.get(i).getName());
            }
        }

        //if the students loop end but the lunch time is still not finished && have student skipped
        while (selfLunchTime[0] < selfLunchTime[1] && skippedStudents[indexHandled] != null) {
            skippedLunchTime = calLunchTime(skippedStudents[indexHandled]);
            //lunch with the skipped student if his lunch is not finished yet
            if (skippedLunchTime[1] > selfLunchTime[0]) {
                System.out.println("Lunch with: " + skippedStudents[indexHandled].getName());
                //increase rep point
                totalRepGain++;
                IncreaseRepPoints(student, skippedStudents[indexHandled]);
                //update lunch time
                selfLunchTime[0] = skippedLunchTime[1];
            }
            indexHandled++;

        }

        System.out.println("Total reputation gained: " + totalRepGain);
    }

    private ArrayList<Student> sortReliability() {
        List<Student> list =
                students.stream()
                        //remove self from the list of students
                        .filter(student -> !student.equals(this.student))
                        .sorted(new Comparator<Student>() {
                            @Override
                            //sort by diving rate, rep point is different for everyone so can't consider
                            public int compare(Student o1, Student o2) {
                                return (o1.getDivingRate() - o2.getDivingRate());
                            }
                        })
                        //question say want lunch with student with high reliability
                        //assume high reliability = diving rate lower than 50
                        .filter(student -> student.getDivingRate() < 50)
                        .collect(Collectors.toList());
        ArrayList<Student> arrayList = new ArrayList<>(list);
        arrayList = matchLunchTime(arrayList);
        return arrayList;
    }

    private ArrayList<Student> matchLunchTime(ArrayList<Student> students) {
        int[] selfLunch = calLunchTime(student);
        List<Student> list =
                students.stream().filter(student -> {
                    int[] lunchTime = calLunchTime(student);
                    //whether my lunch time is within their lunch time or not
                    boolean selfStart = selfLunch[0] >= lunchTime[0] && selfLunch[0] <= lunchTime[1];
                    boolean selfEnd = selfLunch[1] >= lunchTime[0] && selfLunch[1] <= lunchTime[1];
                    //vice versa
                    boolean start = lunchTime[0] >= selfLunch[0] && lunchTime[0] <= selfLunch[1];
                    boolean end = lunchTime[1] >= selfLunch[0] && lunchTime[1] <= selfLunch[1];
                    return selfStart || selfEnd || start || end;
                })
                        .collect(Collectors.toList());
        return new ArrayList<>(list);
    }

    //[start, end]
    //return int[] but in 24 hours format
    private int[] calLunchTime(Student student) {
        int start = Integer.parseInt(student.getLunchTime());
        int period = student.getLunchPeriod();
        int end;
        if (period >= 60)
            end = start + (period/60)*100 + (period%60);
        else
            end = start + period;

        //check third digit more than 6 or not
        if (end % 100 / 10 >= 6) {
            end = end - 60 + 100;
        }

        int[] result = {start, end};
        return result;
    }

    //rep point of s1 to s2 increase by 1
    //rep point of s2 to s1 increase by 1
    private void IncreaseRepPoints(Student s1, Student s2) {
        ArrayList<Friend> f1 = s1.getFriend();
        ArrayList<Friend> f2 = s2.getFriend();
        //check if they are in the friend list or not
        boolean inFriendList1 = false;
        boolean inFriendList2 = false;

        for (int i = 0 ; i < f1.size(); i++) {
            if (f1.get(i).getFriend().equals(s2)) {
                f1.get(i).setRepPoints(f1.get(i).getRepPoints() + 1);
                inFriendList1 = true;
                break;
            }
        }
        //if s2 is not found in friend list of s1
        if (!inFriendList1) {
            //rep point start from 0 but + 1 so = 1
            s1.addFriend(s2, 1);
        }

        for (int i = 0 ; i < f2.size(); i++) {
            if (f2.get(i).getFriend().equals(s1)) {
                f2.get(i).setRepPoints(f2.get(i).getRepPoints() + 1);
                inFriendList2 = true;
                break;
            }
        }
        //if s1 is not found in friend list of s2
        if (!inFriendList2) {
            //rep point start from 0 but + 1 so = 1
            s2.addFriend(s1, 1);
        }
    }

    ////===================================Additional Challenge 4 -- Parallel Farming=============================

    //modify based on event 3, so there will be some repeating code
    //main idea : use array to track who is having lunch with me, when their lunch end, next student join
    private void ParallelFarming() {
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
