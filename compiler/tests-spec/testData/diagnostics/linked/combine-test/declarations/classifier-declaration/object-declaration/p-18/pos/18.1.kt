// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, object-declaration -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: object can implement a Java functional interface
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
object Runner : java.lang.Runnable {
    override fun run() {}
}

fun case_1() {
    checkSubtype<java.lang.Runnable>(Runner)
    Runner.run()
}
