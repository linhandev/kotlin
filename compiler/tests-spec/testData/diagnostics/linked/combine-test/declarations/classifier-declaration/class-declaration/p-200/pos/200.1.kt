// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 200 -> sentence 200
 * PRIMARY LINKS: inheritance, overriding -> paragraph 200 -> sentence 200
 *                inheritance, classifier-type-inheritance, open-classes -> paragraph 200 -> sentence 200
 *                inheritance, inheriting -> paragraph 200 -> sentence 200
 * NUMBER: 1
 * DESCRIPTION: type inference when overriding members of a JDK non-final class from Kotlin in a class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class MyList : java.util.ArrayList<Int>() {
    override fun add(element: Int): Boolean = super.add(element)
}

fun case1() {
    val list = MyList()
    list checkType { check<MyList>() }
    checkSubtype<MutableList<Int>>(list)
    list.add(1) checkType { check<Boolean>() }
}

// TESTCASE NUMBER: 2
class CountingList : java.util.ArrayList<String>() {
    override fun add(element: String): Boolean = super.add(element)
}

fun case2() {
    val list = CountingList()
    list checkType { check<CountingList>() }
    checkSubtype<MutableList<String>>(list)
    list.add("a") checkType { check<Boolean>() }
}

// TESTCASE NUMBER: 3
class PrefixedList : java.util.ArrayList<String>() {
    override fun add(element: String): Boolean = super.add("P:$element")
}

fun case3() {
    val list = PrefixedList()
    list checkType { check<PrefixedList>() }
    checkSubtype<MutableList<String>>(list)
    list.size checkType { check<Int>() }
}
