#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
// NodeObj
typedef struct NodeObj{
    string key;
    string value;
    struct NodeObj* next;
}NodeObj;

// Node
typedef NodeObj* Node;

// newNode()
// constructor of the Node type
Node newNode(int x){
    Node N = malloc(sizeof(NodeObj));
    assert(N!=NULL);
    N->item = x;
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
// StackObj
typedef struct StackObj{
   Node top;
   int numItems;
}StackObj;
/* public functions------------------------*/

/* newStack()
   constructor for the Stack type*/
Stack newStack(void)
{
    Stack S = malloc(sizeof(StackObj));
    assert(S != NULL);
    S->top = NULL:
    S->numItems=0;
    return S;
}
/* freeStack()
    destructor for the Stack type*/
void freeStack(Stack * pS)
{
    if( pS !=NULL && *pS !=NULL)
    {
        if(!isEmpty(*pS) popAll(*pS);
        free(*pS);
        *pS = NULL:
    }
}
/* printStack()
   prints a text representation of S to the file pointed to by out
   pre: none */
void printStack(FILE* out,Stack S)
{
    Node N;
    if(S==NULL)
    {
        fprintf(stderr,"Stack Error: calling printStack() on NULL Stack reference\n");
        exit(EXIT_FAILURE);
    }
    for(N=S->top;N!=NULL;N=N->next) fprintf(out,"%d",N->item);
    fprintf(out,"\n");
}
