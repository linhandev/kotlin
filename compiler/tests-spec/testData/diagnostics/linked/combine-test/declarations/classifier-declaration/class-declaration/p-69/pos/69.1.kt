// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 69 -> sentence 69
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 69 -> sentence 69
 * NUMBER: 1
 * DESCRIPTION: private primary constructor property accessible inside class
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class User(private val secret: Int) { fun get(): Int = secret }

fun test(): Int = User(1).get()

fun case1() {
    checkSubtype<Int>(test())
}
