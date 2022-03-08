package store.repository;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

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