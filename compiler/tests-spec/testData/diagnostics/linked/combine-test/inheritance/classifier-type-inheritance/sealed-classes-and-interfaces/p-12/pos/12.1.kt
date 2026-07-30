// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 12 -> sentence 12
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: when on sealed interface is exhaustive when all implementations are covered
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
sealed interface Result
data class Ok(val v: Int) : Result
data class Err(val msg: String) : Result

fun case_1(r: Result) {
    checkSubtype<Int>(when (r) {
        is Ok -> r.v
        is Err -> r.msg.length
    })
}
