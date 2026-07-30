// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, object-literals -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: object literal can be assigned to interface typed variable
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Svc {
    fun run(): Int
}

fun case_1(): Int {
    val s: Svc = object : Svc {
        override fun run(): Int = 5
    }
    return s.run()
}

fun case_1_check() {
    checkSubtype<Int>(case_1())
}
