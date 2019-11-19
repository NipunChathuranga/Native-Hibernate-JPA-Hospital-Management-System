package lk.ijse.dep.hms.business.custom;

import lk.ijse.dep.hms.business.SuperBO;
import lk.ijse.dep.hms.dto.DoctorDTO;

import java.util.List;

public interface DoctorBO extends SuperBO {

    boolean saveDoctor(DoctorDTO doctor) throws Exception;

    boolean updateDoctor(DoctorDTO doctor) throws Exception;

    boolean deleteDoctor(String doctorid) throws Exception;

    List<DoctorDTO> findAllDoctors() throws Exception;

    String getLastDoctorId() throws Exception;

    DoctorDTO findDoctor(String doctorid) throws Exception;

    List<String> getAllDoctorIDs() throws Exception;


}
