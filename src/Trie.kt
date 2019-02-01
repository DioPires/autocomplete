class Trie {
    private var head = Node()

    private fun addWord(word: String) {
        var curNode = head
        var isFinished = true
        val wordNorm = word.toLowerCase()

        for (letter in wordNorm) {
            val id = letter.toLowerCase().toString()
            if (id in curNode.children.keys) curNode = curNode.children[id]!!
            else {
                isFinished = false
                break
            }
        }

        if (!isFinished) {
            var id: String
            for (letter in wordNorm) {
                id = letter.toString()
                val child = Node(id, null)
                curNode.addChild(child, id)
                curNode = child
            }
        }
        curNode.id = wordNorm
        curNode.label = word
        curNode.isKey = true
    }

    fun addCorpus(words: MutableList<String>) {
        for (word in words) {
            addWord(word)
        }
    }

    private fun buildOutput(result: MutableSet<String>, prefix: String): MutableMap<String, MutableSet<String>> {
        val resultOut: MutableMap<String, MutableSet<String>> = mutableMapOf()
        resultOut["matches"] = result
        resultOut["nextChars"] = mutableSetOf()

        for (word in result) {
            val aux = word.split(prefix, ignoreCase = true).last()
            if (aux.isNotEmpty()) resultOut["nextChars"]!!.add(aux[0].toString())
        }
        return resultOut
    }

    fun fromPrefix(prefix: String): MutableMap<String, MutableSet<String>> {
        var result = mutableSetOf<String>()
        var frontier: MutableList<Node>

        var topNode = head
        for (letter in prefix.toLowerCase()) {
            if ("$letter" in topNode.children) topNode = topNode.children["$letter"]!!
            else {
                val resultOut: MutableMap<String, MutableSet<String>> = mutableMapOf()
                resultOut["matches"] = mutableSetOf()
                resultOut["nextChars"] = mutableSetOf()
                return resultOut
            }
        }

        frontier = if (topNode == head) topNode.children.map { it.value }.toMutableList() else mutableListOf(topNode)

        var curNode: Node
        while (frontier.size != 0) {
            curNode = frontier.removeAt(0)
            frontier = (frontier + curNode.children.map { it.value }).toMutableList()
            if (curNode.children.keys.size == 0 || curNode.isKey) result.add(curNode.label!!)
        }

        result = result.sortedBy { it.length}.toMutableSet()

        return buildOutput(result, prefix.toLowerCase())
    }

}