package store.repository;

import org.bank.entity.Account;
import store.entity.Customer;

import java.util.List;

public interface CustHibernateInterfaceRepo {
    Customer save(Customer customer);
    void update(Customer customer);
    void delete(Customer customer);
    Customer findById(int id);
    List<Customer> findAll();
}
