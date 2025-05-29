public class Student {
    private int ID;
    private String name;
    private int groupID;

    public Student(){}

    public Student(int ID, String name, int groupID) {
        this.ID = ID;
        this.name = name;
        this.groupID = groupID;
    }

    public int getID(){
        return ID;
    }
    public String getName(){
        return name;
    }
    public int getGroupID(){
        return groupID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }
}
