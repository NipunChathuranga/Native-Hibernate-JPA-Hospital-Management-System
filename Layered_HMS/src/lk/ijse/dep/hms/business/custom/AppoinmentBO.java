package lk.ijse.dep.hms.business.custom;

import lk.ijse.dep.hms.business.SuperBO;
import lk.ijse.dep.hms.dto.AppoinmentDTO;
import lk.ijse.dep.hms.dto.AppoinmentInfoDTO;
import lk.ijse.dep.hms.dto.AppoinmentInfoDTO2;
import lk.ijse.dep.hms.entity.Appoinment;

import java.util.List;

public interface AppoinmentBO extends SuperBO {

    boolean saveAppoinment(AppoinmentDTO appoinment) throws Exception;

    boolean updateAppoinment(AppoinmentDTO appoinment) throws Exception;

    boolean deleteAppoinent(String appoinmentid) throws Exception;

    List<AppoinmentDTO> findAllAppoinments() throws Exception;

    String getLastAppoinmentId() throws Exception;

    AppoinmentDTO findAppoinment(String appoinmentid) throws Exception;

    List<String> getAllAppoinmentIDs() throws Exception;

    List<AppoinmentDTO> findAppoinmentsByDoctorID(String doctorid) throws Exception;

    List<AppoinmentInfoDTO> getAppoinmentsInfo() throws Exception;

    List<AppoinmentInfoDTO2> getAppoinmentsInfobyDocID(String doctorid) throws Exception;




}
