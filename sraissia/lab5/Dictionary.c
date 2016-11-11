// ---------------------------------------------------------------
// Sepehr Raissian
// Sraissia@ucsc.edu
// 12B lab5
// 11/10/16
// Dictionary.c
// adds pair of key and value to the stack
// ----------------------------------------------------------------
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"
// NodeObj
typedef struct NodeObj{
    char* key;
    char* value;
    struct NodeObj* next;
}NodeObj;

// Node
typedef NodeObj* Node;

// newNode()
// constructor of the Node type
Node newNode(char* K,char* V){
    Node N = malloc(sizeof(NodeObj));
    assert(N!=NULL);
    N->key = K;
    N->value = V;
    N->next = NULL;
    return N;
}

// freeNode()
// destructor for the Node type
void freeNode(Node* pN){
        if(pN != NULL && *pN != NULL)
        {
        free(*pN);
        *pN = NULL;
        }
}
// DictionaryObj
typedef struct DictionaryObj{
   Node top;
   int numItems;
}DictionaryObj;
Node find(Dictionary D,char* K)
{
    Node n = D->top;
    int found = 0;
    while(n != NULL && found != 1)
    {
        if(strcmp(n->key,K)==0)
        {
            found = 1;
        }
        else
        {
            n = n->next;
        }
    }
    return n;
}
void freeRecursively(Node n)
{
    if(n != NULL)
    {
        freeRecursively(n->next);
       // printf("free: %s\n",n->key);
        freeNode(&n);
    }
}
void printInOrder(FILE* out,Node n)
{
    if(n != NULL)
    {
    printInOrder(out,n->next);
    fprintf(out,"%s %s\n",n->key,n->value);
    }
}
/* public functions------------------------*/

/* newDictionary()
   constructor for the Dictionary type*/
Dictionary newDictionary(void)
{
    Dictionary D = malloc(sizeof(DictionaryObj));
    assert(D != NULL);
    D->top = NULL;
    D->numItems=0;
    return D;
}
/* freeDictionary()
    destructor for the Dictionary type*/
void freeDictionary(Dictionary * pD)
{
    if( pD !=NULL && *pD !=NULL) // pD the reference of the node *pD the reference the pD points to
    {
        if(!isEmpty(*pD)) makeEmpty(*pD);
        free(*pD);
        *pD = NULL;
    }
}
// isEmpty()
// pre: none
// returns 1 if D is empty, otherwise 0
int isEmpty(Dictionary D)
{
 return (D->numItems==0);   
}
// size()
// pre: none
// returns number of pairs in the dictionary
int size(Dictionary D)
{
    return D->numItems;
}
// lookup()
// pre: none
// returns the value of the given key
char* lookup(Dictionary D,char* K)
{
    Node t = find(D,K);
    if(t != NULL)
    {
        return t->value;
    }
    else
    {
        return NULL;
    }
}
// insert()
// pre: lookup(D,K)==NULL
// inserts new (key,value) pair into D
void insert(Dictionary D,char* K,char* V)
{
    if(lookup(D,K) == NULL) // pair doesn't exist
    {
        if(D == NULL) // D is empty
        {
         fprintf(stderr,"Dictionary Error: calling insert() on NULL Dictionary reference\n");
         exit(EXIT_FAILURE);
          
        }
        else
        {
            Node n = newNode(K,V); // new node
            n->next = D->top;   // new node points to head
            D->top = n;         // head is now pointed to new node
            D->numItems++;
        }
    }
    else
    {
        fprintf(stderr,"Cannot add duplicate key\n");
    }
}
// delete()
// deletes pair with the key K
// pre: looup(D,K) != NULL
void delete(Dictionary D,char* K)
{
    if(lookup(D,K) != NULL) // D and K exist in the dictionary
    {
        Node t = D->top;
        if(strcmp(t->key,K)==0) // if first node is the key
        {
            D->top = t->next;//head points to next node 
            freeNode(&t);
            D->numItems--;
        }// end if
        else  // start the loop
        {
            int found = 0;
            while(t != NULL && found !=1)//exit once node reaches the end or the key has been found
            {
                if(strcmp(t->next->key,K)==0)//if key is found
                {
                    Node d = t->next; // get next value, the node to delete
                    t->next =  t->next->next; // point to the node after the node to be deleted
                    freeNode(&d); 
                    found = 1;
                    D->numItems--;
                }
                else 
                {
//                    assert(t->next == NULL);
                    t = t->next;
                    printf("t: %p\n",t);
                }
            }//end while
        }//end else

    }//end if lookup
}//end delete

// makeEmpty()
// re-sets D to the empty state
// pre: none
void makeEmpty(Dictionary D)
{
    freeRecursively(D->top);
    D->numItems=0;
    D->top = NULL;
}
/* printDictionary()
   prints a text representation of S to the file pointed to by out
   pre: none */
void printDictionary(FILE* out,Dictionary D)
{
    //Node N;
    if(D==NULL)
    {
        fprintf(stderr,"Dictionary Error: calling printDictionary() on NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }
    printInOrder(out,D->top);
/*    for(N=D->top;N!=NULL;N=N->next) fprintf(out,"%s %s ",N->key,N->value);
    fprintf(out,"\n");*/
}

