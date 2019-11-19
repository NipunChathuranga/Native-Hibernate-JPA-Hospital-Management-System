package lk.ijse.dep.hms.entity;

import java.sql.Date;

public class CustomEntity implements SuperEntity {


    private String firstname;
    private String prescriptionid;
    private String appoinmentid;
    private Date prescriptiondate;
    private String patientid;
    private String patientfname;
    private String patientlname;
    private String doctorid;
    private String specialization;
    private Date appoinmentdate;
    private String gender;
    private String patientemail;


    public CustomEntity() {
    }

    public CustomEntity(String prescriptionid, String appoinmentid, Date prescriptiondate, String patientid, String patientfname, String patientlname) {
        this.setPrescriptionid(prescriptionid);
        this.setAppoinmentid(appoinmentid);
        this.setPrescriptiondate(prescriptiondate);
        this.setPatientid(patientid);
        this.setPatientfname(patientfname);
        this.setPatientlname(patientlname);

    }

    public CustomEntity(String appoinmentid, String patientid, String patientfname, String patientlname, String gender, String patientemail) {
        this.setAppoinmentid(appoinmentid);
        this.setPatientid(patientid);
        this.setPatientfname(patientfname);
        this.setPatientlname(patientlname);
        this.setGender(gender);
        this.setPatientemail(patientemail);
    }

    public CustomEntity(String appoinmentid, String patientid, String patientfname, String doctorid, String firstname, String specialization, Date appoinmentdate) {
        this.setAppoinmentid(appoinmentid);
        this.setPatientid(patientid);
        this.setPatientfname(patientfname);
        this.setDoctorid(doctorid);
        this.setFirstname(firstname);
        this.setSpecialization(specialization);
        this.setAppoinmentdate(appoinmentdate);
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

    public String getPrescriptionid() {
        return prescriptionid;
    }

    public void setPrescriptionid(String prescriptionid) {
        this.prescriptionid = prescriptionid;
    }

    public Date getPrescriptiondate() {
        return prescriptiondate;
    }

    public void setPrescriptiondate(Date prescriptiondate) {
        this.prescriptiondate = prescriptiondate;
    }

    @Override
    public String toString() {
        return "CustomEntity{" +
                "firstname='" + firstname + '\'' +
                ", prescriptionid='" + prescriptionid + '\'' +
                ", appoinmentid='" + appoinmentid + '\'' +
                ", prescriptiondate=" + prescriptiondate +
                ", patientid='" + patientid + '\'' +
                ", patientfname='" + patientfname + '\'' +
                ", patientlname='" + patientlname + '\'' +
                ", doctorid='" + doctorid + '\'' +
                ", specialization='" + specialization + '\'' +
                ", appoinmentdate=" + appoinmentdate +
                ", gender='" + gender + '\'' +
                ", patientemail='" + patientemail + '\'' +
                '}';
    }





}
