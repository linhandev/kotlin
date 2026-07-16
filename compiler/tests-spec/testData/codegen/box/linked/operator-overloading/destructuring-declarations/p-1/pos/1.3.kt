// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: operator-overloading, destructuring-declarations -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: for ((x, _, z) in ...) iteration variable destructuring calls component1 and component3, skips component2
 */

// TESTCASE NUMBER: 1
class Item {
    var component1Calls = 0
    var component2Calls = 0
    var component3Calls = 0

    operator fun component1(): String {
        component1Calls++
        return "O"
    }

    operator fun component2(): String {
        component2Calls++
        return "skip"
    }

    operator fun component3(): String {
        component3Calls++
        return "K"
    }
}

class ItemList(private val items: List<Item>) : Iterable<Item> {
    override fun iterator() = items.iterator()
}

fun box(): String {
    val item = Item()
    var result = ""
    for ((x, _, z) in ItemList(listOf(item))) {
        result = x + z
    }
    if (result != "OK") return "NOK result: $result"
    if (item.component1Calls != 1) return "NOK component1Calls: ${item.component1Calls}"
    if (item.component2Calls != 0) return "NOK component2Calls: ${item.component2Calls}"
    if (item.component3Calls != 1) return "NOK component3Calls: ${item.component3Calls}"
    return "OK"
}
