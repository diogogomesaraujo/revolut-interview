package org.example;

import java.util.*;

public class LoadBalancer {
    private final List<String> machines;
    private final Random random;
    private final int maxMachinesRegistered;

    public LoadBalancer(Random random, int maxMachinesRegistered) {
        if (maxMachinesRegistered < 0)
            throw new IllegalArgumentException("The maximum number of machines should be >= 0.");

        if (random == null)
            throw new IllegalArgumentException("Random should not be null");

        this.machines = new ArrayList<String>();
        this.random = random;
        this.maxMachinesRegistered = maxMachinesRegistered;
    }

    public synchronized void register(String a) {
        if (machines.size() >= maxMachinesRegistered)
            throw new IllegalArgumentException("There are already " + maxMachinesRegistered + " machines registered.");

        if (a == null || a.isBlank())
            throw new IllegalArgumentException("A machine can't have a blank ip address");

        if (machines.contains(a))
            throw new IllegalArgumentException("The machine is already registered");

        machines.add(a);
    }

    public synchronized String get() {
        if (machines.isEmpty())
            throw new IllegalArgumentException("There aren't machines available.");

        return machines.get(random.nextInt(machines.size()));
    }
}
