// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, object-declaration -> paragraph 23 -> sentence 23
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 23 -> sentence 23
 *                declarations, property-declaration -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: object implementing interface properties initializes them in the declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface S {
    val v: Int
}

object O : S {
    override val v: Int = 1
}

fun case_1() {
    checkSubtype<Int>(O.v)
}
