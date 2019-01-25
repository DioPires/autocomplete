class Node(var id: String?, var label: String?) {
    var children: MutableMap<String, Node> = mutableMapOf()
    var isKey = false

    fun addChild(childId: String) {
        children[childId] = Node(childId, null)
    }
}