// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, logical-conjunction-expressions -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 11 -> sentence 11
 *                expressions, conditional-expressions -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: if condition && call infers Boolean; counters remain Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(flag: Boolean) {
    var n = 0
    var body = 0
    fun side(): Boolean {
        n++
        return true
    }
    if (flag && side()) {
        body++
    }
    checkSubtype<Boolean>(flag && side())
    checkSubtype<Int>(n)
    checkSubtype<Int>(body)
}
