package lk.ijse.dep.hms.business.custom;

import lk.ijse.dep.hms.business.SuperBO;
import lk.ijse.dep.hms.dto.MedicineDTO;

import java.util.List;

public interface MedicineBO extends SuperBO {

    boolean saveMedicine(MedicineDTO medicine) throws Exception;

    boolean updateMedicine(MedicineDTO medicine) throws Exception;

    boolean deleteMedicine(String medicineid) throws Exception;

    List<MedicineDTO> findAllMedicines() throws Exception;

    String getLastMedicineId() throws Exception;

    MedicineDTO findMedicine(String medicineid) throws Exception;

    List<String> getAllMedicineIDs() throws Exception;


}
