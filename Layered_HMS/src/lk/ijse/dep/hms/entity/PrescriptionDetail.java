package lk.ijse.dep.hms.entity;

public class PrescriptionDetail implements SuperEntity{

    private PrescriptionDetailPK prescriptionDetailPK;

    public PrescriptionDetail() {
    }

    public PrescriptionDetail(PrescriptionDetailPK prescriptionDetailPK) {
        this.prescriptionDetailPK = prescriptionDetailPK;
    }

    public PrescriptionDetail(String prescriptionid,String medicineid) {
        this.prescriptionDetailPK = new PrescriptionDetailPK(prescriptionid,medicineid);

    }

    public PrescriptionDetailPK getPrescriptionDetailPK() {
        return prescriptionDetailPK;
    }

    public void setPrescriptionDetailPK(PrescriptionDetailPK prescriptionDetailPK) {
        this.prescriptionDetailPK = prescriptionDetailPK;
    }

    @Override
    public String toString() {
        return "PrescriptionDetail{" +
                "prescriptionDetailPK=" + prescriptionDetailPK +
                '}';
    }
}
