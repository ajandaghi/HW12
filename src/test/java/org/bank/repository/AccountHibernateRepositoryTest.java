package org.bank.repository;

import org.bank.Connect;
import org.bank.entity.Account;
import org.bank.entity.AccountType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountHibernateRepositoryTest {

    Account account;
    AccountHibernateRepository accountHibernateRepository;
    @BeforeAll
    public  static void setup() {
        Connection   connection= Connect.getInstance().getConnect();
    }

    @BeforeEach
    public void beforeEach() throws SQLException {
        accountHibernateRepository= new AccountHibernateRepository();

    }

    @AfterEach
    public void cleanUp() throws SQLException {
        // deletes all records in table.
        String delete = "DELETE FROM Account ";
        PreparedStatement preparedStatement = Connect.getInstance().getConnect().prepareStatement(delete);
        preparedStatement.execute();
        preparedStatement.close();
    }

    @Test
    public void insertTest() throws SQLException {
        // Arrange -->
        account = new Account("1.1.1",1,1, AccountType.Saving,1L,-1);

        // Act
        accountHibernateRepository.save(account);


        // Assert
        Account loadedAccount = accountHibernateRepository.findById(account.getId());
        assertAll(
                () -> assertNotNull(loadedAccount),
                () -> assertNotNull(loadedAccount.getId()),
                () -> assertEquals("1.1.1", loadedAccount.getAccountNo())
        );
    }

    @Test
    public void findAllTest() throws SQLException {
        // Arrange
        accountHibernateRepository.save(new Account("1.1.1",1,1, AccountType.Saving,1L,-1));
        accountHibernateRepository.save(new Account("1.1.2",1,1, AccountType.Saving,1L,-1));
        accountHibernateRepository.save(new Account("1.1.3",1,1, AccountType.Saving,1L,-1));

        // Act
        List<Account> accounts = accountHibernateRepository.findAll();

        // Assert
        assertEquals(accounts.size(),3);

        // Assert GreaterThanOrEqual
        /*Assertions.assertThat(wallets.size()).isGreaterThanOrEqualTo(3);*/
        //assertThat(wallets).hasSizeGreaterThan(3);
        //assertEquals(3, wallets.size());
    }

    @Test
    public void updateTest() throws SQLException {
        // Arrange
        account = new Account("1.1.1",1,1, AccountType.Saving,1L,-1);


        // Act
       Account account1= accountHibernateRepository.save(account);
       account1.setAccountNo("2.2.2");
        accountHibernateRepository.update(account1);

        // Assert
        Account loadedAccount = accountHibernateRepository.findById(account1.getId());
        assertEquals(loadedAccount.getId(),account1.getId());
       assertEquals(loadedAccount.getAccountNo(),account1.getAccountNo());
    }

    @Test
    public void deleteTest() throws SQLException {
        // Arrange
        account = new Account("1.1.1",1,1, AccountType.Saving,1L,-1);
       Account account1= accountHibernateRepository.save(account);

        // Act
        accountHibernateRepository.delete(account1);

        // Assert
        assertTrue(accountHibernateRepository.findAll().isEmpty());
    }



}