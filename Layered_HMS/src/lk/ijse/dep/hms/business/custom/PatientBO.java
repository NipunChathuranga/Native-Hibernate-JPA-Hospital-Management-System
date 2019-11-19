package lk.ijse.dep.hms.business.custom;

import lk.ijse.dep.hms.business.SuperBO;
import lk.ijse.dep.hms.dto.PatientDTO;

import java.util.List;

public interface PatientBO extends SuperBO {


    boolean savePatient(PatientDTO patient) throws Exception;

    boolean updatePatient(PatientDTO patient) throws Exception;

    boolean deletePatient(String patientid) throws Exception;

    List<PatientDTO> findAllPatients() throws Exception;

    String getLastPatientId() throws Exception;

    PatientDTO findPatient(String patientid) throws Exception;

    List<String> getAllPatientIDs() throws Exception;


}
