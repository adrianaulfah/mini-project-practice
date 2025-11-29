public class user {
    // Existing fields
    String name;
    String email;
    String password;
    
    // New fields
    String phone;
    String ic;
    String address;
    
    // Updated constructor (maintains backward compatibility)
    public user(String name, String email, String password) {
        this(name, email, password, "", "", ""); // Default empty values for new fields
    }
    
    // New full constructor
    public user(String name, String email, String password, String phone, String ic, String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.ic = ic;
        this.address = address;
    }
    
    // Getters and setters for new fields (optional but recommended)
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getIc() {
        return ic;
    }
    
    public void setIc(String ic) {
        this.ic = ic;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
}
