// ---------------------------------------------------------------
// Sepehr Raissian
// Sraissia@ucsc.edu
// 12B lab5
// 11/10/16
// DictionaryTest.c
// test the functionality of Dictionary.c as implementing it
// ----------------------------------------------------------------
#include<stdio.h>
#include"Dictionary.h"

int main()
{
    //printDictionary(stdout,NULL); exits the program
    Dictionary A = newDictionary();
/*    printDictionary(stdout,A);
    printf("is empty: %i\n",isEmpty(A));
    printf("size: %i\n",size(A));
    printf("not available key: %s\n",lookup(A,"something"));
    insert(A,"bob","22");
    printf("key: %s\n",lookup(A,"bob"));
    insert(A,"bob","22");
    insert(A,"b","2");
    printDictionary(stdout,A);
    printf("is empty: %i\n",isEmpty(A));
    printf("size: %i\n",size(A));
    insert(A,"c","3");
    insert(A,"d","4");
    printDictionary(stdout,A);
    delete(A,"b");
    makeEmpty(A);
    printDictionary(stdout,A);*/
    insert(A,"a","22");
    insert(A,"b","23");
    insert(A,"c","23");
    delete(A,"a");
  //  freeDictionary(&A);
    printDictionary(stdout,A);
    return 0;
}

