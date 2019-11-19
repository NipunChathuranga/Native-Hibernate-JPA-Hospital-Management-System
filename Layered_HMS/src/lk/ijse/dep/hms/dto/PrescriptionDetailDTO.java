package lk.ijse.dep.hms.dto;

public class PrescriptionDetailDTO {
    private String medicineid;


    public PrescriptionDetailDTO() {
    }

    public PrescriptionDetailDTO(String medicineid) {
        this.medicineid = medicineid;
    }

    public String getMedicineid() {
        return medicineid;
    }

    public void setMedicineid(String medicineid) {
        this.medicineid = medicineid;
    }

    @Override
    public String toString() {
        return "PrescriptionDetailDTO{" +
                "medicineid='" + medicineid + '\'' +
                '}';
    }
}
