// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 65 -> sentence 65
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 65 -> sentence 65
 * NUMBER: 1
 * DESCRIPTION: primary constructor parameter usable in init block
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class User(name: String) { val upper: String; init { upper = name.uppercase() } }

fun test(): String = User("a").upper

fun case1() {
    checkSubtype<String>(test())
}
