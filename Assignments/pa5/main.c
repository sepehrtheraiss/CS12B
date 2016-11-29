#include<stdio.h>
#include"Dictionary.h"
int main()
{
    Dictionary A = newDictionary();
    char* word1[] = {"one","two","three","four","five","six","seven"};
    char* word2[] = {"foo","bar","blah","galumph","happy","sad","blue"};
    int i;

    for(i=0; i<7; i++){
        insert(A, word1[i], word2[i]);
    }

    printDictionary(stdout, A);
    freeDictionary(&A);
    printDictionary(stdout, A);
    return 0;
}
