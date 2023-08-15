package model;

public class Visitor {
    private String name;
    private long id;
    private String contactNumber;
    private String houseNo;
    private String entryTime;
    private String exitTime;

    public int getVisitStatus() {
        return visitStatus;
    }

    public void setVisitStatus(int visitStatus) {
        this.visitStatus = visitStatus;
    }

    private int visitStatus;
    public Visitor(long id,String name,String contact,String houseNo,String entry){
        this.id = 10000+id;
        this.name=name;
        this.contactNumber=contact;
        this.houseNo = houseNo;
        this.entryTime =entry;
    }
    @Override
    public String toString()
    {
        return "Visitor : "+this.name+" At "+this.houseNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getExitTime() {
        return exitTime;
    }

    public void setExitTime(String exitTime) {
        this.exitTime = exitTime;
    }
}
