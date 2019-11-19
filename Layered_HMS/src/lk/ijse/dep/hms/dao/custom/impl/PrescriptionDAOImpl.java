package lk.ijse.dep.hms.dao.custom.impl;

import lk.ijse.dep.hms.dao.CrudUtil;
import lk.ijse.dep.hms.dao.custom.PrescriptionDAO;
import lk.ijse.dep.hms.entity.Prescription;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionDAOImpl implements PrescriptionDAO {

    @Override
    public String getLastPrescriptionId() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT prescriptionid FROM Prescription ORDER BY prescriptionid DESC LIMIT 1");
        if (resultSet.next()) {
            return resultSet.getString(1);
        }

        return null;
    }

    @Override
    public boolean existsByAppoinmentID(String appoinmentid) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Prescription WHERE appoinmentid=?",appoinmentid);
        return resultSet.next();
    }

    @Override
    public List<Prescription> findAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Prescription");
        List<Prescription> prescriptions = new ArrayList<>();
        while (rst.next()) {
            prescriptions.add(new Prescription(rst.getString(1),
                    rst.getString(2),
                    rst.getDate(3)));
        }
        return prescriptions;
    }

    @Override
    public Prescription find(String s) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Prescription WHERE prescriptionid=?", s);
        if (rst.next()) {
            return new Prescription(rst.getString(1),
                    rst.getString(2),
                    rst.getDate(3)
            );
        }
        return null;
    }

    @Override
    public boolean save(Prescription entity) throws Exception {
        return CrudUtil.execute("INSERT INTO Prescription VALUES (?,?,?)", entity.getPrescriptionid(), entity.getAppoinmentid(), entity.getPrescriptiondate());
    }

    @Override
    public boolean update(Prescription entity) throws Exception {
        return CrudUtil.execute("UPDATE Prescription SET appoinmentid=?,prescriptiondate=?  WHERE prescriptionid=?",entity.getAppoinmentid(),entity.getPrescriptiondate(),entity.getPrescriptionid());
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.execute("DELETE FROM Prescription WHERE prescriptionid=?", s);
    }




}
