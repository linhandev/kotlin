// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: declarations, classifier-declaration, data-class-declaration -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: data class may implement an interface
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Named {
    val name: String
}

data class User(override val name: String) : Named

fun case_1() {
    checkSubtype<String>(User("Ann").name)
}
