package lk.ijse.dep.hms.dao.custom.impl;

import lk.ijse.dep.hms.dao.CrudUtil;
import lk.ijse.dep.hms.dao.custom.AppoinmentDAO;
import lk.ijse.dep.hms.entity.Appoinment;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AppoinmentDAOImpl implements AppoinmentDAO {

    @Override
    public String getLastAppoinmentID() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT appoinmentid FROM Appoinment ORDER BY appoinmentid DESC LIMIT 1");
        if (resultSet.next()) {
            System.out.println(resultSet.getString(1));
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public boolean existsByPatientID(String patientid) throws Exception {
        ResultSet rst =  CrudUtil.execute("SELECT * FROM Appoinment WHERE patientid=?",patientid);
        return rst.next();
    }

    @Override
    public boolean existsByDoctorID(String doctorid) throws Exception {
        ResultSet rst =  CrudUtil.execute("SELECT * FROM Appoinment WHERE doctorid=?",doctorid);
        return rst.next();
    }

    @Override
    public List<Appoinment> findAppoinmentsByDoctorID(String doctorid) throws Exception {
        ResultSet rst =  CrudUtil.execute("SELECT * FROM Appoinment WHERE doctorid=?",doctorid);
        List<Appoinment> appoinments = new ArrayList<>();
        while (rst.next()){
            appoinments.add(new Appoinment(rst.getString(1),rst.getString(2),rst.getString(3),
                    rst.getDate(4)));
        }

        return appoinments;
    }

    @Override

    public List<Appoinment> findAll() throws Exception {

        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Appoinment");
        List<Appoinment> appoinments = new ArrayList<>();
        while (resultSet.next()) {
            appoinments.add(new Appoinment(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3), resultSet.getDate(4)));

        }

        return appoinments;

    }

    @Override
    public Appoinment find(String s) throws Exception {

        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Appoinment WHERE appoinmentid = ?", s);
        if (resultSet.next()) {
           return new Appoinment(resultSet.getString(1), resultSet.getString(2),
                    resultSet.getString(3), resultSet.getDate(4));
        }
        return null;
    }

    @Override
    public boolean save(Appoinment entity) throws Exception {
        return CrudUtil.execute("INSERT INTO Appoinment VALUES(?,?,?,?)", entity.getAppoinmentid(), entity.getPatientid(),
                entity.getDoctorid(), entity.getAppoinmentdate());
    }

    @Override
    public boolean update(Appoinment entity) throws Exception {
        return CrudUtil.execute("UPDATE Appoinment SET appoinmentid=?,patientid=?,doctorid=?,appoinmentdate=?",entity.getAppoinmentid()
        ,entity.getPatientid(),entity.getDoctorid(),entity.getAppoinmentdate());
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.execute("DELETE FROM Appoinment WHERE appoinmentid=?", s);
    }


}
