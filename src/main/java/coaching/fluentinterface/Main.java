package coaching.fluentinterface;

public class Main {

    /**
     * Die {@link Person} Klasse ist als fluent interface implementiert. Die Methoden, die den Zustand verändern, geben
     * immer wieder die {@link Person}-Instanz zurück, so dass die Methodenaufrufe einfach aneinandergehängt werden können.
     * Sieht ganz elegant aus und fühlt sich auch gut an, ist aber manchmal blöd zu debuggen.
     * Andere Beispiele sind {@link java.util.stream.Stream}, {@link java.util.Optional}, viele Builder Implementierungen
     * und assertj assertions.
     */
    public static void main(String[] args) {
        var p = new Person()
                .setFirstname("André")
                .setLastname("Schreck")
                .setAge(38);

        System.out.println(p);
    }

}
