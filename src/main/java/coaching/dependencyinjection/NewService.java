package coaching.dependencyinjection;

class NewService implements Service {
    @Override
    public void perform() {
        System.out.println("New service is running");
    }
}
