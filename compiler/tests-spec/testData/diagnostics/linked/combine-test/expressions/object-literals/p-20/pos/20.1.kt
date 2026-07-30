// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, object-literals -> paragraph 20 -> sentence 20
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: object literal can implement Java functional interface
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(): Int {
    var ran = 0
    val r = object : java.lang.Runnable {
        override fun run() {
            ran = 1
        }
    }
    r.run()
    return ran
}

fun case_1_check() {
    checkSubtype<Int>(case_1())
}
