package lk.ijse.dep.hms.business.custom.impl;

import lk.ijse.dep.hms.business.custom.DoctorBO;
import lk.ijse.dep.hms.business.exception.AlreadyExistsInAppoinmentException;
import lk.ijse.dep.hms.dao.DAOFactory;
import lk.ijse.dep.hms.dao.DAOTypes;
import lk.ijse.dep.hms.dao.custom.AppoinmentDAO;
import lk.ijse.dep.hms.dao.custom.DoctorDAO;
import lk.ijse.dep.hms.dto.DoctorDTO;
import lk.ijse.dep.hms.entity.Doctor;

import java.util.ArrayList;
import java.util.List;

public class DoctorBOImpl implements DoctorBO {

    private DoctorDAO doctorDAO = DAOFactory.getInstance().getDAO(DAOTypes.DOCTOR);
    private AppoinmentDAO appoinmentDAO = DAOFactory.getInstance().getDAO(DAOTypes.APPOINMENT);

    @Override
    public boolean saveDoctor(DoctorDTO doctor) throws Exception {

        return doctorDAO.save(new Doctor(doctor.getDoctorid(), doctor.getFirstname(), doctor.getLastname(),
                doctor.getFee(), doctor.getSpecialization(), doctor.getEmail(), doctor.getPassword()));
    }

    @Override
    public boolean updateDoctor(DoctorDTO doctor) throws Exception {
        return doctorDAO.update(new Doctor(doctor.getDoctorid(), doctor.getFirstname(), doctor.getLastname(),
                doctor.getFee(), doctor.getSpecialization(), doctor.getEmail(), doctor.getPassword()));
    }

    @Override
    public boolean deleteDoctor(String doctorid) throws Exception {
        if (appoinmentDAO.existsByDoctorID(doctorid)) {
            throw new AlreadyExistsInAppoinmentException("Doctor already exists in an appoinment, hence unable to delete");
        }
        return doctorDAO.delete(doctorid);
    }

    @Override
    public List<DoctorDTO> findAllDoctors() throws Exception {
        List<Doctor> doctors = doctorDAO.findAll();
        List<DoctorDTO> doctorDTOS = new ArrayList<>();
        for (Doctor doctor : doctors) {
            doctorDTOS.add(new DoctorDTO(doctor.getDoctorid(), doctor.getFirstname(), doctor.getLastname(),
                    doctor.getFee(), doctor.getSpecialization(), doctor.getEmail(), doctor.getPassword()));
        }

        return doctorDTOS;
    }

    @Override
    public String getLastDoctorId() throws Exception {
        return doctorDAO.getLastDoctorId();
    }

    @Override
    public DoctorDTO findDoctor(String doctorid) throws Exception {
        Doctor doctor = doctorDAO.find(doctorid);

        return new DoctorDTO(doctor.getDoctorid(), doctor.getFirstname(), doctor.getLastname(),
                doctor.getFee(), doctor.getSpecialization(), doctor.getEmail(), doctor.getPassword());
    }

    @Override
    public List<String> getAllDoctorIDs() throws Exception {
        List<Doctor> doctors = doctorDAO.findAll();
        List<String> doctorids = new ArrayList<>();

        for (Doctor doctor : doctors) {
            doctorids.add(doctor.getDoctorid());
        }

        return doctorids;

    }
}
