package model;

public class House {
    private String houseNo;
    private String contactNo;
    private String houseOwner;

    public House(String houseNo,String contactNo,String houseOwner)
    {
        this.houseNo = houseNo;
        this.contactNo = contactNo;
        this.houseOwner = houseOwner;
    }
    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }


    @Override
    public String toString()
    {
        return "House : "+this.houseNo+" Owner Name "+this.houseOwner;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getHouseOwner() {
        return houseOwner;
    }

    public void setHouseOwner(String houseOwner) {
        this.houseOwner = houseOwner;
    }
}
