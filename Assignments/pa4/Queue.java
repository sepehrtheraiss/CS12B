// ---------------------------------------------------------------
// Sepehr Raissian
// Sraissia@ucsc.edu
// 12B pa4
// 11/22/16
// Queue.java
// ----------------------------------------------------------------
public class Queue implements QueueInterface{
                private class Node{
                        Object data;
                        Node next;
                        Node(Object data)
                        {
                                this.data = data;
                                next = null;
                        }
                }
                private Node head;
                private Node tail;
                private int size;
                Queue()
                {
                        head = null;
                        tail = null;
                        size = 0;
                }
           // isEmpty()
           // pre: none
           // post: returns true if this Queue is empty, false otherwise
           public boolean isEmpty(){return head==null; }

           // length()
           // pre: none
           // post: returns the length of this Queue.
           public int length(){return size;}

           // enqueue()
           // adds newItem to back of this Queue
           // pre: none
           // post: !isEmpty()
           // runs in constant time
           public void enqueue(Object newItem)
           {
                   if(head != null)//head !empty
                   {
                           if(tail == null) //tail empty
                           {
                                   tail = new Node(newItem);
                                   head.next = tail;//link head to tail, head.next --> tail
                           }
                           else
                           {
                                   tail.next = new Node(newItem); // adds new node to the end,tail.next --> new Node
                                   tail = tail.next; // now tail points to new node,tail --> new Node
                           }
                   }
                   else
                   {
                           head = new Node(newItem);
                   }
                   size++;
           }

           // dequeue()
           // deletes and returns item from front of this Queue
           // pre: !isEmpty()
           // post: this Queue will have one fewer element
           public Object dequeue() throws QueueEmptyException
           {
                   if(!isEmpty())
                   {
                           Object r = head.data;
                           head = head.next;
                           size--;
                           return r;
                   }
                   else
                   {
                           return null;
                   }
                   
           }

           // peek()
           // pre: !isEmpty()
           // post: returns item at front of Queue
           public Object peek() throws QueueEmptyException
           {
                   if(!isEmpty())
                   {
                           return head.data;
                   }
                   else
                   {
                           return null;
                   }
           }
           public Object getTail() throws QueueEmptyException
           {

                   if(!isEmpty())
                   {
                           return tail.data;
                   }
                   else
                   {
                           return null;
                   }
           }

           // dequeueAll()
           // sets this Queue to the empty state
           // pre: !isEmpty()
           // post: isEmpty()
           public void dequeueAll() throws QueueEmptyException
           {
                   if(!isEmpty())
                   {
                           head = tail =null;
                           size = 0;
                   }
           }

           // toString()
           // overrides Object's toString() method
           public String toString()
           {
                   StringBuffer s = new StringBuffer();
                   Node p = head;
                   while(p!=null)
                   {
                           s.append(p.data+" ");
                           p = p.next;
                   }
                   return s.toString();
           }
}

