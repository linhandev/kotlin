// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, object-literals -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: fun interface can be implemented with object literal
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun interface Op {
    fun eval(): Int
}

fun case_1(): Int = object : Op {
    override fun eval(): Int = 1
}.eval()

fun case_1_check() {
    checkSubtype<Int>(case_1())
}
