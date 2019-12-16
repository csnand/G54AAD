import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        DCLinkedList list = new DCLinkedList();
        do {
            System.out.println("\nCircular Doubly Linked List Operations\n");
            System.out.println("1. insert at begining");
            System.out.println("3. move right");
            System.out.println("4. move left");
            System.out.println("2. insert at end");
            System.out.println("5. check empty");

            int choice = scan.nextInt();
            switch (choice) {
                case 1 :
                    System.out.println("Enter integer element to insert");
                    list.insertAtStart( scan.nextInt() );
                    break;
                case 2 :
                    System.out.println("Enter integer element to insert");
                    list.insertAtEnd( scan.nextInt() );
                    break;
                case 3: list.moveRight(); break;
                case 4: list.moveLeft();  break;
                case 5 :
                    System.out.println("Empty status = "+ list.isEmpty());
                    break;
                default :
                    System.out.println("Wrong Entry \n ");
                    break;
            }
            list.display();
        } while (true);
    }
}