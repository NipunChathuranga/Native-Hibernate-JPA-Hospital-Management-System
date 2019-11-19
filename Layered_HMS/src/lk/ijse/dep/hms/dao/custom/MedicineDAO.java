package lk.ijse.dep.hms.dao.custom;

import lk.ijse.dep.hms.dao.CrudDAO;
import lk.ijse.dep.hms.entity.Medicine;

public interface MedicineDAO extends CrudDAO<Medicine, String> {
    String getLastMedicineId() throws Exception;
}
