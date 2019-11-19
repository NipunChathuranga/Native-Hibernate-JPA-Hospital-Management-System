package lk.ijse.dep.hms.dao;

import lk.ijse.dep.hms.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory() {

    }


    public static DAOFactory getInstance() {
        return (daoFactory == null) ? (daoFactory = new DAOFactory()) : daoFactory;
    }

    public <T extends SuperDAO> T getDAO(DAOTypes daoType) {

        switch (daoType) {

            case PATIENT:
                return (T) new PatientDAOImpl();
            case DOCTOR:
                return (T) new DoctorDAOImpl();
            case APPOINMENT:
                return (T) new AppoinmentDAOImpl();
            case MEDICINE:
                return (T) new MedicineDAOImpl();
            case PRESCRIPTION:
                return (T) new PrescriptionDAOImpl();
            case PRESCRIPTION_DETAIL:
                return (T) new PrescriptionDetailDAOImpl();
            case QUERY:
                return (T) new QueryDAOImpl();
            default:
                return null;


        }
    }

}
