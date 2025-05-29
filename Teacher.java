public class Teacher {
    private int ID;
    private String name;

    public Teacher() {

    }

    public Teacher(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public int getID(){
        return ID;
    }
    public String getName(){
        return name;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }
}
