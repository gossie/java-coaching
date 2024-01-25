package coaching.dependencyinjection;

class OldService implements Service {
    @Override
    public void perform() {
        System.out.println("Old service is running");
    }
}
