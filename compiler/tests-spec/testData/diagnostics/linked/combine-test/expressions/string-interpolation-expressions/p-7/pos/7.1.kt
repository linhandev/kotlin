// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, string-interpolation-expressions -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: property on implicit this receiver interpolation type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class C(private val v: Int) {
    fun case1() {
        checkSubtype<String>("v=$v")
    }
}
