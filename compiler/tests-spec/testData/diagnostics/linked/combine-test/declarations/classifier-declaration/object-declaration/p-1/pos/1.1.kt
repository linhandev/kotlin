// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, object-declaration -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: object declaration can implement a single interface
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Click {
    fun onClick(): String
}

object Btn : Click {
    override fun onClick(): String = "ok"
}

fun case_1() {
    checkSubtype<Click>(Btn)
    checkSubtype<String>(Btn.onClick())
}
