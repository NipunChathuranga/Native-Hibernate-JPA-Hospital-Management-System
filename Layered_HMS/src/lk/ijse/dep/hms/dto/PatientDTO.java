package lk.ijse.dep.hms.dto;

public class PatientDTO {

    private String patientid;
    private String patientfirstname;
    private String patientlastname;
    private String gender;
    private String city;
    private String patientemail;

    public PatientDTO() {
    }

    public PatientDTO(String patientid, String patientfirstname, String patientlastname, String gender, String city, String patientemail) {
        this.patientid = patientid;
        this.patientfirstname = patientfirstname;
        this.patientlastname = patientlastname;
        this.gender = gender;
        this.city = city;
        this.patientemail = patientemail;
    }

    public String getPatientid() {
        return patientid;
    }

    public void setPatientid(String patientid) {
        this.patientid = patientid;
    }

    public String getPatientfirstname() {
        return patientfirstname;
    }

    public void setPatientfirstname(String patientfirstname) {
        this.patientfirstname = patientfirstname;
    }

    public String getPatientlastname() {
        return patientlastname;
    }

    public void setPatientlastname(String patientlastname) {
        this.patientlastname = patientlastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPatientemail() {
        return patientemail;
    }

    public void setPatientemail(String patientemail) {
        this.patientemail = patientemail;
    }

    @Override
    public String toString() {
        return "PatientDTO{" +
                "patientid='" + patientid + '\'' +
                ", patientfirstname='" + patientfirstname + '\'' +
                ", patientlastname='" + patientlastname + '\'' +
                ", gender='" + gender + '\'' +
                ", city='" + city + '\'' +
                ", patientemail='" + patientemail + '\'' +
                '}';
    }
}
