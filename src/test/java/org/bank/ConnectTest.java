package org.bank;

import org.bank.repository.SessionFactorySingleton;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConnectTest {

    @Test
    public void testConnection() {
        assertDoesNotThrow(() -> Connect.getInstance().getConnect());
    }

    @Test
    public void testSessionFactory(){
        assertDoesNotThrow(() -> SessionFactorySingleton.getInstance().openSession());
    }
}