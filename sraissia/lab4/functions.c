#include"header.h"
void extract_chars(char* s,char* a,char* d,char* p, char* w)
{
    int aCount,dCount,pCount,wCount;
    aCount=dCount=pCount=wCount=0;
    int index=0;
    while(s[index]!='\0')
    {
        if(isalpha((int)s[index]))
        {
            a[aCount] = s[index];
            aCount++;
        }

        if(isdigit((int)s[index]))
        {
            d[dCount] = s[index];
            dCount++;
        }

        if(ispunct((int)s[index]))
        {
            p[pCount] = s[index];
            pCount++;
        }
        if(isspace((int)s[index]))
        {
            w[wCount] = s[index];
            wCount++;
        }
        index++;
    }//end while
}

int count(char* s)
{
    int i=0;
    while(s[i]!='\0')
    {
//    printf("counted func: %c\n",s[i]);
    i++;
    }
    return i;
}

void deinit(char* s,int count)
{
    int i=0;
    while(i <= count)
    {
    //printf("where do you crash?:%i\n",i);
     s[i] = '\0';
     i++;
    }
}


