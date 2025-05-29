import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DataLinker {

    public static void linkAllData(List<Student> students, List<Group> groups, List<Teacher> teachers,
                                   List<Subject> subjects, List<Grade> grades, List<Journal> journals) {
        linkStudentsToGroups(students, groups);
        linkGradesToStudents(grades, students);
        linkGradesToSubjects(grades, subjects);
        linkJournalsToTeachers(journals, teachers);
        linkJournalsToGroups(journals, groups);
        linkJournalsToSubjects(journals, subjects);

    } // основной метод для удобства , чтобы не вызывать кучу разных методов

    private static void linkStudentsToGroups(List<Student> students, List<Group> groups) {
        for (Group group : groups) {
            List<Student> groupStudents = students.stream()
                    // создаем поток из списка студентов
                    .filter(s -> s.getGroupID() == group.getID())
                    // лямбда-выражение для проверки совпадения ID группы у студентов
                    .toList(); // собираем студентов одной группы в список groupStudents
        }
    }

    private static void linkGradesToStudents(List<Grade> grades, List<Student> students) {
        for (Student student : students) {
            List<Grade> studentGrades = grades.stream()
                    .filter(g -> g.getStudentID() == student.getID())
                    .toList();
        }
    }

    private static void linkGradesToSubjects(List<Grade> grades, List<Subject> subjects) {
        for (Subject subject : subjects) {
            List<Grade> subjectGrades = grades.stream()
                    .filter(g -> g.getSubjectID() == subject.getID())
                    .toList();
        }
    }

    private static void linkJournalsToTeachers(List<Journal> journals, List<Teacher> teachers) {
        for (Teacher teacher : teachers) {
            List<Journal> teacherJournals = journals.stream()
                    .filter(j -> j.getTeacherID() == teacher.getID())
                    .toList();
        }
    }

    private static void linkJournalsToGroups(List<Journal> journals, List<Group> groups) {
        for (Group group : groups) {
            List<Journal> groupJournals = journals.stream()
                    .filter(j -> j.getGroupID() == group.getID())
                    .toList();
        }
    }

    private static void linkJournalsToSubjects(List<Journal> journals, List<Subject> subjects) {
        for (Subject subject : subjects) {
            List<Journal> subjectJournals = journals.stream()
                    .filter(j -> j.getSubjectID() == subject.getID())
                    .toList();

        }
    }

    public static Map<String, Object> prepareForJsonExport(
            List<Student> students,
            List<Group> groups,
            List<Teacher> teachers,
            List<Subject> subjects,
            List<Grade> grades,
            List<Journal> journals
    ) {
        // мои данные уже связаны между собой в классе mainApp
        Map<String, Object> jsonData = new LinkedHashMap<>(); // такая коллекция для логического сохранения полей
        jsonData.put("students", students);
        jsonData.put("groups", groups);
        jsonData.put("teachers", teachers);
        jsonData.put("subjects", subjects);
        jsonData.put("grades", grades);
        jsonData.put("journals", journals);

        return jsonData;
    }

}