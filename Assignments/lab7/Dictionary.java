// ---------------------------------------------------------------
// Sepehr Raissian
// Sraissia@ucsc.edu
// 12B lab7
// 12/2/16
// Dictionary.java
// implements the DictionaryInterface inserts pair of keys and values in BST style, keys cannot be duplicate but the values may be
// uses a inner node class to add the new pairs of strings
// looks up the values by the key
// if delete is called on a key not found exception of non existing key will be thrown
// ----------------------------------------------------------------
public class Dictionary implements DictionaryInterface{

    private class Node
    {
        String key;
        String value;
        Node left;
        Node right;
        Node(String k,String v)
        {
            key=k;
            value=v;
            left=right=null;
        }
    }
    private Node root;
    private int numNodes;

    public Dictionary()
    {
        root=null;
        numNodes=0;
    }
 // private helper function ----------------------------
    
 // findKey()
 // returns the Node containing key k in the subtree rooted at R, or returns
 // NULL if no such Node exists
 private Node findKey(Node R, String k){
    if(R==null || R.key == k) 
       return R;
    if( k.compareTo(R.key) < 0) 
       return findKey(R.left, k);
    else // R.key > k
       return findKey(R.right, k);
 }


 // findParent()
 // returns the parent of N in the subtree rooted at R, or returns NULL 
 // if N is equal to R. (pre: R != NULL)
 private Node findParent(Node N, Node R){
    Node P=null;
    if( N!=R ){
       P = R;
       while( P.left!=N && P.right!=N ){
          if(N.key.compareTo(P.key) < 0)
             P = P.left;
          else
             P = P.right;
       }
    }
    return P;
 }

 // findLeftmost()
 // returns the leftmost Node in the subtree rooted at R, or NULL if R is NULL.
 private Node findLeftmost(Node R){
    Node L = R;
    if( L!=null ) for( ; L.left!=null; L=L.left) ;
    return L;
 }
//printInOrder()
//prints the (key, value) pairs belonging to the subtree rooted at R in order
//of increasing keys to file pointed to by out.
private void printInOrder(Node R,StringBuffer s){
 if( R!=null ){
    printInOrder(R.left,s);
    s.append(R.key+" "+R.value+"\n");
    printInOrder(R.right,s);
 }
}

//deleteAll()
//deletes all Nodes in the subtree rooted at N.
private void deleteAll(Node N){
 if( N!=null ){
	 N = null;
 }
}
 	//ADT operations ----------------------------

 	   // isEmpty()
 	   // pre: none
 	   // returns true if this Dictionary is empty, false otherwise
 	   public boolean isEmpty()
 	   {
 		   return numNodes == 0;
 	   }

 	   // size()
 	   // pre: none
 	   // returns the number of entries in this Dictionary
 	   public int size()
 	   {
 		   return numNodes;
 	   }

 	// lookup()
 	// returns the value v such that (k, v) is in D, or returns NULL if no 
 	// such value v exists.
 	// pre: none
 	public String lookup(String k){
 	   Node N;
 	   if( root==null ){
 	       System.out.println("Dictionary Error: calling lookup() on NULL Dictionary reference");
 	   }
 	   N = findKey(root, k);
 	   return ( N==null ? null : N.value);
 	}

 	// insert()
 	// inserts new (key,value) pair into D
 	// pre: lookup(D, k)==NULL
 	public void insert(String k, String v) throws DuplicateKeyException{
 	   Node N, A, B;
 	   if( findKey(root, k)!=null ){
 		   System.out.println("Dictionary Error: cannot insert() duplicate key: "+k);
 	   }
 	   N = new Node(k,v);
 	   B = null;
 	   A = root;
 	   while( A!=null ){
 	      B = A;
 	      if( k.compareTo(A.key) < 0) A = A.left;
 	      else A = A.right;
 	   }
 	   if( B==null ) root = N;
 	   else if( k.compareTo(B.key) < 0) B.left = N;
 	   else B.right = N;
 	   numNodes++;
 	}

 	// delete()
 	// deletes pair with the key k
 	// pre: lookup(D, k)!=NULL
 	
 	public void delete(String k) throws KeyNotFoundException {
 	  Node N, P, S;
 	   if( root==null ){ 
 	         System.out.println("Dictionary Error: calling delete() on empty Dictionary.");
 	   }
 	   N = findKey(root, k);
 	   if( N==null ){
 		   System.out.println("Dictionary Error: cannot delete() non-existent key: "+k);
 	   }
 	   if( N.left==null && N.right==null ){  // case 1 (no children)
 	      if( N==root){
 	         root = null;
 	      }else{
 	         P = findParent(N, root);
 	         if( P.right==N ) P.right = null;
 	         else P.left = null;
 	      }
 	   }else if( N.right==null ){  // case 2 (left but no right child)
 	      if( N==root ){
 	         root = N.left;
 	      }else{
 	         P = findParent(N, root);
 	         if( P.right==N ) P.right = N.left;
 	         else P.left = N.left;
 	      }
 	   }else if( N.left==null ){  // case 2 (right but no left child)
 	      if( N==root ){
 	         root = N.right;
 	      }else{
 	         P = findParent(N, root);
 	         if( P.right==N ) P.right = N.right;
 	         else P.left = N.right;
 	      }
 	   }else{                     // case3: (two children: N->left!=NULL && N->right!=NULL)
 	      S = findLeftmost(N.right);
 	      N.key = S.key;
 	      N.value = S.value;
 	      P = findParent(S, N);
 	      if( P.right==S ) P.right = S.right;
 	      else P.left = S.right;
 	   }
 	   numNodes--;
 	}

 	   // makeEmpty()
 	   // pre: none
 	   public void makeEmpty()
 	   {
 		   root = null;
 		   numNodes=0;
 	   }
	   // toString()
	   // returns a String representation of this Dictionary
	   // overrides Object's toString() method
	   // pre: none
	 	public String toString()
	 	{
	 		StringBuffer s = new StringBuffer();
	 		printInOrder(root,s);
	 		return s.toString();
	 	}

 }
