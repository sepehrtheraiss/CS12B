// ---------------------------------------------------------------
// Sepehr Raissian
// Sraissia@ucsc.edu
// 12B lab7
// 12/2/16
// DictionaryTest.java
// ----------------------------------------------------------------
public class DictionaryTest
{
    public static void main(String[] args)
    {
        Dictionary d = new Dictionary();
        System.out.println(d.isEmpty());
        System.out.println(d.size());
      
        d.insert("2", "1");
        d.insert("1", "1");
        d.insert("3", "1");
        System.out.println(d);
        System.out.println(d.size());
        d.delete("1");
        d.delete("3");
        System.out.println(d);
        System.out.println(d.size());
        
    }
}
