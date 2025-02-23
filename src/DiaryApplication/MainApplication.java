package DiaryApplication;

import java.util.Scanner;

public class MainApplication {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Diaries diaries = new Diaries();

    public static void main(String[] args) {
        mainMenu();
    }

    private static void createDiary() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        if (username.isEmpty()) {
            System.out.println("Username cannot be empty. Please try again.");
            createDiary();
            return;
        }

        if (diaries.findByUsername(username) != null) {
            System.out.println("Username already exists. Please choose a different username.");
            createDiary();
            return;
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (password.isEmpty()) {
            System.out.println("Password cannot be empty. Please try again.");
            createDiary();
            return;
        }

        diaries.add(username, password);
        System.out.println("Diary created successfully.");
        mainMenu();
    }

    private static void mainMenu() {
        System.out.println("""
            Select an option:
            1. Create Diary
            2. Unlock Diary
            3. Create Entry
            4. Delete Entry
            5. View Entries
            6. Exit
        """);

        int choice = getValidMenuChoice();
        switch (choice) {
            case 1 -> createDiary();
            case 2 -> unlockDiary();
            case 3 -> createEntry();
            case 4 -> deleteEntry();
            case 5 -> viewEntries();
            case 6 -> {
                System.out.println("Exiting...");
                scanner.close();
            }
        }
    }

    private static void unlockDiary() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        Diary diary = diaries.findByUsername(username);

        if (diary == null) {
            System.out.println("Diary not found. Please try again.");
            unlockDiary();
            return;
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        diary.unlock(password);

        if (diary.isLocked()) {
            System.out.println("Incorrect password. Try again.");
            unlockDiary();
            return;
        }

        System.out.println("Diary unlocked successfully.");
        mainMenu();
    }

    private static void createEntry() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        Diary diary = diaries.findByUsername(username);

        if (diary == null) {
            System.out.println("Diary not found.");
            mainMenu();
            return;
        }

        if (diary.isLocked()) {
            System.out.println("Diary is locked. You cannot create an entry until it is unlocked.");
            mainMenu();
            return;
        }

        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter body: ");
        String body = scanner.nextLine();

        diary.createEntry(title, body);
        System.out.println("Entry created successfully.");
        mainMenu();
    }

    private static void deleteEntry() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        Diary diary = diaries.findByUsername(username);

        if (diary == null) {
            System.out.println("Diary not found.");
            mainMenu();
            return;
        }

        System.out.print("Enter entry ID to delete: ");
        int entryId = getInputInt();

        if (entryId < 1 || entryId > diary.getEntries().size()) {
            System.out.println("Invalid entry ID. Please try again.");
            deleteEntry();
            return;
        }

        diary.deleteEntry(entryId);
        System.out.println("Entry deleted successfully.");
        mainMenu();
    }

    private static void viewEntries() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        Diary diary = diaries.findByUsername(username);

        if (diary == null) {
            System.out.println("Diary not found.");
            mainMenu();
            return;
        }

        if (diary.isLocked()) {
            System.out.println("Diary is locked. Unlock it first.");
            mainMenu();
            return;
        }

        if (diary.getEntries().isEmpty()) {
            System.out.println("No entries found.");
        } else {
            System.out.println("Diary Entries:");
            diary.getEntries().forEach(entry -> entry.toString());
        }
        mainMenu();
    }

    private static int getValidMenuChoice() {
        int choice = getInputInt();
        if (choice >= 1 && choice <= 6) {
            return choice;
        }
        System.out.println("Invalid option. Please select a number between 1 and 6.");
        return getValidMenuChoice();
    }

    private static int getInputInt() {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}
