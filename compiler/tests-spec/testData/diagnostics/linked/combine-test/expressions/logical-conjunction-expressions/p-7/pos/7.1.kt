// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, logical-conjunction-expressions -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 7 -> sentence 7
 *                type-system, introduction-1 -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: null check && call infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(s: String?) {
    var n = 0
    fun len(x: String?): Int { n++; return x!!.length }
    checkSubtype<Boolean>((s != null) && len(s) > 0)
}
