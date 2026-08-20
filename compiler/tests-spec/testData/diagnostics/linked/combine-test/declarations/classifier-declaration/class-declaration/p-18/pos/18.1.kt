// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: member function uses enclosing class type parameter
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box<T>(val v: T) { fun id(): T = v }

fun test(): Int = Box(2).id()

fun case1() {
    checkSubtype<Int>(test())
}
