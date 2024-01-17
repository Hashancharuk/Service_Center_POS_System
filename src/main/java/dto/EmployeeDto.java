package dto;

public class EmployeeDto {
    private String employeeId;
    private String employeeName;
    private String employeeEmail;
    private String employeePassword;
    private String employeeContNumber;
    private String employeeAddress;

    public EmployeeDto(){

    }

    public EmployeeDto(String employeeId, String employeeName, String employeeEmail, String employeePassword, String employeeContNumber, String employeeAddress) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeEmail = employeeEmail;
        this.employeePassword = employeePassword;
        this.employeeContNumber = employeeContNumber;
        this.employeeAddress = employeeAddress;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }

    public String getEmployeeContNumber() {
        return employeeContNumber;
    }

    public void setEmployeeContNumber(String employeeContNumber) {
        this.employeeContNumber = employeeContNumber;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    @Override
    public String toString() {
        return "EmployeeDto{" +
                "employeeId='" + employeeId + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", employeeEmail='" + employeeEmail + '\'' +
                ", employeePassword='" + employeePassword + '\'' +
                ", employeeContNumber='" + employeeContNumber + '\'' +
                ", employeeAddress='" + employeeAddress + '\'' +
                '}';
    }
}
