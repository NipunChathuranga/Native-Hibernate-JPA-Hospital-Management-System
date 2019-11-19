package lk.ijse.dep.hms.util;

public class PrescripDetailTM {

    private String medicineid;
    private String drugname;
    private String brandname;
    private String drugtype;

    public PrescripDetailTM() {
    }

    public PrescripDetailTM(String medicineid, String drugname, String brandname, String drugtype) {
        this.setMedicineid(medicineid);
        this.setDrugname(drugname);
        this.setBrandname(brandname);
        this.setDrugtype(drugtype);
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

    @Override
    public String toString() {
        return "PrescripDetailTM{" +
                "medicineid='" + medicineid + '\'' +
                ", drugname='" + drugname + '\'' +
                ", brandname='" + brandname + '\'' +
                ", drugtype='" + drugtype + '\'' +
                '}';
    }
}
