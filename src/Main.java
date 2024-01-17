import it.pack.classes.ContoCorrente;
import it.pack.classes.ContoOnLine;
import it.pack.exceptions.BancaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Scegli un numero da 1 a 3 per selezionare l'esercizio:");
        int esercizio = scanner.nextInt();

        switch (esercizio) {
            case 1:
                System.out.println("-----Esercizio 1-----");
                int[] randomArr = new int[5];
                for (int i = 0; i < randomArr.length; i++) {
                    Random rand = new Random();
                    randomArr[i] = rand.nextInt(1, 10);
                }
                System.out.println(Arrays.toString(randomArr));

                while (true) {
                    try {
                        System.out.println("Valore da inserire: (oppure 0 per uscire).");
                        Integer newNum = scanner.nextInt();
                        if (newNum.equals(0)) {
                            System.out.println("Ciao ciao");
                            scanner.close();
                            break;
                        } else {
                            System.out.println("Digita la posizione selezionata:");
                            int newPos = scanner.nextInt();
                            scanner.nextLine();
                            randomArr[newPos - 1] = newNum;
                            System.out.println(Arrays.toString(randomArr));
                        }
                    } catch (IndexOutOfBoundsException exception) {
                        System.err.println(exception.getMessage());
                        logger.error("Valore non valido.");
                    }
                }
                scanner.close();
                break;

            case 2:
                System.out.println("-----Esercizio 2-----");
                Scanner scanner2 = new Scanner(System.in);
                while(true){
                    System.out.println("Quanti km hai percorso? (o 0 per uscire)");
                    int numKm = scanner2.nextInt();
                    scanner2.nextLine();
                    if(numKm != 0){
                        System.out.println("Quanto hai consumato?");
                        int consumoLt = scanner2.nextInt();
                        scanner2.nextLine();

                        try{
                            int kmAlLt = numKm/consumoLt;
                            System.out.println("Il consumo totale di km al litro è di: " + kmAlLt);
                        } catch (ArithmeticException exception){
                            System.err.println(exception.getMessage());
                            logger.error("Valore non valido.");
                        }
                    } else {
                        System.out.println("Ciao ciao");
                        scanner2.close();
                        break;
                    }
                }
            case 3:
                System.out.println("-----Esercizio 3-----");
                ContoCorrente conto1 = new ContoCorrente("Grossi Mario", 20000.0);
                System.out.println("Saldo conto: " + conto1.restituisciSaldo());
                try {
                    conto1.preleva(1750.5);
                    System.out.println("Saldo conto: " + conto1.restituisciSaldo());
                } catch (BancaException e) {
                    System.out.println("Errore durante il prelievo: " + e.getMessage());
                    logger.error("Errore prelievo.");
                }
                ContoOnLine conto2 = new ContoOnLine("Rossi Luigi", 50350.0, 1500);
                conto2.stampaSaldo();
                try {
                    conto2.preleva(2000);
                    conto2.stampaSaldo();
                } catch (BancaException e) {
                    System.err.println("Errore durante il prelievo: " + e.getMessage());
                    logger.error("Errore prelievo.");
                }
        }
    }
}