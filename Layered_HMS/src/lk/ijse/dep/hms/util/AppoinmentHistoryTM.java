package lk.ijse.dep.hms.util;

public class AppoinmentHistoryTM {

    private String appoinmentID;
    private String patientID;
    private String fname;
    private String lname;
    private String gender;
    private String email;

    public AppoinmentHistoryTM() {
    }

    public AppoinmentHistoryTM(String appoinmentID, String patientID, String fname, String lname, String gender, String email) {
        this.appoinmentID = appoinmentID;
        this.patientID = patientID;
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.email = email;
    }

    public String getAppoinmentID() {
        return appoinmentID;
    }

    public void setAppoinmentID(String appoinmentID) {
        this.appoinmentID = appoinmentID;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
