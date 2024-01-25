package coaching.dependencyinjection;

/**
 * {@link ClientA} bekommt den Service per constructor injection
 */
public class ClientA {

    private final Service service;

    public ClientA(Service service) {
        this.service = service;
    }

    public void doSomething() {
        service.perform();
    }

}
