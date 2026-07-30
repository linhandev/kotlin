// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 87 -> sentence 87
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 87 -> sentence 87
 *                declarations, property-declaration -> paragraph 87 -> sentence 87
 * NUMBER: 1
 * DESCRIPTION: primary constructor argument type must match parameter
 */

// TESTCASE NUMBER: 1
class User(val age: Int)

fun test() = User(<!TYPE_MISMATCH!>"x"<!>)
