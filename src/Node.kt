class Node(var id: String? = null, var label: String? = null) {
    var children: MutableMap<String, Node> = mutableMapOf()
    var isKey = false

    fun addChild(child: Node, childId: String) {
        children[childId] = child
    }
}