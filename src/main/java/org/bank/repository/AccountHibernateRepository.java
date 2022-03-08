package org.bank.repository;

import org.bank.entity.Account;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.List;

public class AccountHibernateRepository implements AccountHibernateInterfaceRepo{
    private SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    @Override
    public Account save(Account account) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.save(account);
                transaction.commit();
                return account;
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public void update(Account account) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.update(account);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }

    }

    @Override
    public void delete(Account account) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.delete(account);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }



    @Override
    public Account findById(int id) {
        try (var session = sessionFactory.openSession()) {
            return session.find(Account.class, id);
        }    }

    @Override
    public List<Account> findAll() {

        try (var session = sessionFactory.openSession()) {

            String hql = "FROM Account";
            var query = session.createQuery(hql, Account.class);
            List<Account> accounts = query.list();
            return accounts;
        }
    }
}
