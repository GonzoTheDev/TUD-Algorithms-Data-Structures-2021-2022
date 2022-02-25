// Sorted linked list with a sentinel node
// Skeleton code
import java.util.Scanner;

class SortedLL
{
    // internal data structure and
    // constructor code to be added here
    
    
    
    // this is the crucial method
    public void insert(int x)
    {
        Node prev, curr, t;
          
             
    }    
    
    /*
    public boolean remove(int x) {
        Node prev, curr;
        
            }
    
    public boolean isEmpty() {
        
    }
    */
    
    public void display()
    {
        Node t = head;
        System.out.print("\nHead -> ");
        while( t != z) {
            System.out.print(t.data + " -> ");
            t = t.next;
        }
        System.out.println("Z\n");
    }
    
    public static void main(String args[])   
    {
        SortedLL list = new SortedLL();
        //list.display();
        
        double x;
        int i, r;
        
        /*
           for(i = 1; i <= 5; ++i)  {
           x =  (Math.random()*100.0);
           r = (int) x; 
           System.out.println("Inserting " + r);
           list.insert(r);
           list.display();            
        }
        */
        
        /*
        while(!list.isEmpty())  {
            System.out.println("\nInput a value to remove: ");
            Scanner in = new Scanner(System.in);
            r = in.nextInt();
            if(list.remove(r)) {
                System.out.println("\nSuccessfully removed: " + r);
            list.display(); }
            else System.out.println("\nValue not in list");                        
        }
        */
    }
}