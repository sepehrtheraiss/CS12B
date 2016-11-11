// ---------------------------------------------------------------
// Sepehr Raissian
// Sraissia@ucsc.edu
// 12B pa3
// 11/4/16
// Dictionary.java
// implements the DictionaryInterface inserts pair of keys and values, keys cannot be duplicate but the values may be
// uses a inner node class to add the new pairs of strings
// looks up the values by the key
// if delete is called on a key not found exception of non existing key will be thrown
// ----------------------------------------------------------------
public class Dictionary implements DictionaryInterface {
	
	private class Node
	{
		// private inner Node class
		String key;
		String value;
		Node next;
		Node(String key,String value)
		{
			this.key = key;
			this.value = value;
			next = null;
			
		}
	}
	// Fields for the Dictionary class
	private Node head;
	private int numItems;
	// Dictionary()
	// Constructor for the Dictionary class
	public Dictionary()
	{
		head = null;
		numItems = 0;
	}
	
	// private helper function ----------------------------
	// find()
	// returns a reference to the Node at position index in this Dictionary
	private Node find(String key)
	{
		Node n = head;
		boolean found = false; // to exit the loop once the key has been found
		while(n != null && !found)
		{
			// while the key is not found go to the next node
			if(n.key == key)
			{
				found = true;
			}
			else
			{
			n = n.next;
			}
		}
		return n;
	}
	//ADT operations ----------------------------
	   // isEmpty()
	   // pre: none
	   // returns true if this Dictionary is empty, false otherwise
	   public boolean isEmpty()
	   {
		   return head == null;
	   }

	   // size()
	   // pre: none
	   // returns the number of entries in this Dictionary
	   public int size()
	   {
		   return numItems;
	   }

	   // lookup()
	   // pre: none
	   // returns value associated key, or null reference if no such key exists
	   public String lookup(String key)
	   {
		   Node temp = find(key); // returns the value of the founded key, otherwise null
		   if(temp != null)
		   {
			   return temp.value;
		   }
		   else
		   {
			   return null;
		   }
	   }

	   // insert()
	   // inserts new (key,value) pair into this Dictionary
	   // pre: lookup(key)==null
	   public void insert(String key, String value) throws DuplicateKeyException
	   {
		   if(lookup(key) == null)
		   {
			   
			   if(head == null) // no pairs exist, list is empty
			   {
				   head = new Node(key,value);  // head points to the first new node
				   ++numItems;
			   }
			   else
			   {
				   Node t = head; // a pointer to head,don't want to move the head
				   while(t.next != null) // to add the pair to the last node
				   {
					   t = t.next;
				   }
				   t.next = new Node(key,value); // since t.next is null its safe to add new node to it
				   ++numItems;
			   }
		   }
		   else
		   {
			   throw new DuplicateKeyException("cannot insert duplicate keys");
		   }
		   
	   }

	   // delete()
	   // deletes pair with the given key
	   // pre: lookup(key)!=null
	   public void delete(String key) throws KeyNotFoundException
	   {
		  if(lookup(key) != null) // if key does not exist throw exception
		   {
			  Node t = head;
			  boolean found = false;
			  while(t != null && !found) 
			  {
				  if(head.key == key && head.next == null) // only one item
				  {
					  head = head.next; // head points to null
					  found = true;
					  --numItems;
				  }
				  else if(t.key == key)//first value is key
				  {
					  head = t.next; // lose the first node which head points to 
					  found = true;
					  --numItems;
				  }
				  else if(t.next.key == key) // the next node 
				  {
					  t.next = t.next.next; // to skip the node that has the key to be deleted
					  found = true;
					  --numItems;
				  }
				  else
				  {
					  t = t.next; // go got next node
				  }
			  }
		   } // end if
		  else
		  {
			  throw new KeyNotFoundException("cannot delete non-existent key");
		  }
	   }

	   // makeEmpty()
	   // pre: none
	   public void makeEmpty()
	   {
		   head = null;
                   numItems=0;
	   }

	   // toString()
	   // returns a String representation of this Dictionary
	   // overrides Object's toString() method
	   // pre: none
	   public String toString()
	   {
		   Node t = head;
		   StringBuffer str = new StringBuffer();
		   // keep on going to the next node until it reaches the end of the list
		   while(t != null)
		   {
			   str.append(t.key + " "+ t.value+"\n");
			   t = t.next;
		   }
		   return str.toString();
	   }
}
