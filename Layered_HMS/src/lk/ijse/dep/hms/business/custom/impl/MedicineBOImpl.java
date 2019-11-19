package lk.ijse.dep.hms.business.custom.impl;

import lk.ijse.dep.hms.business.custom.MedicineBO;
import lk.ijse.dep.hms.business.exception.AlreadyExistsInPrescriptionDetailException;
import lk.ijse.dep.hms.dao.DAOFactory;
import lk.ijse.dep.hms.dao.DAOTypes;
import lk.ijse.dep.hms.dao.custom.MedicineDAO;
import lk.ijse.dep.hms.dao.custom.PrescriptionDetailDAO;
import lk.ijse.dep.hms.dto.MedicineDTO;
import lk.ijse.dep.hms.entity.Medicine;

import java.util.ArrayList;
import java.util.List;

public class MedicineBOImpl implements MedicineBO {

    private MedicineDAO medicineDAO = DAOFactory.getInstance().getDAO(DAOTypes.MEDICINE);
    private PrescriptionDetailDAO prescriptionDetailDAO = DAOFactory.getInstance().getDAO(DAOTypes.PRESCRIPTION_DETAIL);

    @Override
    public boolean saveMedicine(MedicineDTO medicine) throws Exception {
        return medicineDAO.save(new Medicine(medicine.getMedicineid(), medicine.getDrugname(),
                medicine.getBrandname(), medicine.getDrugtype()));
    }

    @Override
    public boolean updateMedicine(MedicineDTO medicine) throws Exception {
        return medicineDAO.update(new Medicine(medicine.getMedicineid(), medicine.getDrugname(),
                medicine.getBrandname(), medicine.getDrugtype()));
    }

    @Override
    public boolean deleteMedicine(String medicineid) throws Exception {
        if (prescriptionDetailDAO.existsByMedicineID(medicineid)) {
            throw new AlreadyExistsInPrescriptionDetailException("Medicine already exists in a prescription detail, hence unable to delete");
        }
        return medicineDAO.delete(medicineid);
    }

    @Override
    public List<MedicineDTO> findAllMedicines() throws Exception {
        List<Medicine> medicines = medicineDAO.findAll();
        List<MedicineDTO> medicineDTOS = new ArrayList<>();
        for (Medicine medicine : medicines) {
            medicineDTOS.add(new MedicineDTO(medicine.getMedicineid(), medicine.getDrugname(),
                    medicine.getBrandname(), medicine.getDrugtype()));
        }

        return medicineDTOS;
    }

    @Override
    public String getLastMedicineId() throws Exception {
        return medicineDAO.getLastMedicineId();
    }

    @Override
    public MedicineDTO findMedicine(String medicineid) throws Exception {
        Medicine medicine = medicineDAO.find(medicineid);

        return new MedicineDTO(medicine.getMedicineid(), medicine.getDrugname(),
                medicine.getBrandname(), medicine.getDrugtype());
    }

    @Override
    public List<String> getAllMedicineIDs() throws Exception {
        List<Medicine> medicines = medicineDAO.findAll();
        List<String> medicineids = new ArrayList<>();

        for (Medicine medicine : medicines) {
            medicineids.add(medicine.getMedicineid());
        }

        return medicineids;
    }
}
