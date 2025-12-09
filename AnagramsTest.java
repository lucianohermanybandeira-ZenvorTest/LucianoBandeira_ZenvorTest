package src.test;

import org.junit.jupiter.api.Test; // Para JUnit 5
import src.lucianobandeira.Anagram;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AnagramsTest {

    @Test
    void testAnagramGenerationSuccessful(){

       // prepare
        Anagram anagram = new Anagram();
        String listFinalToBeDisplaiedString;
        List <String> listTest;
        List<String> listFinalToBeCompared;
        List<String> listComparator;
 
        listTest = new ArrayList<>();
        listTest.add("a");
        listTest.add("b");
        listTest.add("c");
        
        listComparator = new ArrayList<>(); 
        listComparator.add("abc"); 
        listComparator.add("cba"); 
        listComparator.add("cab"); 
        listComparator.add("bca"); 
        listComparator.add("acb"); 
        listComparator.add("bac"); 
        Set<String> set1 = new HashSet<>(listComparator);
        
        // execute
        listFinalToBeDisplaiedString = anagram.generateAnagrams(listTest);      
        listFinalToBeCompared = Arrays.stream(listFinalToBeDisplaiedString.split(",")).toList();
                        
        Set<String> set2 = new HashSet<>(listFinalToBeCompared);

        // assert        
        assertTrue(set1.equals(set2));
    }


    @Test
    void testSingleValueEntry(){

         // prepare
        Anagram anagram = new Anagram();
        String listFinalToBeDisplaiedString;
        List <String> listTest;
 
        listTest = new ArrayList<>();
        listTest.add("a");
        
        listFinalToBeDisplaiedString = anagram.generateAnagrams(listTest);      
     
        // assert        
        assertEquals(listFinalToBeDisplaiedString,"[a]");

    }

    @Test
    void  testEmptyEntry(){

         // prepare
        List <String> listFinalToBeDisplaiedString;
        String palavraString = "{}";
        Anagram anagram = new Anagram();
 
        // execute
        listFinalToBeDisplaiedString = anagram.validateUserEntry(palavraString); 

        // assert
        assertEquals(listFinalToBeDisplaiedString.toString(), "[The text entered must not be blank or empty!]");
    }

    @Test
    void testContainsOnlyLetters(){

        // prepare
        List <String> listFinalToBeDisplaiedString;
        String palavraString = "{a,b,*}";
        Anagram anagram = new Anagram();
          
        // execute
        listFinalToBeDisplaiedString = anagram.validateUserEntry(palavraString); 

        // assert
        assertEquals(listFinalToBeDisplaiedString.toString(), "[The text entered must not have numbers or symbols. It must have distinct letters!]");
    }

    @Test
    void testRepeatedLetters(){

        // prepare
        List <String> listFinalToBeDisplaiedString;
        String palavraString = "{a,b,a}";
        Anagram anagram = new Anagram();
          
        // execute
        listFinalToBeDisplaiedString = anagram.validateUserEntry(palavraString); 

        // assert
        assertEquals(listFinalToBeDisplaiedString.toString(), "[There are repeated characteres!]");
        
    }    
}
