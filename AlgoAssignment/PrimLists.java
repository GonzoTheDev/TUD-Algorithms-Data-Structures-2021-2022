// Simple weighted graph representation 
// Uses an Adjacency Linked Lists, suitable for sparse graphs

import java.io.*;
import java.util.Stack;
import java.util.Queue;
import java.util.Scanner;
import java.util.LinkedList;

class GraphLists {
    class Node {
        public int vert;
        public int wgt;
        public Node next;
    }
    
    // V = number of vertices
    // E = number of edges
    // adj[] is the adjacency lists array
    private int V, E;
    private Node[] adj;
    private Node z;
    private int[] mst;
    
    // used for traversing graph
    private int[] visited;
    private int id;
    
    
    // default constructor
    public GraphLists(String graphFile)  throws IOException
    {
        System.out.println(); //    for Neatness
        int u, v;
        int e, wgt;
        Node t, n;

        FileReader fr = new FileReader(graphFile);
		BufferedReader reader = new BufferedReader(fr);
	           
        String splits = " +";  // multiple whitespace as delimiter
		String line = reader.readLine();        
        String[] parts = line.split(splits);
        System.out.println("Parts[] = " + parts[0] + " " + parts[1]);
        
        V = Integer.parseInt(parts[0]);
        E = Integer.parseInt(parts[1]);
        
        // create sentinel node
        z = new Node(); 
        z.next = z;
        
        // create adjacency lists, initialised to sentinel node z       
        adj = new Node[V+1];        
        for(v = 1; v <= V; ++v)
            adj[v] = z;               
        
       // read the edges
        System.out.println("Reading edges from text file");
        for(e = 1; e <= E; ++e)
        {
            line = reader.readLine();
            parts = line.split(splits);
            u = Integer.parseInt(parts[0]);
            v = Integer.parseInt(parts[1]); 
            wgt = Integer.parseInt(parts[2]);
            
            System.out.println("Edge " + toChar(u) + "--(" + wgt + ")--" + toChar(v));    
            
            // write code to put edge into adjacency matrix
            t = new Node(); t.vert = v; t.wgt = wgt;
            t.next = adj[u]; adj[u] = t; 
            n = new Node(); n.vert = u; n.wgt = wgt;
             n.next = adj[v]; adj[v] = n;
            
        }	       
    }
   
    // convert vertex into char for pretty printing
    private char toChar(int u)
    {  
        return (char)(u + 64);
    }
    
    // method to display the graph representation
    public void display() {
        int v;
        Node n;
        
        for(v=1; v<=V; ++v){
            System.out.print("\nadj[" + toChar(v) + "] ->" );
            for(n = adj[v]; n != z; n = n.next) 
                System.out.print(" |" + toChar(n.vert) + " | " + n.wgt + "| ->");    
        }
        System.out.println("");
    }


    
	public void MST_Prim(int s)
	{
        System.out.println("");
        int v, u;
        int wgt, wgt_sum = 0;
        int[]  dist, parent, hPos;
        Node t;

        //initialising each array
        dist = new int[V+1];
        parent = new int[V+1];
        hPos = new int[V+1];

        for(int i = 0; i <= V; i++)
        {
            hPos[i] = 0;
            parent[i] = 0;
            dist[i] = Integer.MAX_VALUE;
        }
        
        dist[s] = 0;
        dist[0] = 0;
        parent[s] = s;
        
        Heap pq =  new Heap(V, dist, hPos);
        pq.insert(s);
        
        while ( !(pq.isEmpty()))  
        {
            v = pq.remove();

            dist[v] = -dist[v]; //  -dist to show this vertex has been visited and does not need to be done again

            t = adj[v];

            while(t.next != t) //   Check each vertex with an edge to v
            {
                if(t.wgt < dist[t.vert] && dist[t.vert] > 0) // dist[t.vert] is the distance between the 2 edges. if less than 0 already done!!
                {
                    dist[t.vert] = t.wgt;
                    parent[t.vert] = v;
                    if(hPos[t.vert] == 0) //    if hpos is 0 it is not on the heap so insert, else siftup with updated shorter distance
                    {
                        pq.insert(t.vert);
                    }
                    else
                    {
                        pq.siftUp(hPos[t.vert]);
                    }
                }

                t = t.next; //  increment t
            }

            
        }
        for(int i = 0; i<=V; i++)
        {
            wgt_sum += dist[i];
        }
        wgt_sum *= -1; //   Turn back to positive number to print the weight of MST

        System.out.print("\n\nWeight of MST = " + wgt_sum + "\n"); //   Display full weight of minimum spanning tree

        mst = parent;                      		
	}
    
