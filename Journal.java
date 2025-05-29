public class Journal {
    private int ID;
    private int studentID;
    private int subjectID;
    private int grade;
    private int teacherID;
    private int groupID;

    public Journal(){}


    public Journal(int ID, int studentID, int subjectID, int grade) {
        this.ID = ID;
        this.studentID = studentID;
        this.subjectID = subjectID;
        this.grade = grade;
    }


    public int getID() {
        return ID;
    }

    public int getStudentID() {
        return studentID;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public int getGrade() {
        return grade;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public int getGroupID() {
        return groupID;
    }


    public void setID(int ID) {
        this.ID = ID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }
}
