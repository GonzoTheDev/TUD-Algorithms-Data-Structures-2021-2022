// Exercise to separate ADT Queue from its implementation
// and to provide 2 implementations. Also exception handling.

class QueueException extends Exception {
    public QueueException(String msg) {
        super(msg);
    }
}    

// In Java an interface can often be the best way to 
// describe an Abstract Data Type (ADT) such as Queue or Stack
interface Queue {
    public void enQueue(int x) throws QueueException;
    public int deQueue() throws QueueException;
    public boolean isEmpty();   
    public void display();  
}


class QueueLL implements Queue {

    private class Node {
        int data;
        Node next;
    }

    Node z, head, tail;

    public void enQueue(int x) throws QueueException {
        Node t;

        t = new Node();
        t.data = x;
        t.next = z;

        if(head == z)       // case of empty list
            head = t;
        else                // case of list not empty
            tail.next = t;
            
        tail = t;           // new node is now at the tail
    }

 
    // assume the queue is non-empty when this method is called, otherwise thro exception
    public int deQueue() throws QueueException {
        int deleting = head.data;
        head = head.next;
        
        return deleting;
    }

    public boolean isEmpty() {
        return head == head.next;
    }
 
    public void display() {
        System.out.println("\nThe queue values are:\n");

        Node t = head;
        while( t != null) {
            System.out.print( t.data + "  ");
            t = t.next;
        }
        System.out.println("\n");
    } 

} // end of QueueLL class



class QueueCB implements Queue {
    private int q[], back, front;
    private int qmax, size;

 
    public QueueCB() {
        qmax = 4;
        size = front = back = 0;
        q = new int[qmax];
    }

    public void enQueue( int x) throws QueueException  {
        size = x + 1;
        if(size > qmax){
            throw new QueueException("Queue is full!");
          } else {
            if (front == -1)
                front = 0;
            back = (back + 1) % size;
            q[back] = x;
            System.out.println("Inserted " + x);
        }
    }
  
    public int deQueue()  throws QueueException 
    {
        if(isEmpty())
            throw new QueueException("Queue is empty!");
        int temp = q[front];
        if(front == back)
            front = back = -1;
        else
            front = (front+1) % q.length;
        return temp;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void display() {
        int i;
        if (isEmpty()) {
            System.out.println("Empty Queue");
        } else {
            System.out.println("Front -> " + front);
            System.out.println("Items -> ");
            for (i = front; i != back; i = (i + 1) % qmax)
                System.out.print(q[i] + " ");
            System.out.println(q[i]);
            System.out.println("Rear -> " + back);
        }
    }
}


// here we test both implementations
class QueueTest2 {
    public static void main(String[] arg) {
        Queue q1, q2;
        q1 = new QueueLL();
        q2 = new QueueCB();
        
        for(int i = 1; i<9; ++i)
        try { 
            q1.enQueue(i);
            System.out.println("LL Enqueue Success: " + i);                
        } catch (QueueException e) {
            System.out.println("Exception thrown: " + e); 
        }
        
         
        q1.display();

        // more test code

        for(int i = 1; i<4; ++i)
        try { 
            q2.enQueue(i);         
        } catch (QueueException e) {
            System.out.println("Exception thrown: " + e); 
        }

        q2.display();
    }   
}

