package lk.ijse.dep.hms.util;

public class DoctorTM {

    private String doctorid;
    private String firstname;
    private String lastname;
    private double fee;
    private String specialization;
    private String email;
    private String password;

    public DoctorTM() {
    }

    public DoctorTM(String doctorid, String firstname, String lastname, double fee, String specialization, String email, String password) {
        this.doctorid = doctorid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.fee = fee;
        this.specialization = specialization;
        this.email = email;
        this.password = password;
    }

    public String getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(String doctorid) {
        this.doctorid = doctorid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "DoctorTM{" +
                "doctorid='" + doctorid + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", fee=" + fee +
                ", specialization='" + specialization + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
