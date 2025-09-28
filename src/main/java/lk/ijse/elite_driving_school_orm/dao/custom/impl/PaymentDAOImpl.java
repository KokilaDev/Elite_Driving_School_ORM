package lk.ijse.elite_driving_school_orm.dao.custom.impl;

import lk.ijse.elite_driving_school_orm.config.FactoryConfiguration;
import lk.ijse.elite_driving_school_orm.dao.custom.PaymentDAO;
import lk.ijse.elite_driving_school_orm.entity.Payment;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PaymentDAOImpl implements PaymentDAO {
    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public List<Payment> getAll() throws SQLException {
        Session session = factoryConfiguration.getSession();
        try {
            Query<Payment> query = session.createQuery("FROM Payment", Payment.class);
            List<Payment> paymentList = query.list();
            return paymentList;
        } finally {
            session.close();
        }
    }

    @Override
    public String getLastId() throws SQLException {
        Session session = factoryConfiguration.getSession();
        try {
            Query<String> query = session.createQuery(
                    "SELECT pay.id FROM Payment pay ORDER BY pay.id DESC",
                    String.class
            ).setMaxResults(1);
            List<String> paymentList = query.list();
            if (paymentList.isEmpty()) {
                return null;
            }
            return paymentList.get(0);
        } finally {
            session.close();
        }
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
        Session session = factoryConfiguration.getSession();
        try {
            Payment payment = session.get(Payment.class, id);
            return Optional.ofNullable(payment);
        } finally {
            session.close();
        }
    }
}
