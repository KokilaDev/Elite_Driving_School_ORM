package lk.ijse.elite_driving_school_orm.bo.custom.impl;

import lk.ijse.elite_driving_school_orm.bo.custom.PaymentBO;
import lk.ijse.elite_driving_school_orm.bo.util.EntityDTOConverter;
import lk.ijse.elite_driving_school_orm.dao.DAOFactory;
import lk.ijse.elite_driving_school_orm.dao.DAOTypes;
import lk.ijse.elite_driving_school_orm.dao.custom.PaymentDAO;
import lk.ijse.elite_driving_school_orm.dto.PaymentDTO;
import lk.ijse.elite_driving_school_orm.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {
    private final PaymentDAO paymentDAO = DAOFactory.getInstance().getDAO(DAOTypes.PAYMENT);

    private final EntityDTOConverter converter = new EntityDTOConverter();

    @Override
    public List<PaymentDTO> getAllPayment() throws SQLException {
        List<Payment> payments = paymentDAO.getAll();
        List<PaymentDTO> paymentDTOS = new ArrayList<>();
        for (Payment payment : payments ) {
            paymentDTOS.add(converter.getPaymentDTO(payment));
        }
        return paymentDTOS;
    }

    @Override
    public String getNextId() throws SQLException {
        String lastId =  paymentDAO.getLastId();
        char tablechar = 'P';
        if (lastId != null){
            String lastNumberString = lastId.substring(1);
            int lastNumber = Integer.parseInt(lastNumberString);
            int nextId = lastNumber + 1;
            return String.format(tablechar +"%03d",nextId);
        }

        return tablechar + "001";
    }
}
