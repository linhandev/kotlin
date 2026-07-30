// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, object-declaration -> paragraph 29 -> sentence 29
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 29 -> sentence 29
 * NUMBER: 1
 * DESCRIPTION: data object can implement an interface
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Flag {
    val on: Boolean
}

data object On : Flag {
    override val on: Boolean = true
}

fun case_1() {
    checkSubtype<Boolean>(On.on)
}
