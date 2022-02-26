import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;


public class Application {

    private Scanner scanner;
    private ArrayList<Contact> contacts;
    
    public Application() {
    
        scanner = new Scanner(System.in);
        
        try {
            ObjectInputStream i = new ObjectInputStream(new FileInputStream("contacts.data"));
            try { contacts = (ArrayList<Contact>) i.readObject(); } catch (Exception beQuiet) {}
            i.close();
        } catch (Exception e) { 
            contacts = new ArrayList<>();
            System.out.println("Blad: " + e); 
        }
    
    }

    public void run() {
           
        while (true) {
            System.out.println("menu");
            System.out.println("(1) nowy kontakt");
            System.out.println("(2) wypisz wszystkie kontakty");
            System.out.println("(3) wyjscie z programu");
            System.out.println("(4) eksport do pliku");
            System.out.println("(5) import z pliku");
            System.out.print("Wybierz opcje: ");
            int option = scanner.nextInt();
            switch (option) {
                case 1: 
                    newContact();
                    break;
                case 2:
                    showContacts();
                    break;
                case 3: 
                    stop();
                    break;
                case 4:
                    exportList();
                    break;
                case 5:
                    importList();
                    break;
                default:
                    System.out.println("Blad! Wybrana opcja nie istnieje.");
                    break;
            }
            
        }
    }
    
    public void newContact() {
        System.out.println("\nDodawanie nowego kontaktu");
        System.out.print("Podaj nazwe kontaktu: ");
        String name = scanner.next();
        System.out.print("Podaj numer telefonu: ");
        int number = scanner.nextInt();
        System.out.print("Podaj email: ");
        String email = scanner.next();
        contacts.add(new Contact(name, number, email));
    }
    
    public void showContacts() {
        Collections.sort(contacts, (Contact c1, Contact c2) -> {
                return c1.getName().compareTo(c2.getName());
            }
        );
        int i = 1;
        for (Contact contact : contacts) {
            System.out.println((i++) + ": " + contact.getName());
        }
               
        while (true) {
            System.out.println("\nWybierz opcje:");
            System.out.println("(a) szczegoly dotyczace znanego kontaktu");
            System.out.println("(b) usun kontakt");
            System.out.println("(c) powrot do menu");
            System.out.print("Wybierz opcje: ");
            char option = scanner.next().charAt(0);
            switch (option) {
                case 'a':
                    System.out.print("Podaj nazwe kontaktu:");
                    String name = scanner.next();
                    for (Contact contact : contacts) {
                        if (contact.getName().equals(name)) {
                            System.out.println("\nSzczegoly:");
                            System.out.println(contact);
                            break;
                        }
                    }
                    break;
                case 'b':
                    System.out.print("Podaj nazwe kontaktu:");
                    name = scanner.next();
                    i = 0;
                    for (Contact contact : contacts) {
                        if (contact.getName().equals(name)) {
                            contacts.remove(i);
                            System.out.println("\nUsunieto kontakt.");
                            break;
                        }
                        i++;
                    }
                    break;
                case 'c': 
                    return;
                default:System.out.println("(4) eksport do pliku");
                    System.out.println("Blad! Wybrana opcja nie istnieje.");
                    break;
            
            }
        
        }
    
    }
    
    public void stop() {
    
        System.out.println("Konczenie dzialania programu ...");
        System.out.println("[ OK ]");
        System.out.println("Tworzenie kopii pliku contacts.data: contacts.data.bak ...");
        
        Path src = Paths.get("contacts.data");
        Path dest = Paths.get("contacts.data.bak");
        try {
            Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("[ OK ]");
        } catch (Exception e) {
            System.out.println("[ ERROR ]" + e);
        }
        
        System.out.println("Serializowanie danych w pliku contacts.data ... ");
        
        try {
            ObjectOutputStream o = new ObjectOutputStream(
                new FileOutputStream("contacts.data"));
            o.writeObject(contacts);
            o.close();
            System.out.println("[ OK ]");
        } 
        catch (IOException e) {
            System.out.println("[ ERROR ]" + e);
        }
        
        System.out.println("KONIEC");
        System.exit(0);
    
    }
    
    public void exportList() {
        System.out.print("\nPodaj nazwe pliku: ");
        String fileName = scanner.next();
        try {
            PrintWriter out = new PrintWriter(fileName);
            for (Contact contact : contacts) {
                out.print(contact.toString() + "\n");
            }
            out.close();
            System.out.println("\nZapisano dane do pliku");
        } catch(IOException e) {
            System.out.println("Blad podczas zapisywania do pliku!");
        }
    }
    
    public void importList() {
    
        System.out.print("\nPodaj nazwe pliku: ");
        String fileName = scanner.next();
        try {
            File file = new File(fileName);
            Scanner fScanner = new Scanner(file);
            while (fScanner.hasNext()) {
                fScanner.next();
                String name = fScanner.next();
                fScanner.next();
                String number = fScanner.next();
                fScanner.next();
                String email = fScanner.next();                
                contacts.add(new Contact(name, Integer.parseInt(number), email));
            }

        } catch(Exception e) {
            System.out.println("Blad podczas odczytywania z pliku!");
        }
    }
    

}
