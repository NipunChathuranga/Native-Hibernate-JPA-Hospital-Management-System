package lk.ijse.dep.hms.dao.custom;

import lk.ijse.dep.hms.dao.SuperDAO;
import lk.ijse.dep.hms.entity.CustomEntity;

import java.util.List;

public interface QueryDAO extends SuperDAO {

    List<CustomEntity> getAppoinmentsInfo() throws Exception;

    List<CustomEntity> getAppoinmentsInfoByDocID(String doctorid) throws Exception;

    List<CustomEntity> getPrescriptionHistoryByDocID(String doctorid) throws Exception;





}
