// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 49 -> sentence 49
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 49 -> sentence 49
 * NUMBER: 1
 * DESCRIPTION: Any upper bound accepts object type arguments
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box<T : Any>(val v: T)

fun case1() {
    checkSubtype<Int>(Box(1).v)
}
