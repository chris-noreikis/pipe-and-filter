package pipeandfilter;

class WordCount {
    private int wordCount;
    private String word;

    WordCount(String word, Integer wordCount) {
        this.wordCount = wordCount;
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public int getWordCount() {
        return wordCount;
    }
}
