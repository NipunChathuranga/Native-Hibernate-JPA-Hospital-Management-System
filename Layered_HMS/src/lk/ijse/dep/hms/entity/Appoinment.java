package lk.ijse.dep.hms.entity;

import java.sql.Date;

public class Appoinment implements SuperEntity {

    private String appoinmentid;
    private String patientid;
    private String doctorid;
    private Date appoinmentdate;

    public Appoinment() {
    }

    public Appoinment(String appoinmentid, String patientid, String doctorid, Date appoinmentdate) {
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
}
