package lk.ijse.dep.hms.dao.custom;

import lk.ijse.dep.hms.dao.CrudDAO;
import lk.ijse.dep.hms.entity.Prescription;

public interface PrescriptionDAO extends CrudDAO<Prescription, String> {
    String getLastPrescriptionId() throws Exception;

    boolean existsByAppoinmentID(String appoinmentid) throws Exception;


}
