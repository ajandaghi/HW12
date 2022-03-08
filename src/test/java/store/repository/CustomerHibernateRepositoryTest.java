package store.repository;

import org.bank.Connect;
import org.bank.entity.Account;
import org.bank.entity.AccountType;
import org.bank.repository.AccountHibernateRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.entity.Customer;
import store.entity.enumeration.UserType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerHibernateRepositoryTest {

    Customer customer;
    CustomerHibernateRepository customerHibernateRepository;
    @BeforeAll
    public  static void setup() {
        Connection connection= store.repository.Connect.getInstance().getConnect();
    }

    @BeforeEach
    public void beforeEach() throws SQLException {
        customerHibernateRepository= new CustomerHibernateRepository();

    }

    @AfterEach
    public void cleanUp() throws SQLException {
        // deletes all records in table.
        String delete = "DELETE FROM Customer ";
        PreparedStatement preparedStatement = Connect.getInstance().getConnect().prepareStatement(delete);
        preparedStatement.execute();
        preparedStatement.close();
    }

    @Test
    public void insertTest() throws SQLException {
        // Arrange -->
        customer = new Customer(null,"00000","00000", UserType.CUSTOMER,"teh",0L);

        // Act
        customerHibernateRepository.save(customer);


        // Assert
        Customer loadedCustmer = customerHibernateRepository.findById(customer.getId());
        assertAll(
                () -> assertNotNull(loadedCustmer),
                () -> assertNotNull(loadedCustmer.getId()),
                () -> assertEquals("00000", loadedCustmer.getNationalId())
        );
    }

    @Test
    public void findAllTest() throws SQLException {
        // Arrange
        customerHibernateRepository.save(new Customer(null,"00001","00000", UserType.CUSTOMER,"teh",0L));
        customerHibernateRepository.save(new Customer(null,"00002","00000", UserType.CUSTOMER,"teh",0L));
        customerHibernateRepository.save(new Customer(null,"00003","00000", UserType.CUSTOMER,"teh",0L));

        // Act
        List<Customer> customers = customerHibernateRepository.findAll();

        // Assert
        assertEquals(customers.size(),3);

        // Assert GreaterThanOrEqual
        /*Assertions.assertThat(wallets.size()).isGreaterThanOrEqualTo(3);*/
        //assertThat(wallets).hasSizeGreaterThan(3);
        //assertEquals(3, wallets.size());
    }

    @Test
    public void updateTest() throws SQLException {
        // Arrange
        customer = new Customer(null,"00000","00000", UserType.CUSTOMER,"teh",0L);


        // Act
        Customer customer1= customerHibernateRepository.save(customer);
        customer1.setNationalId("11111");
        customerHibernateRepository.update(customer1);

        // Assert
        Customer loadedCustomer = customerHibernateRepository.findById(customer1.getId());
        assertEquals(loadedCustomer.getId(),customer1.getId());
        assertEquals(loadedCustomer.getNationalId(),customer1.getNationalId());
    }

    @Test
    public void deleteTest() throws SQLException {
        // Arrange
        Customer customer1= new Customer(null,"00000","00000", UserType.CUSTOMER,"teh",0L);

        // Act
        customerHibernateRepository.delete(customer1);

        // Assert
        assertTrue(customerHibernateRepository.findAll().isEmpty());
    }


}