#ifndef _INTEGER_STACK_H_INCLUDE_
#define _INTEGER_STACK_H_INCLUDE_
//stack
//Exported reference type
typedef struct StackObj* Stack;

// newStack()
// contructor for the stack type
Stack newTack(void);

// freeStack()
// destrcutor for the stack type
void freeStack(Stack* pS);

//------------------------------------------
// prototypes of ADT operations
//------------------------------------------
//
// isEmpty()
// pre: none
// returns true if this Dictionary is empty, false otherwise
bool isEmpty();

// size()
// pre: none
// returns the number of entries in this Dictionary
int size();

// lookup()
// pre: none
// returns value associated key, or null refence if no such key exists
string lookup(string key);

// insert()
// pre: lookup(key) == null
// inserts new (key,value) pair to this Dictionary
void insert(string key,string value);

// delete()
// pre: lookup(key) != null
// deletes pair with the given key
void deleteK(string key);

// makeEmpty()
// pre: none
// empties the dictionary
void makeEmpty();

// printStack()
// pre: none
// prints a text representation of s to the file pointed to by out
void printStack(FILE* out,Stack s);
#endif // INTEGER_STACK
