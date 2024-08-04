import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class fileListMaker
{

    private static List<String> items = new ArrayList<>();
    private static boolean needsToBeSaved = false;
    private static String filename = null;

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            printMenu();
            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice)
            {
                case "A":
                    addItem(scanner);
                    break;
                case "D":
                    deleteItem(scanner);
                    break;
                case "I":
                    insertItem(scanner);
                    break;
                case "M":
                    moveItem(scanner);
                    break;
                case "O":
                    openList(scanner);
                    break;
                case "S":
                    saveList();
                    break;
                case "C":
                    clearList();
                    break;
                case "V":
                    viewList();
                    break;
                case "Q":
                    quitProgram(scanner);
                    return; // Exit the loop and end the program
                default:
                    System.out.println("Sorry! That is an invalid. Please choose again.");
            }
        }
    }

    private static void printMenu()
    {
        System.out.println("\nMenu:");
        System.out.println("A - Add an item to the list");
        System.out.println("D - Delete an item from the list");
        System.out.println("I - Insert an item into the list");
        System.out.println("M - Move an item in the list");
        System.out.println("O - Open a list file from disk");
        System.out.println("S - Save the current list file to disk");
        System.out.println("C - Clear the list");
        System.out.println("V - View the list");
        System.out.println("Q - Quit the program");
    }

    private static void addItem(Scanner scanner)
    {
        System.out.print("Please enter item to add: ");
        String item = scanner.nextLine();
        items.add(item);
        needsToBeSaved = true;
    }

    private static void deleteItem(Scanner scanner)
    {
        System.out.print("Please enter index of item to delete: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        if (index >= 0 && index < items.size())
        {
            items.remove(index);
            needsToBeSaved = true;
        }
        else
        {
            System.out.println("The index out of range.");
        }
    }

    private static void insertItem(Scanner scanner)
    {
        System.out.print("Please enter index to insert at: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Please enter item to insert: ");
        String item = scanner.nextLine();
        if (index >= 0 && index <= items.size())
        {
            items.add(index, item);
            needsToBeSaved = true;
        }
        else
        {
            System.out.println("This index is out of range.");
        }
    }

    private static void moveItem(Scanner scanner)
    {
        System.out.print("Please enter index of item to move: ");
        int fromIndex = scanner.nextInt();
        System.out.print("Please enter new index to move to: ");
        int toIndex = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        if (fromIndex >= 0 && fromIndex < items.size() && toIndex >= 0 && toIndex <= items.size())
        {
            String item = items.remove(fromIndex);
            items.add(toIndex, item);
            needsToBeSaved = true;
        }
        else
        {
            System.out.println("This index out of range.");
        }
    }

    private static void openList(Scanner scanner)
    {
        if (needsToBeSaved && filename != null)
        {
            System.out.print("You have unsaved changes. Would you like to save them first? (y/n): ");
            String save = scanner.nextLine().trim().toLowerCase();
            if (save.equals("y"))
            {
                saveList();
            }
        }
        System.out.print("Please enter filename to open and make sure to include .txt extension: ");
        filename = scanner.nextLine();
        File file = new File(filename);
        if (file.exists())
        {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                items.clear();
                String line;
                while ((line = reader.readLine()) != null)
                {
                    items.add(line);
                }
                needsToBeSaved = false;
            } catch (IOException e)
            {
                System.out.println("There was an error reading the file: " + e.getMessage());
            }
        }
        else
        {
            System.out.println("This file does not exist.");
        }
    }

    private static void saveList()
    {
        if (filename != null)
        {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename)))
            {
                for (String item : items)
                {
                    writer.write(item);
                    writer.newLine();
                }
                needsToBeSaved = false;
            }
            catch (IOException e)
            {
                System.out.println("There was an error writing file: " + e.getMessage());
            }
        }
        else
        {
            System.out.println("There is no file to save to. Please use option O to open a file first.");
        }
    }

    private static void clearList()
    {
        items.clear();
        needsToBeSaved = true;
    }

    private static void viewList() {
        System.out.println("\nCurrent List:");
        for (int i = 0; i < items.size(); i++)
        {
            System.out.println(i + ": " + items.get(i));
        }
    }

    private static void quitProgram(Scanner scanner)
    {
        if (needsToBeSaved)
        {
            System.out.print("You have unsaved changes. Would you like to save before quitting? (y/n): ");
            String save = scanner.nextLine().trim().toLowerCase();
            if (save.equals("y"))
            {
                if (filename == null)
                {
                    System.out.print("Please enter filename to save the list and make sure to include .txt extension: ");
                    filename = scanner.nextLine();
                }
                saveList();
            }
        }
    }
}
