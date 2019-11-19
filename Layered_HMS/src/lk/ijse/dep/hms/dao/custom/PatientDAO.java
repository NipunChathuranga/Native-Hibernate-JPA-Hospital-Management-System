package lk.ijse.dep.hms.dao.custom;

import lk.ijse.dep.hms.dao.CrudDAO;
import lk.ijse.dep.hms.entity.Patient;

public interface PatientDAO extends CrudDAO<Patient, String> {
    String getLastPatientId() throws Exception;
}
