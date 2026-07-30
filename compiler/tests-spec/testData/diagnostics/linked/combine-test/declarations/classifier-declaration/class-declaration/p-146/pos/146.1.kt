// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 146 -> sentence 146
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 146 -> sentence 146
 *                inheritance, inheriting -> paragraph 146 -> sentence 146
 * NUMBER: 1
 * DESCRIPTION: subclass without primary constructor parameters still must explicitly delegate via : Parent(...) type inference in class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
open class Parent(val x: Int)

class Child : Parent(1)

fun case1() {
    val c = Child()
    c checkType { check<Child>() }
    checkSubtype<Parent>(c)
    c.x checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
open class Base(val value: Int)

class Fixed : Base(42)

fun case2() {
    val f = Fixed()
    f checkType { check<Fixed>() }
    checkSubtype<Base>(f)
    f.value checkType { check<Int>() }
}

// TESTCASE NUMBER: 3
open class Tagged(val code: Int, val mark: String)

class DefaultTagged : Tagged("hi".length, "d")

fun case3() {
    val t = DefaultTagged()
    t checkType { check<DefaultTagged>() }
    checkSubtype<Tagged>(t)
    t.code checkType { check<Int>() }
    t.mark checkType { check<String>() }
}
