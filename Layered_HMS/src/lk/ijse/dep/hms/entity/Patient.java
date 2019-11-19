package lk.ijse.dep.hms.entity;

public class Patient implements SuperEntity {
    private String patientid;
    private String patientfname;
    private String patientlname;
    private String gender;
    private String city;
    private String patientemail;

    public Patient() {
    }

    public Patient(String patientid, String patientfname, String patientlname, String gender, String city, String patientemail) {
        this.patientid = patientid;
        this.patientfname = patientfname;
        this.patientlname = patientlname;
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

    public String getPatientfname() {
        return patientfname;
    }

    public void setPatientfname(String patientfname) {
        this.patientfname = patientfname;
    }

    public String getPatientlname() {
        return patientlname;
    }

    public void setPatientlname(String patientlname) {
        this.patientlname = patientlname;
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
        return "Patient{" +
                "patientid='" + patientid + '\'' +
                ", patientfname='" + patientfname + '\'' +
                ", patientlname='" + patientlname + '\'' +
                ", gender='" + gender + '\'' +
                ", city='" + city + '\'' +
                ", patientemail='" + patientemail + '\'' +
                '}';
    }
}
