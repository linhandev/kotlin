// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 242 -> sentence 242
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 242 -> sentence 242
 *                inheritance, inheriting -> paragraph 242 -> sentence 242
 * NUMBER: 1
 * DESCRIPTION: a subinterface may redeclare the same type parameter while inheriting a parent generic interface, and a class implementing the child exposes both parent and child members; contrasts with p-231 fixed single-interface producers and next-point out-variance narrowing
 */

// TESTCASE NUMBER: 1
interface Parent<T> {
    fun id(): T
}

interface Child<T> : Parent<T> {
    fun wrap(): T
}

class IntChild : Child<Int> {
    override fun id(): Int = 1
    override fun wrap(): Int = 2
}

// TESTCASE NUMBER: 2
interface Base<T> {
    fun base(): T
}

interface Derived<T> : Base<T> {
    fun derived(): T
}

class StringDerived : Derived<String> {
    override fun base(): String = "b"
    override fun derived(): String = "d"
}

// TESTCASE NUMBER: 3
interface Root<T> {
    val root: T
}

interface Leaf<T> : Root<T> {
    val leaf: T
}

class BoolLeaf : Leaf<Boolean> {
    override val root: Boolean = true
    override val leaf: Boolean = false
}

fun box(): String {
    if (IntChild().id() != 1) return "NOK: int-id"
    if (IntChild().wrap() != 2) return "NOK: int-wrap"
    val asChild: Child<Int> = IntChild()
    if (asChild.id() != 1 || asChild.wrap() != 2) return "NOK: via-child"
    val asParent: Parent<Int> = IntChild()
    if (asParent.id() != 1) return "NOK: via-parent"

    if (StringDerived().base() != "b") return "NOK: string-base"
    if (StringDerived().derived() != "d") return "NOK: string-derived"
    val asDerived: Derived<String> = StringDerived()
    if (asDerived.base() != "b" || asDerived.derived() != "d") return "NOK: via-derived"

    if (!BoolLeaf().root || BoolLeaf().leaf) return "NOK: bool-props"
    val asLeaf: Leaf<Boolean> = BoolLeaf()
    if (!asLeaf.root || asLeaf.leaf) return "NOK: via-leaf"
    return "OK"
}
