package lk.ijse.dep.hms.dao.custom.impl;

import lk.ijse.dep.hms.dao.CrudUtil;
import lk.ijse.dep.hms.dao.custom.PatientDAO;
import lk.ijse.dep.hms.entity.Patient;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PatientDAOImpl implements PatientDAO {
    @Override
    public List<Patient> findAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Patient");
        List<Patient> patients = new ArrayList<>();
        while (rst.next()) {
            patients.add(new Patient(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6)
            ));
        }
        return patients;
    }

    @Override
    public Patient find(String s) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Patient WHERE patientid=?", s);
        if (rst.next()) {
            return new Patient(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6));
        }
        return null;
    }

    @Override
    public boolean save(Patient entity) throws Exception {
        return CrudUtil.execute("INSERT INTO Patient VALUES (?,?,?,?,?,?)", entity.getPatientid(),
                entity.getPatientfname(), entity.getPatientlname(), entity.getGender(), entity.getCity(),entity.getPatientemail());
    }

    @Override
    public boolean update(Patient entity) throws Exception {
        return CrudUtil.execute("UPDATE Patient SET  patientfname=?, patientlname=?, gender=?,city=?, patientemail=? WHERE patientid=?", entity.getPatientfname(),
               entity.getPatientlname(), entity.getGender(), entity.getCity(),entity.getPatientemail(), entity.getPatientid());
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.execute("DELETE FROM Patient WHERE patientid=?", s);
    }

    @Override
    public String getLastPatientId() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT patientid FROM Patient ORDER BY patientid DESC LIMIT 1");
        if (resultSet.next()) {
            return resultSet.getString(1);

        }

        return null;
    }
}
