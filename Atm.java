import java.text.SimpleDateFormat;
import java.util.*;

public class Atm {
    static Scanner scanner= new Scanner(System.in);
    static int total_money = 1000;
    static Map<String, String[]> transaction_history = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("Cajero Automatico");
        System.out.println("Ingresa tu PIN: ");
        String pin = scanner.nextLine();
        int counter = 0;

        while (!pin.equals("1235")){
            System.out.println("PIN incorrecto");
            counter += 1;
            if(counter == 3){
                System.out.println("Demasiados intentos por hoy, nos vemos!!");
                System.exit(0);
            }
            else{
                clean_console();
                System.out.println("Ingresa nuevamente tu PIN: ");
                pin = scanner.nextLine();
            }
        }

        while(true){
            try{
                displayMenu();
                System.out.println("¿Que operación deseas realizar?");

                Scanner scanner1= new Scanner(System.in);
                int option = scanner1.nextInt();

                if(option == 1){
                    check_money();
                }
                else if(option == 2){
                    withdraw_cash();
                }
                else if(option == 3){
                    display_transaction_history();
                }
                else {
                    clean_console();
                    System.out.println("Opción no valida");
                }
            }
            catch (InputMismatchException e){
                clean_console();
                System.out.println("Ingresa datos númericos unicamente");

            }
        }
    }

    static void displayMenu(){
        System.out.println("Hola Martin, Bienvenido\n\n");
        System.out.println(".......::: MENU :::.......");
        System.out.println("1. Consultar saldo");
        System.out.println("2. Retirar saldo");
        System.out.println("3. Histórico de movimientos");
    }

    static void displaySubmenu(){
        System.out.println("\n\n :: Submenu :: ");
        System.out.println("1. Regresar al menu principal");
        System.out.println("2. Salir");

        System.out.println("¿Que deseas hacer ahora?");
        int option= scanner.nextInt();
        if (option == 2) {
            clean_console();
            System.out.println("Nos vemos Martin");
            System.exit(0);
        }
        else if(option != 1){
            System.out.println("Opción invalida, regresando al menu principal");
        }
        else {
            clean_console();
        }
    }

    static void check_money(){
        clean_console();
        System.out.println("Saldo actual: " + total_money);
        displaySubmenu();
    }

    static void withdraw_cash(){
        clean_console();
        if (total_money == 0) {
            System.out.println("No tienes fondos suficientes");
        }
        else {
            while(true){
                try{

                    System.out.println("Cantidad a retirar: ");
                    Scanner scanner1= new Scanner(System.in);
                    int retiro= scanner1.nextInt();

                    if(retiro > total_money){
                        System.out.println("Saldo insuficiente");
                    }
                    else{
                        total_money -= retiro;
                        clean_console();
                        System.out.println("Retiro de saldo exitoso!");
                        System.out.println("Saldo disponible: " + total_money);
                        add_transaction_history(retiro);
                        break;
                    }
                }
                catch (InputMismatchException e){
                    System.out.println("Ingresa una cantidad valida");
                }
            }
            displaySubmenu();
        }
    }

    static void add_transaction_history(int money_poped){
        Date today = new Date();
        System.out.println("Fecha y hora: " + new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(today));

        final String[] array = new String[] {new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(today), "" + money_poped, "" + (money_poped + total_money)};
        int len = transaction_history.size();
        transaction_history.put("Movimiento " + (len+1) , array);
    }

    static void display_transaction_history(){
        clean_console();

        if(transaction_history.size() == 0 ){
            System.out.println("No tienes movimientos");
        }
        else{
            System.out.println("Histórico de movimientos");

            for (Map.Entry<String, String[]> entry : transaction_history.entrySet()) {
                System.out.println("\n\n");
                System.out.println(entry.getKey()); //Key del MAP
                for (int j = 0; j < entry.getValue().length; j++){
                    if(j == 0){
                        System.out.println("Fecha de retiro: " + entry.getValue()[j]);
                    }
                    if (j == 1){
                        System.out.println("Monto retirado: " + entry.getValue()[j]);
                    }
                    if (j == 2){
                        System.out.println("Cantidad anterior: " + entry.getValue()[j]);
                    }
                }
            }
        }

        displaySubmenu();
    }

    static void clean_console(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}