// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 145 -> sentence 145
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 145 -> sentence 145
 *                inheritance, inheriting -> paragraph 145 -> sentence 145
 * NUMBER: 1
 * DESCRIPTION: subclass primary constructor delegates to superclass via : Parent(...) type inference in class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
open class Parent(val x: Int)

class Child(x: Int) : Parent(x)

fun case1() {
    val c = Child(2)
    c checkType { check<Child>() }
    checkSubtype<Parent>(c)
    c.x checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
open class Base(val value: Int)

class Scaled(x: Int) : Base(x * 2)

fun case2() {
    val s = Scaled(3)
    s checkType { check<Scaled>() }
    checkSubtype<Base>(s)
    s.value checkType { check<Int>() }
}

// TESTCASE NUMBER: 3
open class NamedBase(val id: Int, val label: String)

class NamedChild(n: Int, tag: String) : NamedBase(n, tag)

fun case3() {
    val n = NamedChild(7, "ok")
    n checkType { check<NamedChild>() }
    checkSubtype<NamedBase>(n)
    n.id checkType { check<Int>() }
    n.label checkType { check<String>() }
}
