package lk.ijse.dep.hms.business.custom;

import lk.ijse.dep.hms.business.SuperBO;
import lk.ijse.dep.hms.dto.PrescriptionDTO;
import lk.ijse.dep.hms.dto.PrescriptionHistoryDTO;

import java.util.List;

public interface PrescriptionBO extends SuperBO {

    String getLastPrescriptionId() throws Exception;

    boolean issuePrescription(PrescriptionDTO prescriptionDTO) throws Exception;

    List<PrescriptionHistoryDTO>  getPrescriptionHistoryByDoc(String docid) throws Exception;

    List<PrescriptionDTO> findAllPrescriptions() throws Exception;




}
