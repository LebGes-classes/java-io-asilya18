import org.apache.poi.ss.usermodel.Sheet;
import java.util.List;

public interface SchoolBook {
    Sheet getSheet(String name);
    List<Student> getStudents();
    List<Teacher> getTeachers();
    void close();
}
