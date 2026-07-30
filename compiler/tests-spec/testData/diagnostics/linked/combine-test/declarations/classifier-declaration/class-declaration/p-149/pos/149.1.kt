// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 149 -> sentence 149
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 149 -> sentence 149
 *                inheritance, classifier-type-inheritance, open-classes -> paragraph 149 -> sentence 149
 * NUMBER: 1
 * DESCRIPTION: open class may appear as a class supertype type inference in class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
open class Parent

class Child : Parent()

fun case1() {
    val c = Child()
    c checkType { check<Child>() }
    checkSubtype<Parent>(c)
}

// TESTCASE NUMBER: 2
open class Named(val label: String)

class NamedChild(label: String) : Named(label)

fun case2() {
    val n = NamedChild("ok")
    n checkType { check<NamedChild>() }
    checkSubtype<Named>(n)
    n.label checkType { check<String>() }
}

// TESTCASE NUMBER: 3
open class Counter(val start: Int) {
    open fun next(): Int = start + 1
}

class Stepped(start: Int) : Counter(start) {
    override fun next(): Int = start + 10
}

fun case3() {
    val s = Stepped(1)
    s checkType { check<Stepped>() }
    checkSubtype<Counter>(s)
    s.start checkType { check<Int>() }
    s.next() checkType { check<Int>() }
}
