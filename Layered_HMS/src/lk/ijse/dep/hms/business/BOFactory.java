package lk.ijse.dep.hms.business;

import lk.ijse.dep.hms.business.custom.impl.*;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getInstance() {
        return (boFactory == null) ? (boFactory = new BOFactory()) : boFactory;

    }

    public <T extends SuperBO> T getBO(BOTypes boTypes) {
        switch (boTypes) {
            case DOCTOR:
                return (T) new DoctorBOImpl();
            case PATIENT:
                return (T) new PatientBOImpl();
            case MEDICINE:
                return (T) new MedicineBOImpl();
            case PRESCRIPTION:
                return (T) new PrescriptionBOImpl();
            case APPOINMENT:
                return (T) new AppoinmentBOImpl();

            default:
                return null;
        }
    }

}
