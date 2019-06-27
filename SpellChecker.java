/* COMS 3134 Homework 4
 * @ Yiyang Zhao (yz3504)
 * */


import java.io.*;
import java.util.*;

public class SpellChecker implements SpellCheckerInterface{
	
	private HashSet<String> vocabList;
		
	public SpellChecker(){
		System.out.println("Welcome to the Spell Checker!");
        Scanner input = new Scanner(System.in);
        System.out.println("Please choose the dictionary file.");
        String s = input.nextLine();
		while (s.length() == 0){
			System.out.println("Please input a file name.");
			s = input.nextLine();
		}
		try{
			vocabList = makeList(s);
		} catch (IOException io){
			System.out.println("Invalid file.");
		}
	}
	
	
	public List<String> getIncorrectWords(String filename){
		List<String> text = new ArrayList<String>();
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
			String currentLine;
			while ((currentLine = bufferedReader.readLine()) != null){
				String[] s = currentLine.split("\\s+");
				for (int i = 0; i < s.length; i++){
					String currentWord = s[i];
					currentWord = currentWord.replaceAll("\\p{Punct}", ""); 
					currentWord = currentWord.toLowerCase();
					if (currentWord.length()>0)
						text.add(currentWord);
				}
			}
		} catch (IOException io){
			System.out.println("Where is your file?");
		} 
			List<String> wrong = new ArrayList<String>();
		try{
			for (String word: text){
				if (!vocabList.contains(word)){
					wrong.add(word);
				}
			}
		} catch (NullPointerException npe){
			System.out.println("Where is your file?");
		}
		return wrong;	
	}
	
	
	
	public Set<String> getSuggestions(String word){
		HashSet<String> suggest = new HashSet<String>();
		HashSet<String> added = checkAdd(word);
		HashSet<String> removed = checkRem(word);
		HashSet<String> swapped = checkSwap(word);
		suggest.addAll(added);
		suggest.addAll(removed);
		suggest.addAll(swapped);
		return suggest;
	}
	
	
	private HashSet<String> checkAdd(String word){
		int l = word.length();
		HashSet<String> adding = new HashSet<String>();
		char[] letter = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		for (int i = 0; i <= l; i++){
			for (int j = 0; j <= 25; j++){
				String current = word;
				current = current.substring(0, i)+letter[j]+current.substring(i);
				if (vocabList.contains(current) && current.length()>1){
					adding.add(current);
//					System.out.println("1 Add "+current);
				}
			}
		}
		return adding;
	}
	
	private HashSet<String> checkRem(String word){
		int l = word.length();
		HashSet<String> removing = new HashSet<String>();
		for (int i = 0; i < l; i++){
			String current = word;
			current = current.substring(0, i)+current.substring(i+1,l);
			if (vocabList.contains(current)){
				removing.add(current);
//				System.out.println("2 Remove "+current);
			}
		}
		return removing;
	}
	
	private HashSet<String> checkSwap(String word){
		int l = word.length();
		HashSet<String> swaping = new HashSet<String>();
		for (int i = 0; i < l-1; i++){
			char[] letters = word.toCharArray();
			// swap adjacent letters
			char temp = letters[i];
			letters[i] = letters[i+1];
			letters[i+1] = temp;
			String current = new String(letters);
			
			if (vocabList.contains(current)){
				swaping.add(current);
//				System.out.println("3 Swap "+current);
			}
		}
		return swaping;	
	}
	
	
	
	private HashSet<String> makeList(String file) throws IOException { 
        HashSet<String> myHash = new HashSet<String>(); 
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		try {
			String currentWord;
			while ((currentWord = bufferedReader.readLine()) != null){
				myHash.add(currentWord.toLowerCase());
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Something is wrong.");
		} catch (FileNotFoundException fnfe) {
			System.out.println("File not found.");
		}
        return myHash;
	}	
}