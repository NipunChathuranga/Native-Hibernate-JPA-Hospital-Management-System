package lk.ijse.dep.hms.business.custom.impl;

import lk.ijse.dep.hms.business.custom.AppoinmentBO;
import lk.ijse.dep.hms.business.exception.AlreadyExistsInAppoinmentException;
import lk.ijse.dep.hms.dao.DAOFactory;
import lk.ijse.dep.hms.dao.DAOTypes;
import lk.ijse.dep.hms.dao.custom.AppoinmentDAO;
import lk.ijse.dep.hms.dao.custom.PrescriptionDAO;
import lk.ijse.dep.hms.dao.custom.QueryDAO;
import lk.ijse.dep.hms.dto.AppoinmentDTO;
import lk.ijse.dep.hms.dto.AppoinmentInfoDTO;
import lk.ijse.dep.hms.dto.AppoinmentInfoDTO2;
import lk.ijse.dep.hms.entity.Appoinment;
import lk.ijse.dep.hms.entity.CustomEntity;

import java.util.ArrayList;
import java.util.List;

public class AppoinmentBOImpl implements AppoinmentBO {

    private AppoinmentDAO appoinmentDAO = DAOFactory.getInstance().getDAO(DAOTypes.APPOINMENT);
    private QueryDAO queryDAO = DAOFactory.getInstance().getDAO(DAOTypes.QUERY);
    private PrescriptionDAO prescriptionDAO = DAOFactory.getInstance().getDAO(DAOTypes.PRESCRIPTION);
    @Override
    public boolean saveAppoinment(AppoinmentDTO appoinment) throws Exception {
        return appoinmentDAO.save(new Appoinment(appoinment.getAppoinmentid(), appoinment.getPatientid(), appoinment.getDoctorid(),
                appoinment.getAppoinmentdate()));
    }

    @Override
    public boolean updateAppoinment(AppoinmentDTO appoinment) throws Exception {
        return appoinmentDAO.update(new Appoinment(appoinment.getAppoinmentid(), appoinment.getPatientid(), appoinment.getDoctorid(),
                appoinment.getAppoinmentdate()));
    }

    @Override
    public boolean deleteAppoinent(String appoinmentid) throws Exception {
        if(prescriptionDAO.existsByAppoinmentID(appoinmentid)){
            throw new AlreadyExistsInAppoinmentException("Appoinment already exists in an prescription, hence unable to delete");
        }
        return appoinmentDAO.delete(appoinmentid);
    }

    @Override
    public List<AppoinmentDTO> findAllAppoinments() throws Exception {

        List<Appoinment> alappoinments = appoinmentDAO.findAll();
        List<AppoinmentDTO> appoinmentDTOS = new ArrayList<>();

        for (Appoinment appoinment : alappoinments) {
            appoinmentDTOS.add(new AppoinmentDTO(appoinment.getAppoinmentid(), appoinment.getPatientid(), appoinment.getDoctorid(),
                    appoinment.getAppoinmentdate()));
        }

        return appoinmentDTOS;
    }

    @Override
    public String getLastAppoinmentId() throws Exception {
        return appoinmentDAO.getLastAppoinmentID();
    }

    @Override
    public AppoinmentDTO findAppoinment(String appoinmentid) throws Exception {
        Appoinment appoinment = appoinmentDAO.find(appoinmentid);
        return new AppoinmentDTO(appoinment.getAppoinmentid(), appoinment.getPatientid(),
                appoinment.getDoctorid(), appoinment.getAppoinmentdate());
    }

    @Override
    public List<String> getAllAppoinmentIDs() throws Exception {
        List<Appoinment> appoinments = appoinmentDAO.findAll();
        List<String> appoinmenids = new ArrayList<>();
        for (Appoinment appoinment : appoinments) {
            appoinmenids.add(appoinment.getAppoinmentid());
        }

        return appoinmenids;
    }

    @Override
    public List<AppoinmentDTO> findAppoinmentsByDoctorID(String doctorid) throws Exception {
        List<Appoinment> appoinments = appoinmentDAO.findAppoinmentsByDoctorID(doctorid);
        List<AppoinmentDTO> appoinmentDTOS = new ArrayList<>();
        for(Appoinment appoinment:appoinments){
            appoinmentDTOS.add(new AppoinmentDTO(appoinment.getAppoinmentid(),appoinment.getPatientid(),appoinment.getDoctorid(),
                    appoinment.getAppoinmentdate()));
        }

        return appoinmentDTOS;
    }

    @Override
    public List<AppoinmentInfoDTO> getAppoinmentsInfo() throws Exception {
        List<CustomEntity> appoinments = queryDAO.getAppoinmentsInfo();
        List<AppoinmentInfoDTO> appoinmentInfoDTOS = new ArrayList<>();

        for (CustomEntity customEntity:appoinments){
            appoinmentInfoDTOS.add(new AppoinmentInfoDTO(customEntity.getAppoinmentid(),customEntity.getPatientid(),customEntity.getPatientfname(),
                    customEntity.getDoctorid(),customEntity.getFirstname(),customEntity.getSpecialization(),
                    customEntity.getAppoinmentdate()));

        }
        return appoinmentInfoDTOS;

    }

    @Override
    public List<AppoinmentInfoDTO2> getAppoinmentsInfobyDocID(String doctorid) throws Exception {
        List<CustomEntity> appoinments = queryDAO.getAppoinmentsInfoByDocID(doctorid);
        List<AppoinmentInfoDTO2> appoinmentsinf = new ArrayList<>();
        for(CustomEntity customEntity:appoinments){
            appoinmentsinf.add(new AppoinmentInfoDTO2(customEntity.getAppoinmentid(),
                    customEntity.getPatientid(),
                    customEntity.getPatientfname(),
                    customEntity.getPatientlname(),
                    customEntity.getGender(),
                    customEntity.getPatientemail()));
        }

        return appoinmentsinf;

    }
}
