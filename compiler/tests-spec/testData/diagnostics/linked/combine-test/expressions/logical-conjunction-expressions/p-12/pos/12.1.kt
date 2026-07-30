// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, logical-conjunction-expressions -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: while condition && call infers Boolean; counters remain Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(flag: Boolean) {
    var n = 0
    var body = 0
    fun cond(): Boolean {
        n++
        return false
    }
    while (flag && cond()) {
        body++
    }
    checkSubtype<Boolean>(flag && cond())
    checkSubtype<Int>(n)
    checkSubtype<Int>(body)
}
