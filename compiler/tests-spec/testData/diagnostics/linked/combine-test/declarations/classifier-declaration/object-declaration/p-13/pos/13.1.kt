// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, object-declaration -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: fun interface can be implemented by object declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun interface Op {
    fun eval(): Int
}

object Inc : Op {
    override fun eval(): Int = 1
}

fun case_1() {
    checkSubtype<Int>(Inc.eval())
}
