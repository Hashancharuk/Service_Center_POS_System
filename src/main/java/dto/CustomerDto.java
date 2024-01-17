package dto;

import entity.Customer;

public class CustomerDto {
    private String id;
    private String Name;
    private String Email;
    private String ContactNumber;
    private String Address;

    public CustomerDto(String id, String name, String email, String contactNumber, String address) {
        this.id = id;
        this.Name = name;
        this.Email = email;
        this.ContactNumber = contactNumber;
        this.Address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getContactNumber() {
        return ContactNumber;
    }

    public void setContactNumber(String contactNumber) {
        ContactNumber = contactNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    @Override
    public String toString() {
        return "CustomerDto{" +
                "id='" + id + '\'' +
                ", Name='" + Name + '\'' +
                ", Email='" + Email + '\'' +
                ", ContactNumber='" + ContactNumber + '\'' +
                ", Address='" + Address + '\'' +
                '}';
    }

}
