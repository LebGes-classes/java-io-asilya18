public class Grade {
    private int ID;
    private String name;
    private int studentID;
    private int subjectID;

    public Grade(){}

    public Grade(int ID, String name, int studentID, int subjectID) {
        this.ID = ID;
        this.name = name;
        this.studentID = studentID;
        this.subjectID = subjectID;
    }

    public int getID(){
        return ID;
    }
    public String getName(){
        return name;
    }
    public int getStudentID(){
        return studentID;
    }
    public int getSubjectID(){
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public void setName(String name) {
        this.name = name;
    }
}
