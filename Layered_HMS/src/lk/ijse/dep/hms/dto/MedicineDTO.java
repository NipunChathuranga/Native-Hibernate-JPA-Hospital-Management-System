package lk.ijse.dep.hms.dto;

public class MedicineDTO {

    private String medicineid;
    private String drugname;
    private String brandname;
    private String drugtype;

    public MedicineDTO() {
    }

    public MedicineDTO(String medicineid, String drugname, String brandname, String drugtype) {
        this.medicineid = medicineid;
        this.drugname = drugname;
        this.brandname = brandname;
        this.drugtype = drugtype;
    }

    public String getMedicineid() {
        return medicineid;
    }

    public void setMedicineid(String medicineid) {
        this.medicineid = medicineid;
    }

    public String getDrugname() {
        return drugname;
    }

    public void setDrugname(String drugname) {
        this.drugname = drugname;
    }

    public String getBrandname() {
        return brandname;
    }

    public void setBrandname(String brandname) {
        this.brandname = brandname;
    }

    public String getDrugtype() {
        return drugtype;
    }

    public void setDrugtype(String drugtype) {
        this.drugtype = drugtype;
    }
}
