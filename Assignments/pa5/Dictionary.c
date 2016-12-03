// ---------------------------------------------------------------
// Sepehr Raissian
// Sraissia@ucsc.edu
// 12B pa5
// 12/2/16
// Dictionary.c
// adds pair of key and value to the hashtable with chaining
// ----------------------------------------------------------------
#include<stdio.h>
#include<stdlib.h>
#include<assert.h>
#include"Dictionary.h"
const int tableSize=101; 
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
   Node* hashTable;
   int numItems;
}DictionaryObj;
void freeRecursively(Node n)
{
    if(n != NULL)
    {
        freeRecursively(n->next);
        freeNode(&n);
    }
}

/*
---------HASH FUNCTIONS---------
---------PRIVATE---------------
*/
// rotate_left()
// rotate the bits in an unsigned int
unsigned int rotate_left(unsigned int value, int shift) {
    int sizeInBits = 8*sizeof(unsigned int);
    shift = shift & (sizeInBits - 1);  // remainder on division by sizeInBits
    if ( shift == 0 )
        return value;
    return (value << shift) | (value >> (sizeInBits - shift));
}

// pre_hash()
// turn a string into an unsigned int
unsigned int pre_hash(char* input) {  // input points to first char in string
    unsigned int result = 0xBAE86554;  // = 10111010111010000110010101010100
    while (*input) {                   // while *input is not '\0' (not end of string)
        result ^= *input++;                // result = result ^ *input (current char alters result)
        // input++  (go to next char)
        result = rotate_left(result, 5);   // rotate result by fixed amount
    }
    return result;  // result is now a random looking bit pattern depending on input string
}

// hash()
// turns a string into an int in the range 0 to tableSize-1
int hash(char* key){
    return pre_hash(key)%tableSize;
}

/* public functions------------------------*/

/* newDictionary()
   constructor for the Dictionary type*/
Dictionary newDictionary(void)
{
    Dictionary D = malloc(sizeof(DictionaryObj));
    assert(D != NULL);
    D->hashTable=malloc(tableSize*sizeof(Node));
    D->numItems=0;
    //D->hashTable = malloc(tableSize*sizeof(Node));
    for(int i=0;i<tableSize;i++)
    {
        D->hashTable[i] = NULL;
    }
    return D;
}
/* freeDictionary()
    destructor for the Dictionary type*/
void freeDictionary(Dictionary *pD)
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
// returns the value v such that (k, v) is in D, or returns NULL if no
// such value v exists.
// pre: none
char* lookup(Dictionary D, char* k)
{
    int key = hash(k);
    Node ptr = D->hashTable[key];
    if(ptr != NULL)//if not empty
    {
        // look for the key
        while(ptr!=NULL) { // dealing with chaining
            if(ptr->key == k) {
                return ptr->value;
            }
            else
            {
                ptr = ptr->next;
            }
        }
    }
    return NULL;
}
// insert()
// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL
void insert(Dictionary D, char* k, char* v)
{
    if(D==NULL)
    {
        fprintf(stderr,"Dictionary Error: calling insert() on NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }
        // do hashing
    else
    {
        int key = hash(k);
        if(lookup(D,k) == NULL) {
            if(D->hashTable[key] == NULL) { //D->hashTable[key] is empty needs a node to point to
                D->hashTable[key] = newNode(k, v);
            }
            else// there are other nodes collision has occurred
            {
                Node ptr = D->hashTable[key];
                while(ptr->next != NULL) { // go to the last node
                    ptr = ptr->next;
                }
                ptr->next = newNode(k, v);
            }
            D->numItems++;
        }
        else
        {
            fprintf(stderr,"Cannot add duplicated key\n");
        }

    }
}
void postOrder(FILE* out,Node ptr)
{
    if(ptr!=NULL)
    {
        postOrder(out,ptr->next);
        fprintf(out,"%s %s\n",ptr->key,ptr->value);
    }
}
// printDictionary()
// pre: none
// prints a text representation of D to the file pointed to by out
void printDictionary(FILE* out, Dictionary D)
{
    if(D!=NULL) {
        for (int i = 0; i < tableSize; i++) {
            if (D->hashTable[i] != NULL) {
                Node ptr = D->hashTable[i];
                postOrder(out,ptr);//it will print if there is a list
             /*   while (ptr != NULL) {
                    fprintf(out, "%s %s\n", ptr->key, ptr->value);
                    ptr = ptr->next;
                }*/
            }
        }
    }
}

// delete()
// deletes pair with the key k
// pre: lookup(D, k)!=NULL
void delete(Dictionary D, char* k)
{
    if(lookup(D,k)!= NULL)
    {
        int key = hash(k);//index
        Node ptr = D->hashTable[key];   // head of the list
        if(ptr->key == k) // first key (main)
        {
            if(ptr->next != NULL) // key contains a linked list
            {
                // don't want the first element to be NULL
                D->hashTable[key] = D->hashTable[key]->next;
                freeNode(&ptr);
            }
            //freeNode(&ptr); why does D->hashTable[key] still point the same node?
            else
            {
                freeNode(&D->hashTable[key]);
            }
        }
        else// other list contains the key
        {
            while(ptr->next->key != k) // go on until the next node is the key
            {
                ptr = ptr->next;
            }
            Node dPtr = ptr->next;
            ptr->next = ptr->next->next; // skip the node to the be deleted and link them
            freeNode(&dPtr);
        }
        D->numItems--;
    }
    else
    {
        fprintf(stderr,"key does not exist\n");
    }
}
void remove_recursively(Node n)
{
    if(n!=NULL) {
        remove_recursively(n->next);
        freeNode(&n);
    }
}
// makeEmpty()
// re-sets D to the empty state.
// pre: none
void makeEmpty(Dictionary D)
{
    for(int i=0;i<tableSize;i++) {
        if(D->hashTable[i] != NULL && D->hashTable[i]->next != NULL)//contains a list if [i] is already null can't do null->next
        {
            remove_recursively(D->hashTable[i]);
            D->hashTable[i] = NULL;
        } else{
            freeNode(&D->hashTable[i]);
        }
    }
    D->numItems=0;
    free(D->hashTable);
    D->hashTable = NULL;
}
