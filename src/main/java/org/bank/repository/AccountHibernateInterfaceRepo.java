package org.bank.repository;

import org.bank.entity.Account;

import java.util.List;

public interface AccountHibernateInterfaceRepo {
    Account save(Account account);
    void update(Account account);
    void delete(Account account);
    Account findById(int id);
    List<Account> findAll();
}
