package src.lucianobandeira;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Anagram {
    
  public static void main(String[] args) {
    
    Anagram anagram = new Anagram();

    List<String> entryLetters = anagram.getUserEntryGroupOfLetters();

    String theAnagramList = anagram.generateAnagrams(entryLetters);
    System.out.println("THE FINAL OUTPUT IS:" + theAnagramList); 

  }
 /****************************************************************************************
    This method is responsible to get the user entry characteres
  *****************************************************************************************/
  public List<String> getUserEntryGroupOfLetters(){

    Scanner ler = new Scanner(System.in);
    String palavraString = ""; 
    List<String> list = null;

    while(list == null || list.isEmpty()){
        System.out.print("Please input a text in the format {x,y,z} with 'n' letters. They must be distinct letters only:");
        palavraString = ler.next();          
        list = validateUserEntry (palavraString);
    }

    ler.close();    
    return list;
  }


  /****************************************************************************************
    This method is responsible for generating the anagrams a given group of distinct letters 
    can produce.  
  *****************************************************************************************/
  public String generateAnagrams(List<String> list){

    List<String> listProcessed = new ArrayList<String>();
    String listFinalToBeDisplaiedString;
    
    int factorial = generateFactorialNumber(list);

    int i = 0;
    String temp;

    while (i < factorial){

       Collections.shuffle(list);
     
       if (!listProcessed.contains(list.toString())){
            temp = list.toString();     
            listProcessed.add(temp);
            i++;
       }
    
    }
    
    listFinalToBeDisplaiedString = removeCommasAndBrackets(listProcessed);
    return listFinalToBeDisplaiedString;
  }
    
  /****************************************************************************************
    This method is responsible do determine the number of anagrams a given group of distinct 
    letters can produce.  
  *****************************************************************************************/
  private int generateFactorialNumber(List <String>list){

        int numberOfCharacters = list.size();
        int factorial = numberOfCharacters;

        while (numberOfCharacters > 1){ 
            factorial = factorial *(numberOfCharacters-1);
            numberOfCharacters--;             
        }	

        return factorial;
    }

    /***********************************************************    
    This method removes all commas and brackets of the final output
    ***********************************************************/
     private String removeCommasAndBrackets (List <String>listProcessed){

        String valor2;
        String listFinalString = "";
        List<String> listFinal = new ArrayList<String>();


        if (listProcessed.size() > 1){    
          for (String valor : listProcessed){
              valor2 = valor.replace(",","")                      
                            .replace(" ", "");  
              listFinal.add(valor2);
          }

          listFinalString = listFinal.toString()
                          .replace(" ", "")
                          .replace("[", "")
                          .replace("]", "");               
        }else if (listProcessed.size() == 1)
          listFinalString = listProcessed.getFirst();

        return listFinalString;
     }

    /***********************************************************
        This method checks if the user provided a list DISTINCT letters
    ***********************************************************/
     private boolean checkForRepeatedCharacteres(List <String> chars){

        long countOriginal = chars.size();
        long countDistincts = chars.stream().distinct().count();

        if (countOriginal > countDistincts) 
            return true;
         else            
            return false;        
     }

     /***********************************************************
        This method validates if the user provided a list of valid letters
        (input list is neither blank or empty, only distinct letters allowed)
      ***********************************************************/
     public List<String> validateUserEntry (String palavraString){

        List<String> list = new ArrayList<>();
        char inputChars[] = new char[palavraString.length()];

        //check if the user input is empty or blank
        if(palavraString.isBlank() || palavraString.isEmpty() || palavraString.equals("{}")){
                list.add("The text entered must not be blank or empty!");
                return list;
            }

        // check if there are numbers or other symbols in the user input
        for (int i = 0; i < palavraString.length() ; i++){
            char caractere = palavraString.charAt(i);
            if (!Character.isLetter(caractere) 
                && caractere != '{' 
                && caractere != '}' && caractere != ','){
                    list.add("The text entered must not have numbers or symbols. It must have distinct letters!");
                    return list;
            }
            inputChars[i] = caractere;
        }

        // create List from user input array
        for(char x : inputChars){ 
            var aux = String.valueOf(x) ;

            if (!aux.equalsIgnoreCase("{") 
                && !aux.equalsIgnoreCase("}") 
                && !aux.equalsIgnoreCase(",") )
                list.add(String.valueOf(x));                         
        }

        // check if there are repeated characters in the user's input. 
        boolean repeatedCharacters = checkForRepeatedCharacteres(list);

        if (repeatedCharacters){
            list = new ArrayList<>();
            list.add("There are repeated characteres!");
            return list;
        }

        return list;
     }



}