package lk.ijse.dep.hms.dao.custom.impl;

import lk.ijse.dep.hms.dao.CrudUtil;
import lk.ijse.dep.hms.dao.custom.PrescriptionDetailDAO;
import lk.ijse.dep.hms.entity.PrescriptionDetail;
import lk.ijse.dep.hms.entity.PrescriptionDetailPK;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionDetailDAOImpl implements PrescriptionDetailDAO {
    @Override
    public List<PrescriptionDetail> findAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM PrescriptionDetail");
        List<PrescriptionDetail> prescriptionDetails = new ArrayList<>();
        while (rst.next()) {
            prescriptionDetails.add(new PrescriptionDetail(rst.getString(1),
                    rst.getString(2)));
        }
        return prescriptionDetails;
    }

    @Override
    public PrescriptionDetail find(PrescriptionDetailPK prescriptionDetailPK) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM PrescriptionDetail WHERE prescriptionid=?,medicineid=?", prescriptionDetailPK.getPrescriptionid(), prescriptionDetailPK.getMedicineid());
        if (rst.next()) {
            return new PrescriptionDetail(rst.getString(1),
                    rst.getString(2));
        }
        return null;
    }

    @Override
    public boolean save(PrescriptionDetail entity) throws Exception {
        return CrudUtil.execute("INSERT INTO PrescriptionDetail VALUES (?,?)", entity.getPrescriptionDetailPK().getPrescriptionid(), entity.getPrescriptionDetailPK().getMedicineid());
    }

    @Override
    public boolean update(PrescriptionDetail entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(PrescriptionDetailPK prescriptionDetailPK) throws Exception {
        return CrudUtil.execute("DELETE FROM PrescriptionDetail WHERE prescriptionid=?,medicineid=?", prescriptionDetailPK.getPrescriptionid(), prescriptionDetailPK.getMedicineid());
    }

    @Override
    public boolean existsByMedicineID(String medicineid) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM PrescriptionDetail WHERE medicineid=?", medicineid);
        return rst.next();
    }
}
