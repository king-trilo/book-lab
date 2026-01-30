import java.io.IOException;
import java.util.Scanner;
import java.net.URL;

public class Book
{
  private String book;

  public Book(String url)
  {
    readBook(url);
    int wordCount = book.split(" ").length;
  }
  public int getWordCount()
  {
    return book.split(" ").length;
  }
  
  private void readBook(String link)
  {
    try {
    URL url = new URL(link);
    Scanner s = new Scanner(url.openStream());

    while(s.hasNext())
    {
      String text = s.nextLine();
      System.out.println(text);
      book += text;
    }
  }

  catch(IOException ex)
  {
    ex.printStackTrace();
  }
  }
  public String getBook()
  {
    return book;
  }
  public String pigLatin(String word)
  {
    String vowels = "aeiouAEIOU";
    String digits = "0123456789";

    if (word.length() == 0)
    {
      return word;
    }
    else if (digits.indexOf(word.substring(0,1)) >= 0)
    {
      return word;
    }
    

    // starts with vowel
    if (vowels.indexOf(word.substring(0,1)) >= 0)
    {
      return word + "yay";
    }

    // move first consonant cluster to the end
    for (int i = 0; i < word.length(); i++)
    {
      if (vowels.indexOf(word.substring(i, i+1)) >= 0)
      {
        String left = word.substring(0, i);
        String right = word.substring(i);
        return right + left + "ay";
      }
    }

    return word + "ay";
  }

  public int endPunctuation(String word)
  {
    for (int i = word.length() - 1; i >= 0; i--)
    {
      char c = word.charAt(i);
      if (Character.isLetterOrDigit(c))
      {
        if (i == word.length() - 1)
          return -1;
        else
          return i + 1;
      }
    }
    return 0;
  }

  public String translateWord(String word)
  {
    if (word.length() == 0)
      return word;

    int puncIndex = endPunctuation(word);
    String punctuation = "";
    String core = word;

    if (puncIndex > 0)
    {
      punctuation = word.substring(puncIndex);
      core = word.substring(0, puncIndex);
    }

    boolean isCapital = Character.isUpperCase(core.charAt(0));

    String pig = pigLatin(core.toLowerCase());

    if (isCapital)
    {
      pig = pig.substring(0,1).toUpperCase() + pig.substring(1);
    }

    return pig + punctuation;
  }

  public String translateSentence(String sentence)
  {
    String[] words = sentence.split(" ");
    StringBuilder ret = new StringBuilder();

    for (int i = 0; i < words.length; i++)
    {
      ret.append(translateWord(words[i]));
      if (i < words.length - 1)
        ret.append(" ");
    }

    return ret.toString();
  }

  public String translateSentenceNoSplit(String sentence)
  {
    StringBuilder currentWord = new StringBuilder();
    StringBuilder result = new StringBuilder();
    boolean firstWord = true;
    
    for (int i = 0; i < sentence.length(); i++)
    {
      char c = sentence.charAt(i);
      
      if (c == ' ')
      {
        if (currentWord.length() > 0)
        {
          if (!firstWord)
          {
            result.append(" ");
          }
          result.append(translateWord(currentWord.toString()));
          currentWord.setLength(0);
          firstWord = false;
        }
      }
      else
      {
        currentWord.append(c);
      }
    }
    
    // Handle the last word if exists
    if (currentWord.length() > 0)
    {
      if (!firstWord)
      {
        result.append(" ");
      }
      result.append(translateWord(currentWord.toString()));
    }

    return result.toString();
  }
}
