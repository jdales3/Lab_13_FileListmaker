import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class fileListMaker
{
    private static ArrayList<String> myArrList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static boolean needsToBeSaved = false;
    private static String filename = null;

    public static void main(String[] args)
    {
        boolean running = true;

        while (running)
        {
            displayMenu();
            String command = SafeInput.getRegExString(scanner, "Please enter a command ", "[AaDdIiPpMmOoSsCcVvQq]");
            switch (command.toUpperCase())
            {
                case "A":
                    addItem();
                    break;
                case "D":
                    deleteItem();
                    break;
                case "I":
                    insertItem();
                    break;
                case "M":
                    moveItem();
                    break;
                case "O":
                    openList();
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
                    running = quitProgram();
                    break;
            }
        }
        System.out.println("So long!");
    }

    private static void displayMenu()
    {
        System.out.println("\nCurrent List:");
        viewList();
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

    private static void addItem()
    {
        String item = SafeInput.getRegExString(scanner, "Please enter the item to add: ", ".+");
        myArrList.add(item);
        needsToBeSaved = true;
        System.out.println("Item has been added.");
    }

    private static void deleteItem()
    {
        viewList();
        int index = SafeInput.getRangedInt(scanner, "Please enter the number of the item to delete: ", 1, myArrList.size()) - 1; // Convert to zero-based index
        myArrList.remove(index);
        needsToBeSaved = true;
        System.out.println("Item has been deleted.");
    }

    private static void insertItem()
    {
        viewList();
        int index = SafeInput.getRangedInt(scanner, "Please enter the index to insert the item at: ", 1, myArrList.size() + 1) - 1; // Convert to zero-based index
        String item = SafeInput.getRegExString(scanner, "Please enter the item to insert: ", ".+");
        myArrList.add(index, item);
        needsToBeSaved = true;
        System.out.println("Item has been inserted.");
    }

    private static void moveItem()
    {
        viewList();
        int fromIndex = SafeInput.getRangedInt(scanner, "Please enter the index of the item to move: ", 1, myArrList.size()) - 1; // Convert to zero-based index
        int toIndex = SafeInput.getRangedInt(scanner, "Please enter the new index to move the item to: ", 1, myArrList.size()) - 1; // Convert to zero-based index
        String item = myArrList.remove(fromIndex);
        myArrList.add(toIndex, item);
        needsToBeSaved = true;
        System.out.println("Item has been moved.");
    }

    private static void openList()
    {
        if (needsToBeSaved)
        {
            boolean saveBeforeOpen = SafeInput.getYNConfirm(scanner, "You have unsaved changes. Would you like to save them first?");
            if (saveBeforeOpen)
            {
                saveList();
            }
        }

        filename = SafeInput.getRegExString(scanner, "Please enter filename to open and make sure to include .txt extension: ", ".+\\.txt");
        File file = new File(filename);
        if (file.exists())
        {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                myArrList.clear();
                String line;
                while ((line = reader.readLine()) != null)
                {
                    myArrList.add(line);
                }
                needsToBeSaved = false;
                System.out.println("List loaded from " + filename);
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
        if (filename == null)
        {
            filename = SafeInput.getRegExString(scanner, "Please enter filename to save the list and make sure to include .txt extension: ", ".+\\.txt");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String item : myArrList)
            {
                writer.write(item);
                writer.newLine();
            }
            needsToBeSaved = false;
            System.out.println("List saved to " + filename);
        }
        catch (IOException e)
        {
            System.out.println("There was an error writing to the file: " + e.getMessage());
        }
    }

    private static void clearList()
    {
        myArrList.clear();
        needsToBeSaved = true;
        System.out.println("List has been cleared.");
    }

    private static void viewList()
    {
        if (myArrList.isEmpty())
        {
            System.out.println("The list is currently empty.");
        }
        else
        {
            for (int i = 0; i < myArrList.size(); i++)
            {
                System.out.println((i + 1) + ": " + myArrList.get(i)); // Display list with one-based indexing
            }
        }
    }

    private static boolean quitProgram()
    {
        if (needsToBeSaved)
        {
            boolean saveBeforeQuit = SafeInput.getYNConfirm(scanner, "You have unsaved changes. Would you like to save before quitting?");
            if (saveBeforeQuit)
            {
                saveList();
            }
        }
        return !SafeInput.getYNConfirm(scanner, "Are you sure you would like to quit?");
    }
}
