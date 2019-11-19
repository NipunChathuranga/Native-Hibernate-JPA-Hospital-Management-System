package lk.ijse.dep.hms.util;

import java.sql.Date;

public class PrescriptionHistoryTM {

    private String prescriptionid;
    private String appoinmentid;
    private Date prescriptiondate;
    private String patientid;
    private String patientfname;
    private String patientlname;

    public PrescriptionHistoryTM() {
    }

    public PrescriptionHistoryTM(String prescriptionid, String appoinmentid, Date prescriptiondate, String patientid, String patientfname, String patientlname) {
        this.prescriptionid = prescriptionid;
        this.appoinmentid = appoinmentid;
        this.prescriptiondate = prescriptiondate;
        this.patientid = patientid;
        this.patientfname = patientfname;
        this.patientlname = patientlname;
    }

    public String getPrescriptionid() {
        return prescriptionid;
    }

    public void setPrescriptionid(String prescriptionid) {
        this.prescriptionid = prescriptionid;
    }

    public String getAppoinmentid() {
        return appoinmentid;
    }

    public void setAppoinmentid(String appoinmentid) {
        this.appoinmentid = appoinmentid;
    }

    public Date getPrescriptiondate() {
        return prescriptiondate;
    }

    public void setPrescriptiondate(Date prescriptiondate) {
        this.prescriptiondate = prescriptiondate;
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


    @Override
    public String toString() {
        return "PrescriptionHistoryTM{" +
                "prescriptionid='" + prescriptionid + '\'' +
                ", appoinmentid='" + appoinmentid + '\'' +
                ", prescriptiondate=" + prescriptiondate +
                ", patientid='" + patientid + '\'' +
                ", patientfname='" + patientfname + '\'' +
                ", patientlname='" + patientlname + '\'' +
                '}';
    }
}
