package lk.ijse.dep.hms.business.custom.impl;

import lk.ijse.dep.hms.business.custom.PatientBO;
import lk.ijse.dep.hms.business.exception.AlreadyExistsInAppoinmentException;
import lk.ijse.dep.hms.business.exception.AlreadyExistsInPrescriptionException;
import lk.ijse.dep.hms.dao.DAOFactory;
import lk.ijse.dep.hms.dao.DAOTypes;
import lk.ijse.dep.hms.dao.custom.AppoinmentDAO;
import lk.ijse.dep.hms.dao.custom.PatientDAO;
import lk.ijse.dep.hms.dto.PatientDTO;
import lk.ijse.dep.hms.entity.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientBOImpl implements PatientBO {

    private PatientDAO patientDAO = DAOFactory.getInstance().getDAO(DAOTypes.PATIENT);
    private AppoinmentDAO appoinmentDAO = DAOFactory.getInstance().getDAO(DAOTypes.APPOINMENT);


    @Override
    public boolean savePatient(PatientDTO patient) throws Exception {
        return patientDAO.save(new Patient(patient.getPatientid(),
                patient.getPatientfirstname(), patient.getPatientlastname(),
                patient.getGender(), patient.getCity(), patient.getPatientemail()
        ));
    }

    @Override
    public boolean updatePatient(PatientDTO patient) throws Exception {
        return patientDAO.update(new Patient(patient.getPatientid(),
                patient.getPatientfirstname(), patient.getPatientlastname(),
                patient.getGender(), patient.getCity(), patient.getPatientemail()
        ));
    }

    @Override
    public boolean deletePatient(String patientid) throws Exception {
        if(appoinmentDAO.existsByPatientID(patientid)){
            throw new AlreadyExistsInPrescriptionException("Patient already exists in an appoinment, hence unable to delete");
        }
        return patientDAO.delete(patientid);
    }

    @Override
    public List<PatientDTO> findAllPatients() throws Exception {
        List<Patient> patients = patientDAO.findAll();
        List<PatientDTO> patientDTOS = new ArrayList<>();
        for (Patient patient : patients) {
            patientDTOS.add(new PatientDTO(patient.getPatientid(),
                    patient.getPatientfname(),patient.getPatientlname(),
                    patient.getGender(), patient.getCity(),patient.getPatientemail()
            ));
        }

        return patientDTOS;
    }

    @Override
    public String getLastPatientId() throws Exception {
        return patientDAO.getLastPatientId();
    }

    @Override
    public PatientDTO findPatient(String patientid) throws Exception {
        Patient patient = patientDAO.find(patientid);

        return new PatientDTO(patient.getPatientid(),
                patient.getPatientfname(), patient.getPatientlname(),
                patient.getGender(), patient.getCity(),patient.getPatientemail()
        );
    }

    @Override
    public List<String> getAllPatientIDs() throws Exception {
        List<Patient> patients = patientDAO.findAll();
        List<String> patientids = new ArrayList<>();

        for (Patient patient : patients) {
            patientids.add(patient.getPatientid());
        }

        return patientids;
    }
}
