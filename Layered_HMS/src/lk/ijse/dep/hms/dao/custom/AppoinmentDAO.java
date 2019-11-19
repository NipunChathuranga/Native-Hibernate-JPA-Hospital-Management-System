package lk.ijse.dep.hms.dao.custom;

import lk.ijse.dep.hms.dao.CrudDAO;
import lk.ijse.dep.hms.entity.Appoinment;

import java.util.List;

public interface AppoinmentDAO extends CrudDAO<Appoinment, String> {

    String getLastAppoinmentID() throws Exception;

    boolean existsByPatientID(String patientid) throws Exception;

    boolean existsByDoctorID(String doctorid) throws Exception;

    List<Appoinment> findAppoinmentsByDoctorID(String doctorid) throws Exception;


}
