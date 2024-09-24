
import java.util.Scanner;

public class SortedLinkedList{
    private NodeType head;
    public SortedLinkedList(){
        head = null;
        // end of list
    }
    public int getLength(){
        int c=0;
        NodeType cur=head;
        while (cur != null){
            c++;
            cur=cur.next;
        }
        return c;
        // returns number of nodes in list
    }
    public void insertItem(ItemType item) {
        NodeType ins = new NodeType(item); 
        NodeType cur = head;  
        // insert at start or beginning
        if (head == null || head.info.compareTo(item) > 0) {
            ins.next = head; 
            head = ins; 
        } else {
            // find proper position
            while (cur.next != null && cur.next.info.compareTo(item) < 0) {
                cur = cur.next; 
            }
            // Check for duplicates
            if (cur.next != null && cur.next.info.compareTo(item) == 0) {
                System.out.println("Sorry. You cannot insert the duplicate item: " + item.getValue());
            } else {
                // Insert the new item in its correct position
                ins.next = cur.next;
                cur.next = ins;
            }
        }
    }
    public void deleteItem(ItemType item) {
        NodeType cur = head;
        NodeType pre = null;
        // Check if empty
        if (head == null) {
            System.out.println("The list is empty. Cannot delete.");
        }
        // Check if the item to delete is head
        if (head.info.compareTo(item) == 0) {
            head = head.next; // Move head to  next node
            System.out.println(item + " has been deleted from the list.");
        }
        // Hunt for the item in the list
        while (cur != null && cur.info.compareTo(item) != 0) {
            pre = cur;
            cur = cur.next;
        }
        // Check if the item was found
        if (cur == null) {
            System.out.println(item + " is not found in the list.");
        }
        // Item found,  delete
        pre.next = cur.next; // Link previous node to the next node
        System.out.println(item + " has been deleted from the list.");
    }
    public int searchItem(ItemType item){
        NodeType cur = head;
        int index = 0;
        // check list
        while (cur != null) {
            if (cur.info.compareTo(item) == 0) {
                System.out.println(" The item is present at index " + index);
                return index; // Return the index where the item is found
            }
            cur = cur.next;
            index++;
        }
        // item not in list
        System.out.println("Item is not present in the list");
        return -1;
    }
    public void mergeList(int length, int[] newItems) {
        // 2nd mergelist
        SortedLinkedList tempList = new SortedLinkedList();
        // Insert items into the temporary list
        for (int i = 0; i < length; i++) {
            tempList.insertItem(new ItemType(newItems[i]));
        }
        // Now merge this tempList into the current list
        NodeType cur1 = head; // Current node of the original list
        NodeType cur2 = tempList.head; // Current node of the new list
        SortedLinkedList mergedList = new SortedLinkedList();
        while (cur1 != null && cur2 != null) {
            if (cur1.info.compareTo(cur2.info) < 0) {
                mergedList.insertItem(cur1.info);
                cur1 = cur1.next;
            } else {
                mergedList.insertItem(cur2.info);
                cur2 = cur2.next;
            }
        }
        // Add remaining nodes from both lists
        while (cur1 != null) {
            mergedList.insertItem(cur1.info);
            cur1 = cur1.next;
        }
        while (cur2 != null) {
            mergedList.insertItem(cur2.info);
            cur2 = cur2.next;
        }
        head = mergedList.head; // head now points to mergedlist
    }    
    public void deleteAlternateNodes() {
        if (head == null){
            System.out.println("the list is empty");
        } 
        NodeType cur = head; // Start at the head
        // Go through list, delete alternates
        while (cur != null && cur.next != null) {
            cur.next = cur.next.next; // Remove via skip
            cur = cur.next; // Move to next valid node
        }
        // Print list after deleting alternate nodes
        System.out.println("List after deleting alternate nodes:");
    }
    public void intersection() {
        Scanner scanner = new Scanner(System.in);
        // insert new length list
        System.out.print("Enter the length of the new list: ");
        int length = scanner.nextInt();
        SortedLinkedList newList = new SortedLinkedList();
        // insert new length elements
        System.out.print("Enter the numbers: ");
        for (int i = 0; i < length; i++) {
            int num = scanner.nextInt();
            newList.insertItem(new ItemType(num));  // Used previous insert method to stuff new list
        }
        // Print the user-inputted list
        System.out.print("list 2: ");
        newList.printList();
        // Find the intersection
        NodeType cur1 = head;
        NodeType cur2 = newList.head;
        SortedLinkedList resultList = new SortedLinkedList();
        // find common elements
        while (cur1 != null && cur2 != null) {
            if (cur1.info.compareTo(cur2.info) < 0) {
                cur1 = cur1.next;
            } else if (cur1.info.compareTo(cur2.info) > 0) {
                cur2 = cur2.next;
            } else {
                // add to result if common
                resultList.insertItem(cur1.info);
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
        }
        // Print intersection list
        System.out.print("Intersection of lists: ");
        if (resultList.head == null) {
            System.out.println("No intersection found.");
        } else {
            resultList.printList();
        }
    }    
    public void printList() { //utility to print any lists
        NodeType current = head;  
        if (current == null) {
            System.out.println("The list is empty.");
        }
        while (current != null) {
            System.out.print(current.info.getValue() + " ");
            current = current.next; 
        }
        System.out.println(); 
    }
}