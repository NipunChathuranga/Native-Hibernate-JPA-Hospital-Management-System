package lk.ijse.dep.hms.dao.custom;

import lk.ijse.dep.hms.dao.CrudDAO;
import lk.ijse.dep.hms.entity.Doctor;

public interface DoctorDAO extends CrudDAO<Doctor,String> {

    String getLastDoctorId() throws Exception;

}
