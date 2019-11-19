package lk.ijse.dep.hms.entity;

public class PrescriptionDetailPK {

    private String prescriptionid ;
    private String medicineid ;

    public PrescriptionDetailPK() {
    }

    public PrescriptionDetailPK(String prescriptionid, String medicineid) {
        this.prescriptionid = prescriptionid;
        this.medicineid = medicineid;
    }

    public String getPrescriptionid() {
        return prescriptionid;
    }

    public void setPrescriptionid(String prescriptionid) {
        this.prescriptionid = prescriptionid;
    }

    public String getMedicineid() {
        return medicineid;
    }

    public void setMedicineid(String medicineid) {
        this.medicineid = medicineid;
    }

    @Override
    public String toString() {
        return "PrescriptionDetailPK{" +
                "prescriptionid='" + prescriptionid + '\'' +
                ", medicineid='" + medicineid + '\'' +
                '}';
    }
}
