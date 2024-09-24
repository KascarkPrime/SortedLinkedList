import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LinkedListDriver {
    public static void main(String[] args) {
        // I/O code
        if (args.length != 1) {
            System.out.println("Usage: java LinkedListDriver <filename>");
        }
        File inputFile = new File(args[0]);
        Scanner fileScanner=null;
        try {
            fileScanner = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            System.out.println("Error: Unable to open file " + args[0]);
        }
        // Create linked list 
        SortedLinkedList list = new SortedLinkedList();
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine().trim();
            String[] numbers = line.split("\\s+"); 
            if (numbers.length > 0) {
                // Insert each num linked list
                for (String num : numbers) {
                    try {
                        int itemValue = Integer.parseInt(num);
                        list.insertItem(new ItemType(itemValue)); // Insert into the linked list
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number format in file: " + num);
                    }
                }
            }
        }
        boolean cont =true;
        // Process commands from the input file and will continue until "q"
        while (cont) {
            System.out.print("enter a command:");
            fileScanner = new Scanner(System.in);
            String command = fileScanner.nextLine();
            if (command.isEmpty()){
                continue; 
            }  // Skip empty lines
            // Handle each command
            switch (command) {
                case "i" : // insert 
                    System.out.print("Original List: ");
                    list.printList();
                    System.out.print("Enter a number to insert: ");
                    int numberToInsert = fileScanner.nextInt();
                    list.insertItem(new ItemType(numberToInsert));
                    System.out.print("new List: ");
                    list.printList();
                    break;
                case "l": // number of nodes in list
                    System.out.println("List length: " + list.getLength());
                    break;
                case "d": // delete
                    System.out.print("Original List: ");
                    list.printList();
                    System.out.print("Enter a number to delete: ");
                    int numberToDelete = fileScanner.nextInt();
                    list.deleteItem(new ItemType(numberToDelete));
                    System.out.print("new List: ");
                    list.printList();
                    break;
                case "s": // search
                    System.out.print("Enter a number to search: ");
                    int numberToSearch = fileScanner.nextInt();
                    list.searchItem(new ItemType(numberToSearch));
                    break;
                case "a": // delete alt
                    System.out.print("Original List: ");
                    list.printList();
                    list.deleteAlternateNodes();
                    list.printList();
                    break;
                case "m": // merge
                    System.out.print("Enter the length of the new list: ");
                    int length = fileScanner.nextInt();
                    int[] newItems = new int[length];
            
                    System.out.print("Enter the numbers: ");
                    for (int i = 0; i < length; i++) {
                        newItems[i] = fileScanner.nextInt();
                    }
                    list.mergeList(length, newItems);
                    System.out.println("Merged list: ");
                    list.printList();
                    break;
                case "t": // intersection
                    System.out.println("list 1:");
                    list.printList();
                    list.intersection();
                case "p": // print all
                    list.printList();
                    break;
                default: // Invalid command
                    System.out.println("Invalid command, try again!");
                    break;
                case "q": // quit 
                    System.out.println("Exiting the program.");
                    cont=false;
                    break;
                }
           } 
        }  
    }
