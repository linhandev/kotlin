// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 61 -> sentence 61
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 61 -> sentence 61
 * NUMBER: 1
 * DESCRIPTION: primary constructor val parameter declares read-only property
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class User(val name: String)

fun test(): String = User("Ann").name

fun case1() {
    checkSubtype<String>(test())
}
