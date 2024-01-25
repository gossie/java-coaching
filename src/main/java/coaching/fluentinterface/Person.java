package coaching.fluentinterface;

public class Person {

    private String firstname;
    private String lastname;
    private int age;

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getAge() {
        return age;
    }

    public Person setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public Person setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public Person setAge(int age) {
        this.age = age;
        return this;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                '}';
    }
}
