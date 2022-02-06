import java.util.EmptyStackException;
import java.util.Scanner;

// StackTest.java
// Linked list implementation of Stack

class Stack {
    
    int length = 0;
    
    class Node {
        int data;
        Node next;  

        public Node getNextNode() {
            return next;
        }

        public int getData() {
            return data;
        }
    }

    private Node top;
      
    public Stack()
    { 
        top = null;
    }
        
    public void push(int x) {
        Node t = new Node();
        t.data = x;
        t.next = top;
        top = t;
        length++;
    }

    

    // pop() method here
    // only to be called if list is non-empty.
    // Otherwise an exception should be thrown.
    // Utility function to pop a top element from the stack
    public int pop() {
        if(isEmpty()){
            throw new EmptyStackException();
        }
        Node node = top;
        top = top.getNextNode();
        length--;
        return node.getData();
    }

    // check if the stack is empty
    public Boolean isEmpty() {
        return false;
    }


    public void display() {
        Node t = top;     
        System.out.println("\nStack contents are:  ");
        
        while (t != null) {
            System.out.print(t.data + " ");
            t = t.next;
        }        
        System.out.println("\n");
    }

}


public class StackTest
{
    public static void main( String[] arg){
        Stack s = new Stack();
        System.out.println("Stack is created\n");
        Scanner sc = new Scanner(System.in);
        boolean isWrongAnswer;
        isWrongAnswer = true;
        do {
            System.out.println("Please choose: \n1. Add to stack\n2. Remove from stack\n3. Exit program\n\nChoose: ");
            switch (sc.nextInt()) {
                case 1:
                    System.out.println("Enter a number to add to stack: ");
                    Scanner reader2 = new Scanner(System.in); 
                    int stackNo = reader2.nextInt();
                    s.push(stackNo);
                    s.display();
                    break;
                case 2:
                    int i = s.pop();
                    System.out.println("Just popped " + i);
                    s.display();
                    break;
                case 3:
                    isWrongAnswer = false;
                    break;
                default:
                System.out.println("choose from 1 to 3");
                isWrongAnswer = true;
                }
        } while (isWrongAnswer);

    
        /*s.push(10); s.push(3); s.push(11); s.push(7);
        s.display();
        
        int i = s.pop();
        
        System.out.println("Just popped " + i);
        s.display();*/
    }
}


