package lk.ijse.dep.hms.dao.custom.impl;

import lk.ijse.dep.hms.dao.CrudUtil;
import lk.ijse.dep.hms.dao.custom.QueryDAO;
import lk.ijse.dep.hms.entity.CustomEntity;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public List<CustomEntity> getAppoinmentsInfo() throws Exception {

        ResultSet resultSet = CrudUtil.execute(
                "SELECT A.appoinmentid,A.patientid,P.patientfname,A.doctorid,D.firstname,D.specialization,A.appoinmentdate\n" +
                        "FROM appoinment A\n" +
                        "INNER JOIN patient P ON A.patientid = P.patientid\n" +
                        "INNER JOIN doctor D ON A.doctorid = D.doctorid\n" +
                        "ORDER BY appoinmentid");
        List<CustomEntity> appoinmenthistory = new ArrayList<>();
        while (resultSet.next()) {
            appoinmenthistory.add(new CustomEntity(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                    resultSet.getString(5), resultSet.getString(6), resultSet.getDate(7)));
        }
        return appoinmenthistory;
    }

    @Override
    public List<CustomEntity> getAppoinmentsInfoByDocID(String doctorid) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT A.appoinmentid,A.patientid,P.patientfname,P.patientlname,P.gender,P.patientemail,A.doctorid\n" +
                "FROM appoinment A\n" +
                "INNER JOIN patient P ON A.patientid = P.patientid\n" +
                "WHERE A.doctorid=?\n" +
                "ORDER BY appoinmentid",doctorid);
        List<CustomEntity> docappoinmenthistory = new ArrayList<>();

        while (resultSet.next()){
            docappoinmenthistory.add(new CustomEntity(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)));

        }

        return docappoinmenthistory;
    }

    @Override
    public List<CustomEntity> getPrescriptionHistoryByDocID(String doctorid) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT R.prescriptionid,R.appoinmentid,R.prescriptiondate,A.patientid,P.patientfname,P.patientlname\n" +
                "FROM prescription R\n" +
                "INNER JOIN appoinment A on R.appoinmentid = A.appoinmentid\n" +
                "INNER JOIN patient P on A.patientid = P.patientid\n" +
                "WHERE doctorid = ?\n" +
                "ORDER BY R.prescriptionid",doctorid);

        List<CustomEntity> presHistory = new ArrayList<>();
        while (resultSet.next()){
            presHistory.add(new CustomEntity(resultSet.getString(1),
                    resultSet.getString(2),resultSet.getDate(3),
                    resultSet.getString(4),resultSet.getString(5),
                    resultSet.getString(6)));

        }


        return presHistory;



    }


}
