// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, logical-disjunction-expressions -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: a().or(b()) infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    var log = ""
    fun a(): Boolean { log += "a"; return true }
    fun b(): Boolean { log += "b"; return false }
    checkSubtype<Boolean>(a().or(b()))
}
