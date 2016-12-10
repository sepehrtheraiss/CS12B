// ---------------------------------------------------------------
// Sepehr Raissian
// Sraissia@ucsc.edu
// 12B pa5
// 12/2/16
// DictionaryTest.c
// adds pair of key and value to the hashtable with chaining
// ----------------------------------------------------------------
#include<stdio.h>
#include"Dictionary.h"
int main()
{
    Dictionary D = newDictionary();
    char* word1[] = {"one","two","three","four","five","six","seven"};
    char* word2[] = {"foo","bar","blah","galumph","happy","sad","blue"};
    int i;

    for(i=0; i<7; i++){
        insert(D, word1[i], word2[i]);
    }

    printDictionary(stdout, D);
    printf("%s\n",lookup(D,"two"));
    printf("%i\n",isEmpty(D));
    printf("%i\n",size(D));
    delete(D,"two");
    printf("%s\n",lookup(D,"two"));
    printf("%i\n",isEmpty(D));
    printf("%i\n",size(D));
    freeDictionary(&D);
    printDictionary(stdout, D);
    return 0;
}

