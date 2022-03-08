package store.repository;


import org.bank.entity.Account;
import org.hibernate.SessionFactory;
import store.entity.Customer;

import java.util.List;

public class CustomerHibernateRepository implements CustHibernateInterfaceRepo {
    private SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    @Override
    public Customer save(Customer customer) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.save(customer);
                transaction.commit();
                return customer;
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public void update(Customer customer) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.update(customer);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public void delete(Customer customer) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.delete(customer);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public Customer findById(int id) {
        try (var session = sessionFactory.openSession()) {
            return session.find(Customer.class, id);
        }
    }

    @Override
    public List<Customer> findAll() {
        try (var session = sessionFactory.openSession()) {

            String hql = "FROM Customer";
            var query = session.createQuery(hql, Customer.class);
            List<Customer> customers = query.list();
            return customers;
        }
    }
}
