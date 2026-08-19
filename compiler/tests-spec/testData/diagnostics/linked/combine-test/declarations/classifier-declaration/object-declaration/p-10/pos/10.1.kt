// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, object-declaration -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 10 -> sentence 10
 *                declarations, property-declaration -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: abstract interface properties must be implemented by object
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Named {
    val name: String
}

object User : Named {
    override val name: String = "Ann"
}

fun case_1() {
    checkSubtype<String>(User.name)
}
