// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 200 -> sentence 200
 * PRIMARY LINKS: inheritance, overriding -> paragraph 200 -> sentence 200
 *                inheritance, classifier-type-inheritance, open-classes -> paragraph 200 -> sentence 200
 *                inheritance, inheriting -> paragraph 200 -> sentence 200
 * NUMBER: 1
 * DESCRIPTION: JDK non-final class members are overridable from Kotlin; override implementations participate in dynamic dispatch through Java/Kotlin supertype references
 */

// TESTCASE NUMBER: 1
class MyList : java.util.ArrayList<Int>() {
    override fun add(element: Int): Boolean = super.add(element)
}

// TESTCASE NUMBER: 2
class CountingList : java.util.ArrayList<String>() {
    var addCount: Int = 0
        private set

    override fun add(element: String): Boolean {
        addCount++
        return super.add(element)
    }
}

// TESTCASE NUMBER: 3
class PrefixedList : java.util.ArrayList<String>() {
    override fun add(element: String): Boolean = super.add("P:$element")

    fun firstOrEmpty(): String = if (isEmpty()) "" else get(0)
}

fun box(): String {
    val list = MyList()
    if (!list.add(1)) return "NOK: mylist-add"
    if (list.size != 1) return "NOK: mylist-size"
    if (list[0] != 1) return "NOK: mylist-get"
    val asArrayList: MutableList<Int> = list
    if (!asArrayList.add(2)) return "NOK: arraylist-ref-add"
    if (asArrayList.size != 2) return "NOK: arraylist-ref-size"

    val counting = CountingList()
    counting.add("a")
    counting.add("b")
    if (counting.addCount != 2) return "NOK: add-count"
    if (counting.size != 2) return "NOK: counting-size"
    val asMutable: MutableList<String> = counting
    asMutable.add("c")
    if (counting.addCount != 3) return "NOK: mutable-ref-count"

    val prefixed = PrefixedList()
    prefixed.add("x")
    if (prefixed.firstOrEmpty() != "P:x") return "NOK: prefixed"
    val asJavaList: MutableList<String> = prefixed
    asJavaList.add("y")
    if (prefixed.size != 2) return "NOK: list-ref-size"
    if (prefixed.get(1) != "P:y") return "NOK: list-ref-get"
    return "OK"
}
