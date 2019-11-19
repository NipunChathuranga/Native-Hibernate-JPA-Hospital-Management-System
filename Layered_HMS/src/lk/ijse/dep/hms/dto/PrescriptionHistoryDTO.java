package lk.ijse.dep.hms.dto;

import java.sql.Date;

public class PrescriptionHistoryDTO {

    private String prescriptionid;
    private String appoinmentid;
    private Date prescriptiondate;
    private String patientid;
    private String patientfirstname;
    private String patientlastname;

    public PrescriptionHistoryDTO() {
    }

    public PrescriptionHistoryDTO(String prescriptionid, String appoinmentid, Date prescriptiondate, String patientid, String patientfirstname, String patientlastname) {
        this.setPrescriptionid(prescriptionid);
        this.setAppoinmentid(appoinmentid);
        this.setPrescriptiondate(prescriptiondate);
        this.setPatientid(patientid);
        this.setPatientfirstname(patientfirstname);
        this.setPatientlastname(patientlastname);
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
}