    public void showMST()
    {
            System.out.print("\n\nMinimum Spanning tree parent array is:\n");
            for(int v = 1; v <= V; ++v)
                if(v == mst[v]) // Draw @ symbol for starter node
                {
                    System.out.println(toChar(v) + " -> @");
                }
                else
                {
                    System.out.println(toChar(v) + " -> " + toChar(mst[v]));
                }
            System.out.println("");
    }

    public void DF( int s) 
    {
        id = 0;
        visited = new int[V+1];
        System.out.println("");

        for(int j = 1; j<=V; j++)
        {
            visited[j] = 0;
        }
        dfVisit(0, s);
    }

    private void dfVisit( int prev, int v)
    {
        Node n = new Node();
        n = adj[v];
        visited[v] = ++id;
        System.out.println("Visiting node [" + toChar(v) + "] from node [" + toChar(prev) + "]");
        while(n.next != n)
        {
            if(visited[n.vert]==0)
            {
                dfVisit(v, n.vert); //recursively call the next vertex
            }
            n = n.next;
        }
    }


    public void BF(int s)
    {
        int id = 0;
        Node n;
        Queue<Integer> q = new LinkedList<Integer>(); //    Queue to get next vertex through FIFO
        //Printing a line space in between df and bf
        System.out.println();
        System.out.println("Breadth First Traversal:");

        //  initialise visited
        for(int i=0; i<=V; i++)
        {
            visited[i] = 0;
        }
        q.add(s);
        while(!(q.isEmpty()))
        {
            int v = q.poll();
            if(visited[v]==0) //    if not visited
            {
                n = adj[v];
                visited[v] = ++id;
                System.out.println("Currently visiting [" + toChar(v) + "]" );
                while(n.next != n) //   While not on last node
                {
                    if(visited[n.vert]==0) //   if vertex not visited add to queue
                    {
                        q.add(n.vert);
                    }
                    n = n.next;
                }
            }
        }
    }

}

public class PrimLists {
    public static void main(String[] args) throws IOException
    {
        Scanner sc = new Scanner(System.in);
        String fname;
        System.out.print("\nInput name of file with graph definition: ");
        fname = sc.nextLine();   
        
        System.out.print("\nInput the number of the vertex you want to start at: ");
        int s = sc.nextInt();

        GraphLists g = new GraphLists(fname);
       
        g.display();
        System.out.println();             
        
        System.out.print("Depth first using recursion:");
        g.DF(s);
        
        g.MST_Prim(s);
        
        g.showMST();
        
        g.BF(s);
        
    }
    
    
}


/*
Heap Code for efficient implementationofPrim's Alg
*/

class Heap
{
    private int[] a;	   // heap array
    private int[] hPos;	   // hPos[h[k]] == k
    private int[] dist;    // dist[v] = priority of v

    private int N;         // heap size
   
    // The heap constructor gets passed from the Graph:
    //    1. maximum heap size
    //    2. reference to the dist[] array
    //    3. reference to the hPos[] array
    public Heap(int maxSize, int[] _dist, int[] _hPos) 
    {
        N = 0;
        a = new int[maxSize + 1];
        dist = _dist;
        hPos = _hPos;
    }


    public boolean isEmpty() 
    {
        return N == 0;
    }


    public void siftUp( int k) 
    {
        //  k = last element in list
        int v = a[k];

        //  code yourself
        //  must use hPos[] and dist[] arrays
    
        while (dist[v] < dist[a[k / 2]]) {
            hPos[a[k]]= k/2; // Hpos holds the indexes of each element in the heap
            a[k] = a[k / 2];
            k = k / 2;
        }
        a[k] = v;
        hPos[v] = k;

    }


    public void siftDown( int k) 
    {
        int v, j;
       
        v = a[k];  
        
        //  code yourself 
        //  must use hPos[] and dist[] arrays

        while(k * 2 < N) // Check for left child Node
        {
            j = k * 2;
            if(j < N && dist[ a[j] ] > dist[ a[j+1] ]) //   Check for right child, if exist compare 2 children
            {
                ++j; // increment if j+1 is smaller to compare smallest child
            }
            if(dist[v] <= dist[ a[j] ])
            {
                break;
            }

            hPos[ a[k] ] = j;
            a[k] = a[j];
            k = j;

        }
        hPos[v] = k;
        a[k] = v;

    }


    public void insert( int x) 
    {
        System.out.println("Inserting: " + x);
        a[++N] = x;
        siftUp( N);
    }


    public int remove() 
    {   
        int v = a[1];
        System.out.println("Removing: " + v);
        hPos[v] = 0; // v is no longer in heap        
        
        a[1] = a[N--];
        siftDown(1);
        
        a[N+1] = 0;  // put null node into empty spot
        
        return v;
    }
}