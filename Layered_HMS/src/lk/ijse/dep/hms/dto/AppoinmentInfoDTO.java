package lk.ijse.dep.hms.dto;

import java.sql.Date;

public class AppoinmentInfoDTO {

    private String appoinmentid;
    private String patientid;
    private String patientfname;
    private String doctorid;
    private String firstname;
    private String specialization;
    private Date appoinmentdate;

    public AppoinmentInfoDTO() {
    }

    public AppoinmentInfoDTO(String appoinmentid, String patientid, String patientfname, String doctorid, String firstname, String specialization, Date appoinmentdate) {
        this.appoinmentid = appoinmentid;
        this.patientid = patientid;
        this.patientfname = patientfname;
        this.doctorid = doctorid;
        this.firstname = firstname;
        this.specialization = specialization;
        this.appoinmentdate = appoinmentdate;
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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Date getAppoinmentdate() {
        return appoinmentdate;
    }

    public void setAppoinmentdate(Date appoinmentdate) {
        this.appoinmentdate = appoinmentdate;
    }

    @Override
    public String toString() {
        return "AppoinmentInfoDTO{" +
                "appoinmentid='" + appoinmentid + '\'' +
                ", patientid='" + patientid + '\'' +
                ", patientfname='" + patientfname + '\'' +
                ", doctorid='" + doctorid + '\'' +
                ", firstname='" + firstname + '\'' +
                ", specialization='" + specialization + '\'' +
                ", appoinmentdate=" + appoinmentdate +
                '}';
    }
}
