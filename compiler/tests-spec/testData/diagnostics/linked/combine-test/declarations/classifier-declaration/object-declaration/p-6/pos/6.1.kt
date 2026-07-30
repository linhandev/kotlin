// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, object-declaration -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: object declaration is a singleton with a single identity
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Id {
    val name: String
}

object Node : Id {
    override val name: String = "n"
}

fun case_1() {
    checkSubtype<Boolean>(Node === Node)
}
