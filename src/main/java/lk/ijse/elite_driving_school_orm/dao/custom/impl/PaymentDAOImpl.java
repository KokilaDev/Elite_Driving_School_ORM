package lk.ijse.elite_driving_school_orm.dao.custom.impl;

import lk.ijse.elite_driving_school_orm.dao.custom.PaymentDAO;
import lk.ijse.elite_driving_school_orm.entity.Payment;
import lk.ijse.elite_driving_school_orm.entity.Student;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public List<Payment> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public String getLastId() throws SQLException {
        return null;
    }

    @Override
    public boolean save(Payment payment) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Payment payment) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public List<String> getAllIds() throws SQLException {
        return List.of();
    }

    @Override
    public Optional<Payment> findById(String id) throws SQLException {
        return Optional.empty();
    }
}
