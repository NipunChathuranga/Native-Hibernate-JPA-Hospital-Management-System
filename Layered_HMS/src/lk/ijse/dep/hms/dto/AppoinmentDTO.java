package lk.ijse.dep.hms.dto;

import java.sql.Date;

public class AppoinmentDTO {

    private String appoinmentid;
    private String patientid;
    private String doctorid;
    private Date appoinmentdate;

    public AppoinmentDTO() {
    }


    public AppoinmentDTO(String appoinmentid, String patientid, String doctorid, Date appoinmentdate) {
        this.appoinmentid = appoinmentid;
        this.patientid = patientid;
        this.doctorid = doctorid;
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

    public String getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(String doctorid) {
        this.doctorid = doctorid;
    }

    public Date getAppoinmentdate() {
        return appoinmentdate;
    }

    public void setAppoinmentdate(Date appoinmentdate) {
        this.appoinmentdate = appoinmentdate;
    }

    @Override
    public String toString() {
        return "AppoinmentDTO{" +
                "appoinmentid='" + appoinmentid + '\'' +
                ", patientid='" + patientid + '\'' +
                ", doctorid='" + doctorid + '\'' +
                ", appoinmentdate=" + appoinmentdate +
                '}';
    }
}
