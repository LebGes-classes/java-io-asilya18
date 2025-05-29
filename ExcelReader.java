import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader implements SchoolBook {
    private final String filePath;
    private Workbook workbook;
    private FileInputStream fis;

    public ExcelReader(String filePath) {
        this.filePath = filePath;
    }

    public List<Student> readStudents() {
        List<Student> students = new ArrayList<>();
        FileInputStream fis = null; // поток ввода файла
        DataFormatter formatter = new DataFormatter();


        try {
            fis = new FileInputStream(filePath); // этот объект открывает файл по пути filePath
            Workbook workbook = new XSSFWorkbook(fis); // объект - школьный журнал (файл xlsx)
            // я использую конкретную реализацию встроенного в apache poi интерфейса Workbook ,
            // так как я работаю только с файлами .xlsx.

            Sheet sheet = workbook.getSheet("Students"); //ссылка на лист со студентами из workbook
            if (sheet == null) {
                System.err.println("Лист 'Students' не найден в файле");
                return students;
            }

            for (Row row : sheet) {  // проход по каждой строке листа excel
                // интерфейс row (имеет метод getRowNum) встроен в библиотеку apache poi
                if (row.getRowNum() == 0) {
                    continue;
                }

                if (row.getPhysicalNumberOfCells() == 0) {
                    continue; // проверка пустой строки
                }

                Cell idCell = row.getCell(0);
                Cell nameCell = row.getCell(1);
                Cell groupCell = row.getCell(2);

                if (idCell == null || nameCell == null || groupCell == null) {
                    System.err.println("Пропуск строки" + row.getRowNum() + ": отсутствуют данные");
                    continue;
                }

                Student student = new Student();
                student.setID((int) idCell.getNumericCellValue());
                student.setName(formatter.formatCellValue(nameCell).trim());
                /* метод getNumericCellValue() - получение double числового значения ячейки -
                встроен в cell */
                student.setGroupID((int) groupCell.getNumericCellValue());
                students.add(student);
            }

            workbook.close();

        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());

        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    System.err.println("Ошибка при закрытии потока: " + e.getMessage());
                }
            }
        }
        return students;
    }


    public List<Teacher> readTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        FileInputStream fis = null;
        DataFormatter formatter = new DataFormatter();

        try {
            fis = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet("Teachers");

            if (sheet == null) {
                System.err.println("Лист 'Teachers' не найден в файле");
                return teachers;
            }

            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue;
                }

                if (row.getPhysicalNumberOfCells() == 0) {
                    continue; // проверка пустой строки
                }

                Cell idCell = row.getCell(0);
                Cell nameCell = row.getCell(1);

                if (idCell == null || nameCell == null) {
                    System.err.println("Пропуск строки" + row.getRowNum() + ": отсутствуют данные");
                    continue;
                }

                Teacher teacher = new Teacher();
                teacher.setID((int) idCell.getNumericCellValue());
                teacher.setName(formatter.formatCellValue(nameCell).trim());
                teachers.add(teacher);
            }

            workbook.close();

        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    System.err.println("Ошибка при закрытии потока: " + e.getMessage());
                }
            }
        }
        return teachers;
    }

    public List<Grade> readGrades() {
        List<Grade> grades = new ArrayList<>();
        DataFormatter formatter = new DataFormatter();

        try {
            fis = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet("Grades");

            if (sheet == null) {
                System.err.println("Лист 'Grades' не найден в файле");
                return grades;
            }

            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue;
                }

                if (row.getPhysicalNumberOfCells() == 0) {
                    continue; // проверка пустой строки
                }

                Cell idCell = row.getCell(0);
                Cell nameCell = row.getCell(1);
                Cell studentIdCell = row.getCell(2);
                Cell subjectIdCell = row.getCell(3);

                if (idCell == null || nameCell == null || studentIdCell == null || subjectIdCell == null) {
                    System.err.println("Пропуск строки" + row.getRowNum() + ": отсутствуют данные");
                    continue;
                }


                Grade grade = new Grade();
                grade.setID((int) idCell.getNumericCellValue());
                grade.setName(formatter.formatCellValue(nameCell).trim());
                grade.setStudentID((int) studentIdCell.getNumericCellValue());
                grade.setSubjectID((int) subjectIdCell.getNumericCellValue());

                grades.add(grade);
            }

        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    System.err.println("Ошибка при закрытии потока: " + e.getMessage());
                }
            }
        }
        return grades;
    }

    public List<Subject> readSubjects() {
        List<Subject> subjects = new ArrayList<>();
        DataFormatter formatter = new DataFormatter();

        try {
            fis = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet("Subjects");

            if (sheet == null) {
                System.err.println("Лист 'Subjects' не найден в файле");
                return subjects;
            }

            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue;
                }

                if (row.getPhysicalNumberOfCells() == 0) {
                    continue; // проверка пустой строки
                }

                Cell idCell = row.getCell(0);
                Cell nameCell = row.getCell(1);

                if (idCell == null || nameCell == null) {
                    System.err.println("Пропуск строки" + row.getRowNum() + ": отсутствуют данные");
                    continue;
                }


                Subject subject = new Subject();
                subject.setID((int) idCell.getNumericCellValue());
                subject.setName(formatter.formatCellValue(nameCell).trim());

                subjects.add(subject);
            }

        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    System.err.println("Ошибка при закрытии потока: " + e.getMessage());
                }
            }
        }
        return subjects;
    }

    public List<Group> readGroups() {
        List<Group> groups = new ArrayList<>();
        DataFormatter formatter = new DataFormatter();
        try {
            fis = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet("Groups");

            if (sheet == null) {
                System.err.println("Лист 'Groups' не найден в файле");
                return groups;
            }

            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue;
                }

                if (row.getPhysicalNumberOfCells() == 0) {
                    continue; // проверка пустой строки
                }

                Cell idCell = row.getCell(0);
                Cell nameCell = row.getCell(1);

                if (idCell == null || nameCell == null) {
                    System.err.println("Пропуск строки" + row.getRowNum() + ": отсутствуют данные");
                    continue;
                }

                Group group = new Group();
                group.setID((int) idCell.getNumericCellValue());
                group.setName(formatter.formatCellValue(nameCell).trim());
                groups.add(group);
            }

        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    System.err.println("Ошибка при закрытии потока: " + e.getMessage());
                }
            }
        }
        return groups;
    }

    public List<Journal> readJournals() {
        List<Journal> journals = new ArrayList<>();
        try {
            fis = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet("Journals");

            if (sheet == null) {
                System.err.println("Лист 'Journals' не найден в файле");
                return journals;
            }

            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue;
                }

                if (row.getPhysicalNumberOfCells() == 0) {
                    continue; // проверка пустой строки
                }

                Cell idCell = row.getCell(0);
                Cell studentCell = row.getCell(1);
                Cell subjectCell = row.getCell(2);
                Cell gradeCell = row.getCell(0);
                Cell teacherCell = row.getCell(1);
                Cell groupCell = row.getCell(2);

                if (idCell == null || gradeCell == null || studentCell == null || subjectCell == null || groupCell == null || teacherCell == null) {
                    System.err.println("Пропуск строки" + row.getRowNum() + ": отсутствуют данные");
                    continue;
                }

                Journal journal = new Journal();

                journal.setID((int) idCell.getNumericCellValue());
                journal.setStudentID((int) studentCell.getNumericCellValue());
                journal.setSubjectID((int) subjectCell.getNumericCellValue());
                journal.setGrade((int) gradeCell.getNumericCellValue());
                journal.setTeacherID((int) teacherCell.getNumericCellValue());
                journal.setGroupID((int) groupCell.getNumericCellValue());

                journals.add(journal);
            }

        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    System.err.println("Ошибка при закрытии потока: " + e.getMessage());
                }
            }
        }
        return journals;
    }

    @Override
    public Sheet getSheet(String name) {
        return workbook.getSheet(name);
    }

    @Override
    public List<Student> getStudents() {
        return readStudents();
    }

    @Override
    public List<Teacher> getTeachers() {
        return readTeachers();
    }

    @Override
    public void close() {
        try {
            if (workbook != null) {
                workbook.close();
            }
            if (fis != null) {
                fis.close();
            }
        } catch (IOException e) {
            System.err.println("Ошибка при закрытии ресурсов: " + e.getMessage());
        }
    }
}
