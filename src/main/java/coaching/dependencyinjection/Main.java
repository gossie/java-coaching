package coaching.dependencyinjection;

import java.util.Random;

public class Main {

    /**
     * Die main Funktion ist unser dependency injector und steckt die Instanzen richtig zusammen. Hier wird als {@link Service}
     * der {@link OldService} instanziiert. Das das hier die einzige Stelle ist, wo eine Instanz erzeugt wird, kann man
     * den {@link OldService} sehr einfach gegen den {@link NewService} austauschen. {@link ClientA} und {@link ClientB}
     * ist es egal, weil sie nur eine Referenz auf das {@link Service} Interface erwarten.
     */
    public static void main(String[] args) {
        var service = new OldService();

        var clientA = new ClientA(service);
        var clientB = new ClientB();

        var random = new Random().nextInt();
        if (random % 2 == 0) {
            clientB.setService(service);
        }

        clientA.doSomething();
        clientB.doSomethingIfServiceIsPresent();
    }
}