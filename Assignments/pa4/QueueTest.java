// ---------------------------------------------------------------
// Sepehr Raissian
// Sraissia@ucsc.edu
// 12B pa4
// 11/22/16
// QueueTest.java
// ----------------------------------------------------------------
public class QueueTest {

        public static void main(String[] args) {
                Queue q = new Queue();
                System.out.println(q.isEmpty());
                System.out.println(q.length());
                for(int i =0; i < 10;i++)
                {
                        q.enqueue(i);
                }
                System.out.println(q.isEmpty());
                System.out.println(q.length());
                q.enqueue("hey!");
                System.out.println(q.length());
                System.out.println(q.toString());
                q.dequeue();
                System.out.println(q.peek());
                System.out.println(q.length());
                System.out.println(q.toString());
                q.dequeueAll();
                System.out.println(q.toString());
                for(int i =0; i < 10;i++)
                {
                        q.enqueue(i);
                }
                System.out.println(q.toString());
                System.out.println(q.isEmpty());
                System.out.println(q.peek());

        }

}

