package Diary;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    private final ArrayList<DiaryEntry> entries;
    private final Scanner scanner;

    public App() {
        entries = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        String menu = """  
                --- Diary Application ---  
                1. Add Entry  
                2. View Entries  
                3. Delete Entry  
                4. Exit  
                Choose an option: """;
        System.out.print(menu);
    }

    public void addEntry() {
        System.out.print("Enter your entry: ");
        String content = scanner.nextLine();
        DiaryEntry entry = new DiaryEntry(content);
        entries.add(entry);
        System.out.println("Entry added successfully!");
    }

    public void viewEntries() {
        if (entries.isEmpty()) {
            System.out.println("No entries found!");
            return;
        }
        System.out.println("Your entries:");
        for (int i = 0; i < entries.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, entries.get(i).getContent());
        }
    }

    public void deleteEntry() {
        if (entries.isEmpty()) {
            System.out.println("No entries found!");
            return;
        }

        viewEntries();

        while (true) {
            try {
                System.out.print("Enter the entry number to delete: ");
                String input = scanner.nextLine();

                if (input.isEmpty()) {
                    System.out.println("No input provided. Please enter a valid number!");
                    continue;
                }

                int index = Integer.parseInt(input);
                if (index < 1 || index > entries.size()) {
                    System.out.println("Invalid entry number! Please enter a number between 1 and " + entries.size() + ".");
                    continue;
                }

                entries.remove(index - 1);
                System.out.println("Entry deleted successfully!");
                break;

            } catch (NumberFormatException e) {
                System.out.println("Invalid entry number! Please enter a valid number.");
            }
        }
    }

    public void run() {
        while (true) {
            displayMenu();
            try {
                String input = scanner.nextLine();
                if (input.isEmpty()) {
                    System.out.println("No input provided. Please choose a valid option!");
                    continue;
                }

                int option = Integer.parseInt(input);
                switch (option) {
                    case 1->
                        addEntry();

                    case 2->
                        viewEntries();

                    case 3->
                        deleteEntry();

                    case 4->{
                        System.out.println("Exiting...");
                        scanner.close();
                        System.exit(0);}

                    default-> {
                        System.out.println("Invalid option! Please try again.");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }
}