import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class HerdImmunity {
    private ArrayList<Student> StudentsToVaccine;
    private ArrayList<Student> students;

    public HerdImmunity(ArrayList<Student> students, int vaccine) {
        StudentsToVaccine = new ArrayList<>(vaccine);
        this.students = new ArrayList<>(List.copyOf(students));
        vaccinate(vaccine);
        System.out.println("Students need to vaccine: ");
        System.out.println(StudentsToVaccine.toString());
    }

    private void vaccinate(int number) {
        //stopping condition
        if (number < 1) {
            return;
        }
        //the student who has the most friends is the most likely to spread virus, so they should be at high priority
        Optional<Student> toVaccine = students.stream()
                .filter(student -> !student.isVaccinated())
                .max(new Comparator<Student>() {
                    @Override
                    public int compare(Student s1, Student s2) {
                        return s1.getFriend().size() - s2.getFriend().size();
                    }
                });

        //If there is a result
        if (toVaccine.isPresent()) {
            toVaccine.get().setVaccinated(true);
            StudentsToVaccine.add(toVaccine.get());
        }
        //If no result, means the students list is empty in this case
        else
            System.out.println("No students yet");
        //recursive call
        vaccinate(number-1);
    }

    public ArrayList<Student> getStudentsToVaccine() {
        return StudentsToVaccine;
    }

}
