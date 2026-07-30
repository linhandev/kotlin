// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 242 -> sentence 242
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 242 -> sentence 242
 *                inheritance, inheriting -> paragraph 242 -> sentence 242
 * NUMBER: 1
 * DESCRIPTION: precise types when a class implements a subinterface that inherits a parent generic interface with the same type parameter
 * HELPERS: checkType
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

fun case1() {
    val c = IntChild()
    c checkType { check<IntChild>() }
    checkSubtype<Child<Int>>(c)
    checkSubtype<Parent<Int>>(c)
    c.id() checkType { check<Int>() }
    c.wrap() checkType { check<Int>() }
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

fun case2() {
    val d = StringDerived()
    checkSubtype<Derived<String>>(d)
    checkSubtype<Base<String>>(d)
    d.base() checkType { check<String>() }
    d.derived() checkType { check<String>() }
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

fun case3() {
    val l = BoolLeaf()
    checkSubtype<Leaf<Boolean>>(l)
    checkSubtype<Root<Boolean>>(l)
    l.root checkType { check<Boolean>() }
    l.leaf checkType { check<Boolean>() }
}
