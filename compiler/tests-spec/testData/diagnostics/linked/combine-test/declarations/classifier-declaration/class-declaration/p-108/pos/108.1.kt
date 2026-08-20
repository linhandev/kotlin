// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 108 -> sentence 108
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 108 -> sentence 108
 *                declarations, classifier-declaration, classifier-initialization -> paragraph 108 -> sentence 108
 *                declarations, declarations-with-type-parameters -> paragraph 108 -> sentence 108
 * NUMBER: 1
 * DESCRIPTION: generic class secondary constructor delegates to primary constructor type inference in class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box<T>(val v: T) {
    constructor(x: T, marker: Boolean) : this(x)
}

fun case1() {
    val intSecondary = Box(1, true)
    intSecondary checkType { check<Box<Int>>() }
    intSecondary.v checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
fun case2() {
    val stringSecondary = Box("hi", false)
    stringSecondary checkType { check<Box<String>>() }
    stringSecondary.v checkType { check<String>() }
}

// TESTCASE NUMBER: 3
fun case3() {
    val doublePrimary = Box(3.5)
    doublePrimary checkType { check<Box<Double>>() }
    doublePrimary.v checkType { check<Double>() }
}
