// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: declarations, property-declaration -> paragraph 8 -> sentence 8
 *                declarations, function-declaration -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: interface val with custom getter body type inference when inherited by implementing class
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Sized {
    val size: Int get() = 0
}

class Box : Sized

fun case1() {
    val b = Box()
    checkSubtype<Box>(b)
    checkSubtype<Int>(b.size)
    checkSubtype<Sized>(b)
}
