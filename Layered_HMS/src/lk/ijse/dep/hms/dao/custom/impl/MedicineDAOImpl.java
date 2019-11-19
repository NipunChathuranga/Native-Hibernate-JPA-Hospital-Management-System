package lk.ijse.dep.hms.dao.custom.impl;

import lk.ijse.dep.hms.dao.CrudUtil;
import lk.ijse.dep.hms.dao.custom.MedicineDAO;
import lk.ijse.dep.hms.entity.Medicine;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MedicineDAOImpl implements MedicineDAO {
    @Override
    public List<Medicine> findAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Medicine");
        List<Medicine> medicines = new ArrayList<>();
        while (rst.next()) {
            medicines.add(new Medicine(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)));
        }
        return medicines;
    }

    @Override
    public Medicine find(String s) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Medicine WHERE medicineid=?", s);
        if (rst.next()) {
            return new Medicine(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4));
        }
        return null;
    }

    @Override
    public boolean save(Medicine entity) throws Exception {
        return CrudUtil.execute("INSERT INTO Medicine VALUES (?,?,?,?)", entity.getMedicineid(), entity.getDrugname(), entity.getBrandname(), entity.getDrugtype());
    }

    @Override
    public boolean update(Medicine entity) throws Exception {
        return CrudUtil.execute("UPDATE Medicine SET  drugname=?, brandname=?, drugtype=? WHERE medicineid=?", entity.getDrugname(),
                entity.getBrandname(), entity.getDrugtype(), entity.getMedicineid());
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.execute("DELETE FROM Medicine WHERE medicineid=?", s);
    }

    @Override
    public String getLastMedicineId() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT medicineid FROM Medicine ORDER BY medicineid DESC LIMIT 1");
        if(resultSet.next()){
            return resultSet.getString(1);
        }

        return null;
    }
}
