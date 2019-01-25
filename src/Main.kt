fun main(args: Array<String>) {
    val corpus: MutableList<String> = mutableListOf()
    corpus.add("Dartford")
    corpus.add("Dartmouth")
    corpus.add("Tower Hill")
    corpus.add("Derby")
    corpus.add("Liverpool")
    corpus.add("Liverpool Line Street")
    corpus.add("Paddington")
    corpus.add("Euston")
    corpus.add("London Bridge")
    corpus.add("Victoria")

    val trie = Trie()
    trie.addCorpus(corpus)
    println(trie.fromPrefix("dart"))
    println(trie.fromPrefix("tower hill"))
    println(trie.fromPrefix("liverpool"))
}