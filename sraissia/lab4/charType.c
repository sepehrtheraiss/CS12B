/* Sepehr Raissian
 * sraissia@ucsc.edu
 * 12B lab4
 * 10/28/16
 * charType.c
 * recieves two command line arguments input file and output file. 
 * prints the how many specicied characters are on the given line 
 * with label numbers for alphabelt,numbers,puncttions and white spcaes.
 */

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<ctype.h>
#define MAX_STRING_LENGTH 100

void extract_chars(char* s,char* a,char* d,char* p, char* w);
int count(char* s);
void deinit(char* s,int count);

int main(int argc,char* argv[])
{
        FILE* in=NULL; /* file handle for input*/
        FILE* out=NULL; /* file handle for output*/
        char* str=NULL; /* char array to store words from input file */
        char* a=NULL;  /* Alphabetic string*/ 
        char* d=NULL;  /* for storing digits*/
        char* p=NULL; /* for storing punctuations*/
        char* w=NULL; /* for storing whitespace*/
        /* check command line for correct number of arguments */
        if(argc!=3)
        {
                printf("Usage: %s <input file> <output file>\n",argv[0]);
                exit(EXIT_FAILURE);
        }

        /* open input file for reading */
        in = fopen(argv[1],"r");
        if(in==NULL)
        {
                printf("Unable to read from file %s\n",argv[1]);
                exit(EXIT_FAILURE);
        }

        /* open output file for writing */
        out = fopen(argv[2],"w");
        if(out==NULL)
        {
                printf("Unable to write to file %s\n",argv[2]);
                exit(EXIT_FAILURE);
        }

        /* read words from input file, print on separate lines to output file */
        int line=0;
        str = calloc(MAX_STRING_LENGTH+1 ,sizeof(char));
        a   = calloc(MAX_STRING_LENGTH+1 ,sizeof(char));
        d   = calloc(MAX_STRING_LENGTH+1 ,sizeof(char));
        p   = calloc(MAX_STRING_LENGTH+1 ,sizeof(char));
        w   = calloc(MAX_STRING_LENGTH+1 ,sizeof(char));
        int aCount,dCount,pCount,wCount;
       // printf("size of: %lu",sizeof(str)); why 8?
        while(fgets(str,MAX_STRING_LENGTH,in) != NULL)
        {
                line++;
                extract_chars(str,a,d,p,w);
                aCount = count(a);
                dCount = count(d);
                pCount = count(p);
                wCount = count(w);

                fprintf(out,"line %i contains:\n",line);
                if(aCount == 1)
                {
                    fprintf(out,"%i alphabetic character: %s\n",aCount,a);
                }
                else
                {
                    fprintf(out,"%i alphabetic characters: %s\n",aCount,a);
                }

                if(dCount == 1)
                {
                    fprintf(out,"%i numeric character: %s\n",dCount,d);
                }
                else
                {
                    fprintf(out,"%i numeric characters: %s\n",dCount,d);
                }

                if(pCount == 1)
                {
                    fprintf(out,"%i punctuation character: %s\n",pCount,p);
                }
                else
                {
                    fprintf(out,"%i punctuation characters: %s\n",pCount,p);
                }

                if(wCount == 1)
                {
                    fprintf(out,"%i whitespace character: %s\n",wCount,w);
                }
                else
                {
                    fprintf(out,"%i whitespace characters: %s\n",wCount,w);
                }
                deinit(a,aCount);// initilized all the variables to '\0'
                deinit(d,dCount);// initilized all the variables to '\0'
                deinit(p,pCount);// initilized all the variables to '\0'
                deinit(w,wCount);// initilized all the variables to '\0'
                 }

        /* close input and output files */
        fclose(in);
        fclose(out);
        free(str); 
        free(a); 
        free(d); 
        free(p); 
        free(w); 
        str=a=d=p=w=NULL;
    return EXIT_SUCCESS;
}
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
      s[i] = '\0';
      i++;
    }

}
