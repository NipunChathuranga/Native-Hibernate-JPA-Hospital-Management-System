package lk.ijse.dep.hms.dao.custom;

import lk.ijse.dep.hms.dao.CrudDAO;
import lk.ijse.dep.hms.entity.PrescriptionDetail;
import lk.ijse.dep.hms.entity.PrescriptionDetailPK;

public interface PrescriptionDetailDAO extends CrudDAO<PrescriptionDetail, PrescriptionDetailPK> {

    boolean existsByMedicineID(String medicineid) throws Exception;

}
