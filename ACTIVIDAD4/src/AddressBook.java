import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
//Creamos la clase
public class AddressBook {
    //Contacts lo definimos como un HashMap para almacenar los contactos
    private Map<String, String> contacts;
    //Nombre del archivo donde vamos a cargar y guardar los contactos
    private final String fileName = "addressBook.txt";
//Llamamos a load para cargar contactos si ya se escribieron el .txt
    // y starMenu para inicializar el interfaz
    public AddressBook() {
        contacts = new HashMap<>();
        load();
        startMenu();
    }
//Carga los contactos del .txt por cierto, el txt está ubicado en la
    //carpeta del proyecto IDEA, seguimos. Los carga al HashMap.
    //Se leen las lineas del archivo y los separamos por coma.
    private void load() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                contacts.put(parts[0], parts[1]);
            }
        } catch (IOException e) {
            System.err.println("Error al cargar la agenda desde el archivo.");
        }
    }
//En este metódo guardaos los contactos desde el Hashmap y los escribe en
    //el formato CSV
    private void save() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Map.Entry<String, String> entry : contacts.entrySet()) {
                bw.write(entry.getKey() + "," + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error al guardar la agenda en el archivo.");
        }
    }
//Imprimimos "contactos" para dar a entender la info
    //Con el fot se itera cada entrada en el HashMap
    //Después volvemos a imprimir pero ahora cada contacto, el getkey obtiene el teléfono
    // y el get.Value el nombre y damos formato
    private void listContacts() {
        System.out.println("Contactos:");
        for (Map.Entry<String, String> entry : contacts.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
//Creamos la instacia Scanner para leer la entrada del usuario
    //Imprimimos la solicitud a la entrada en este caso el numero de teléfono
    //Leemos la entrada y la almacenamos en PhoneNumber
    //Imprimimos la solicitud ahora por el nombre
    // e igual lo guardamos en una variable en este caso name
    //Agregamos el usuario al HashMap contacts como clave número y nombre
    //E imprimimos la confirmación de la tarea ralizada con éxtio
    private void createContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el número telefónico: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Ingrese el nombre: ");
        String name = scanner.nextLine();
        contacts.put(phoneNumber, name);
        System.out.println("Contacto creado.");
    }
//Usamos Scanner para leer la entrada nuevamente
    //Imprimimos la solicitud de entrada en esta caso será el número a borrar
    //Guardamos la entrada en la variable phoneNumber
    //Abrimos if y verificamos si el HashMap contiene la clave
    //que el usuario ingreso, si sí exíste se borra el contacto del HashMap
    //Imprimimos "contacto eliminado" para confirmar la tarea ralizada
    //si no, imprimimos que el número no se encontró
    private void deleteContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el número telefónico: ");
        String phoneNumber = scanner.nextLine();
        if (contacts.containsKey(phoneNumber)) {
            contacts.remove(phoneNumber);
            System.out.println("Contacto eliminado.");
        } else {
            System.out.println("No se encontró el contacto.");
        }
    }
//Iniciamos con un while para hacer un bucle hasta que se use System.exit(0)
    //Mostramos el menú de opciones por medio de imprimir los comandos
    //En este caso 1 es la lista, 2 crear,3 eliminar y 4 guardar y salir
    private void startMenu() {
        while (true) {
            System.out.println("\nAgenda:");
            System.out.println("1. Lista de contactos");
            System.out.println("2. Crear contacto");
            System.out.println("3. Eliminar contacto");
            System.out.println("4. Guardar y salir");
//Creamos una instacia para leer la entrada del usuario, imprimimos la solicitud para la entrada
            //y leemos la entrada
            Scanner scanner = new Scanner(System.in);
            System.out.print("Seleccione una opción");
            int choice = scanner.nextInt();
//Aquí simplemente llamamos a los métodos que se vayan seleccionando
            switch (choice) {
                case 1:
                    listContacts();
                    break;
                case 2:
                    createContact();
                    break;
                case 3:
                    deleteContact();
                    break;
                case 4:
                    save();
                    System.out.println("Cambios guardados.");
                    System.exit(0);
                    //Si no se ingreso un comando valido imprimimos mensaje diciendolo
                default:
                    System.out.println("Comando no valido.");
            }
        }
    }

    public static void main(String[] args) {
        new AddressBook();
    }
}
