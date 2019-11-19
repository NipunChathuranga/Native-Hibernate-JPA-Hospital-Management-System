package lk.ijse.dep.hms.util;

import java.sql.Date;

public class AppoinmentTM {

    private String appoinmentid;
    private String patientid;
    private String patientfname;
    private String doctorid;
    private String doctorname;
    private String specialization;
    private Date appoinmentdate;


    public AppoinmentTM() {
    }


    public AppoinmentTM(String appoinmentid, String patientid, String patientfname, String doctorid, String doctorname, String specialization, Date appoinmentdate) {
        this.appoinmentid = appoinmentid;
        this.patientid = patientid;
        this.patientfname = patientfname;
        this.doctorid = doctorid;
        this.doctorname = doctorname;
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

    public String getDoctorname() {
        return doctorname;
    }

    public void setDoctorname(String doctorname) {
        this.doctorname = doctorname;
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
}
