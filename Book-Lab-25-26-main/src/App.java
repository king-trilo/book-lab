class App {
  public static void main(String[] args) {
    Book aBook = new Book("https://www.gutenberg.org/cache/epub/1513/pg1513.txt");
    

    String transBook = aBook.getBook();
    
    System.out.println(aBook.translateSentenceNoSplit(transBook));
    System.out.println("Total number of words: " + aBook.getWordCount());
  }
}
