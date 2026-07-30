// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, object-literals -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: object literal implements single interface and is used as expression
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Click {
    fun onClick(): String
}

fun case_1(): Click = object : Click {
    override fun onClick(): String = "ok"
}

fun case_1_check() {
    checkSubtype<Click>(case_1())
}
