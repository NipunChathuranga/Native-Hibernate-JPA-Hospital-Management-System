package lk.ijse.dep.hms.business.custom.impl;

import lk.ijse.dep.hms.business.custom.PrescriptionBO;
import lk.ijse.dep.hms.dao.DAOFactory;
import lk.ijse.dep.hms.dao.DAOTypes;
import lk.ijse.dep.hms.dao.custom.PrescriptionDAO;
import lk.ijse.dep.hms.dao.custom.PrescriptionDetailDAO;
import lk.ijse.dep.hms.dao.custom.QueryDAO;
import lk.ijse.dep.hms.db.DBConnection;
import lk.ijse.dep.hms.dto.PrescriptionDTO;
import lk.ijse.dep.hms.dto.PrescriptionDetailDTO;
import lk.ijse.dep.hms.dto.PrescriptionHistoryDTO;
import lk.ijse.dep.hms.entity.CustomEntity;
import lk.ijse.dep.hms.entity.Prescription;
import lk.ijse.dep.hms.entity.PrescriptionDetail;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionBOImpl implements PrescriptionBO {


    private PrescriptionDAO prescriptionDAO = DAOFactory.getInstance().getDAO(DAOTypes.PRESCRIPTION);
    private PrescriptionDetailDAO prescriptionDetailDAO = DAOFactory.getInstance().getDAO(DAOTypes.PRESCRIPTION_DETAIL);
    private QueryDAO queryDAO = DAOFactory.getInstance().getDAO(DAOTypes.QUERY);

    @Override
    public String getLastPrescriptionId() throws Exception {
        return prescriptionDAO.getLastPrescriptionId();
    }

    @Override
    public boolean issuePrescription(PrescriptionDTO prescriptionDTO) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        try {

            // Let's start a transaction
            connection.setAutoCommit(false);
            String presid = prescriptionDTO.getPrescriptionid();
            boolean result = prescriptionDAO.save(new Prescription(presid, prescriptionDTO.getAppoinmentid(),
                    prescriptionDTO.getPrescriptiondate()));
            if (!result) {
                connection.rollback();
                throw new RuntimeException("Sorry, Something went wrong");
            }


            for (PrescriptionDetailDTO presdetails : prescriptionDTO.getPrescriptionDetails()) {
                result = prescriptionDetailDAO.save(new PrescriptionDetail(presid, presdetails.getMedicineid()));

                if (!result) {
                    connection.rollback();
                    throw new RuntimeException("Sorry, Something went wrong");
                }

            }

            connection.commit();
            return true;

        } catch (Throwable e) {

            try {
                connection.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public List<PrescriptionHistoryDTO> getPrescriptionHistoryByDoc(String docid) throws Exception {
        List<CustomEntity> preshistory = queryDAO.getPrescriptionHistoryByDocID(docid);
        List<PrescriptionHistoryDTO> prhistorydto = new ArrayList<>();
        for (CustomEntity customEntity : preshistory) {

            prhistorydto.add(new PrescriptionHistoryDTO(customEntity.getPrescriptionid(),
                    customEntity.getAppoinmentid(),
                    customEntity.getPrescriptiondate(),
                    customEntity.getPatientid(),
                    customEntity.getPatientfname(),
                    customEntity.getPatientlname()));
        }
        System.out.println(prhistorydto);
        return prhistorydto;
    }

    @Override
    public List<PrescriptionDTO> findAllPrescriptions() throws Exception {
        List<Prescription> prescriptions = prescriptionDAO.findAll();
        List<PrescriptionDTO> prescriptionDTOS = new ArrayList<>();
        for(Prescription prescription:prescriptions){

            prescriptionDTOS.add(new PrescriptionDTO(prescription.getPrescriptionid(),
                    prescription.getAppoinmentid(),prescription.getPrescriptiondate()));
        }
        return prescriptionDTOS;
    }
}
