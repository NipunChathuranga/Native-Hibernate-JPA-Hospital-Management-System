package lk.ijse.dep.hms.entity;

import com.sun.xml.internal.bind.v2.model.core.ID;

import java.sql.Date;

public class Prescription implements SuperEntity {

    private String prescriptionid;
    private String appoinmentid;
    private Date prescriptiondate;

    public Prescription() {
    }

    public Prescription(String prescriptionid, String appoinmentid, Date prescriptiondate) {
        this.prescriptionid = prescriptionid;
        this.appoinmentid = appoinmentid;
        this.prescriptiondate = prescriptiondate;
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

    @Override
    public String toString() {
        return "Prescription{" +
                "prescriptionid='" + prescriptionid + '\'' +
                ", appoinmentid='" + appoinmentid + '\'' +
                ", prescriptiondate=" + prescriptiondate +
                '}';
    }






}
