package coaching.dependencyinjection;

/**
 * {@link ClientB} bekommt den Service per setter injection
 */
public class ClientB {

    private Service service;

    public void setService(Service service) {
        this.service = service;
    }

    public void doSomethingIfServiceIsPresent() {
        if (service == null) {
            System.out.println("No service");
            return;
        }

        service.perform();
    }

}
