package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoadBalancerTest {
    private LoadBalancer lb;
    private final Random random = mock(Random.class);

    @BeforeEach
    void initLoadBalancer() {
        lb = new LoadBalancer(random, 10);
    }

    @Test
    void randomNotNull() {
        assertThrows(IllegalArgumentException.class, () -> new LoadBalancer(null, 10));
    }

    @Test
    void invalidMaxMachinesRegistered() {
        assertThrows(IllegalArgumentException.class, () -> new LoadBalancer(random, -1));
    }

    @Test
    void registersAndReturns() {
        String ip = "10.0.0.1";

        lb.register(ip);

        assertEquals(ip, lb.get());
    }

    @Test
    void rejectsDoubles() {
        String ip = "10.0.0.1";

        lb.register(ip);

        assertThrows(IllegalArgumentException.class, () -> lb.register(ip));
    }

    @Test
    void rejectsBlank() {
        String ip = "";

        assertThrows(IllegalArgumentException.class, () -> lb.register(ip));
    }

    @Test
    void rejectsNullRegister() {
        String ip = null;

        assertThrows(IllegalArgumentException.class, () -> lb.register(ip));
    }

    @Test
    void rejectsMoreThan10() {
        String ipPrefix = "10.0.0.";

        for (int i = 1; i <= 10; i++)
            lb.register(ipPrefix + i);

        assertThrows(IllegalArgumentException.class, () -> lb.register(ipPrefix + 11));
    }

    @Test
    void randomIndex() {
        String ipPrefix = "10.0.0.";

        String ip1 = ipPrefix + 1;
        String ip2 = ipPrefix + 2;

        lb.register(ip1);
        lb.register(ip2);

        when(random.nextInt(2)).thenReturn(1);

        assertEquals(ip2, lb.get());

        verify(random).nextInt(2);
    }
}