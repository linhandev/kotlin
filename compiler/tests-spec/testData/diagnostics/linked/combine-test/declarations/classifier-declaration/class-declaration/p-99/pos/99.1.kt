// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 99 -> sentence 99
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 99 -> sentence 99
 *                declarations, classifier-declaration, classifier-initialization -> paragraph 99 -> sentence 99
 *                inheritance, inheriting -> paragraph 99 -> sentence 99
 * NUMBER: 1
 * DESCRIPTION: subclass secondary constructor delegates to class primary constructor via this() type inference in class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
open class Base(val x: Int)

class Child(val y: Int) : Base(0) {
    constructor(x: Int, y: Int) : this(y)
}

fun case1() {
    val viaSecondary = Child(1, 2)
    viaSecondary checkType { check<Child>() }
    viaSecondary.y checkType { check<Int>() }
    viaSecondary.x checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
fun case2() {
    val viaPrimary = Child(3)
    viaPrimary checkType { check<Child>() }
    viaPrimary.y checkType { check<Int>() }
}

// TESTCASE NUMBER: 3
fun case3() {
    val child = Child(5, 6)
    child checkType { check<Child>() }
    child.y checkType { check<Int>() }
    child.x checkType { check<Int>() }
}

// TESTCASE NUMBER: 4
fun case4() {
    val viaSecondary: Base = Child(7, 8)
    checkSubtype<Base>(viaSecondary)
    viaSecondary.x checkType { check<Int>() }
}
