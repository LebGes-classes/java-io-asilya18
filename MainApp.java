        import com.fasterxml.jackson.databind.ObjectMapper;

        import java.io.*;
        import java.util.*;


        public class MainApp {
            private static final String JSON_DIR = "json_output/";
            // директория для json
            private static final String EXCEL_FILE = "/Users/asilyashakirova/Desktop/journal_data.xlsx";
            private static List<Student> students;
            private static List<Grade> grades;
            private static List<Subject> subjects;
            private static List<Group> groups;
            private static List<Teacher> teachers;
            private static List<Journal> journals;
            private static ExcelReader excelReader;
            private static Scanner scanner = new Scanner(System.in);

            public static void main(String[] args) {
                new File(JSON_DIR).mkdirs();
                try {
                    excelReader = new ExcelReader(EXCEL_FILE);
                    students = excelReader.readStudents();
                    teachers = excelReader.readTeachers();
                    grades = excelReader.readGrades();
                    subjects = excelReader.readSubjects();
                    groups = excelReader.readGroups();
                    journals = excelReader.readJournals();

                    DataLinker.linkAllData(students, groups, teachers, subjects, grades, journals);
                    runMenu();

                } catch (Exception e) {
                    System.out.println("Ошибка: " + e.getMessage());
                } finally {
                    scanner.close();
                }
            }

            private static void runMenu() throws Exception {
                while (true) { // цикл бесконечен для отображения меню
                    System.out.println(" \n 1. Найти студента \n 2. Добавить оценку \n 3. Выход");
                    String choice = scanner.nextLine();

                    switch (choice) {
                        case "1":
                            showGradesOfStudent();
                            break;
                        case "2":
                            addGrade();
                            break;
                        case "3":
                            break;
                        default:
                            System.out.println("Неверный ввод!");
                            break;
                    }
                }
            }

            private static void showGradesOfStudent() {
                System.out.print("Введите фамилию: ");
                String name = scanner.nextLine();

                for (Student student : students) {
                    if (student.getName().toLowerCase().contains(name.toLowerCase())) {
                        System.out.println(" \n Студент: " + student.getName());

                        List<Grade> studentGrades = grades.stream()
                                .filter(g -> g.getStudentID() == student.getID())
                                // метод отбирает элементы по условию
                                .toList(); // поток -> коллекция
                        if (studentGrades.isEmpty()) { // метод collection
                            System.out.println("Нет оценок");
                            continue;
                        }

                        for (Grade grade : studentGrades) {
                            String subjectName = subjects.stream()
                                    .filter(s -> s.getID() == grade.getSubjectID())
                                    .map(Subject::getName)
                                    // method reference (заменила по подсказке ii)
                                    .findFirst() //возврат первого элемента потока
                                    .orElse("Неизвестный предмет"); // предмета в потоке нет
                            // это все конвейерные операции

                            System.out.println(subjectName + ": " + grade.getName());
                            // выводим оценку с предметом
                        }
                    }
                }
            }

            private static void addGrade() throws Exception {
                System.out.println(" \n Студенты:");
                for (int i = 0; i < students.size(); i++) {
                    System.out.println((i + 1) + ". " + students.get(i).getName());
                    // выводим список студентов с нумерацией
                }

                System.out.print("Выберите студента (введите его номер) : ");
                int studentNum = Integer.parseInt(scanner.nextLine()) - 1;
                // метод - преобразование введенной строки в число

                System.out.println("Предметы:");
                for (int i = 0; i < subjects.size(); i++) {
                    System.out.println((i + 1) + ". " + subjects.get(i).getName());
                    // выводим список предметов с нумерацией
                }

                System.out.print("Выберите предмет (введите его номер) : ");
                int subjectNum = Integer.parseInt(scanner.nextLine()) - 1;

                System.out.print("Введите оценку: ");
                String gradeName = scanner.nextLine();

                Grade newGrade = new Grade();

                newGrade.setID(grades.size() + 1); // создаем новый ID , просто используя новый уникальный размер списка
                newGrade.setName(gradeName);
                newGrade.setStudentID(students.get(studentNum).getID());
                // оценка конкретному студенту
                newGrade.setSubjectID(subjects.get(subjectNum).getID());
                // оценка по конкретному предмету

                grades.add(newGrade); // добавляем в конец списка новую оценку

                String studentName = students.get(studentNum).getName();
                String subjectName = subjects.get(subjectNum).getName();

                Map<String, String> gradeInfo = new HashMap<>();
                gradeInfo.put("studentName", studentName);
                gradeInfo.put("subjectName", subjectName);
                gradeInfo.put("grade", gradeName);

                System.out.println("Оценка добавлена!");
                saveDataJson(gradeInfo);
            }

            private static void saveDataJson(Map<String, String> gradeInfo) throws Exception {
                SerializationJson serializationJson = new SerializationJson(new FileOutputStream(JSON_DIR + "grades.json"));

                serializationJson.writeObject(grades);
                ObjectMapper objectMapper = new ObjectMapper();

                String jsonOutput = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(gradeInfo);
                System.out.println("Данные в формате JSON:");
                System.out.println(jsonOutput);
                serializationJson.close();
            }
        }
