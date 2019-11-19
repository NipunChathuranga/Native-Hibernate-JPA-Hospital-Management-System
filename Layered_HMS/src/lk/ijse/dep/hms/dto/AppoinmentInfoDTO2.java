package lk.ijse.dep.hms.dto;

public class AppoinmentInfoDTO2 {
    private String appoinmentid;
    private String patientid;
    private String patientfname;
    private String patientlname;
    private String gender;
    private String patientemail;

    public AppoinmentInfoDTO2() {
    }


    public AppoinmentInfoDTO2(String appoinmentid, String patientid, String patientfname, String patientlname, String gender, String patientemail) {
        this.appoinmentid = appoinmentid;
        this.patientid = patientid;
        this.patientfname = patientfname;
        this.patientlname = patientlname;
        this.gender = gender;
        this.patientemail = patientemail;
    }

    public String getAppoinmentid() {
        return appoinmentid;
    }

    public void setAppoinmentid(String appoinmentid) {
        this.appoinmentid = appoinmentid;
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

    public String getPatientemail() {
        return patientemail;
    }

    public void setPatientemail(String patientemail) {
        this.patientemail = patientemail;
    }

    @Override
    public String toString() {
        return "AppoinmentInfoDTO2{" +
                "appoinmentid='" + appoinmentid + '\'' +
                ", patientid='" + patientid + '\'' +
                ", patientfname='" + patientfname + '\'' +
                ", patientlname='" + patientlname + '\'' +
                ", gender='" + gender + '\'' +
                ", patientemail='" + patientemail + '\'' +
                '}';
    }
}
