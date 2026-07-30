// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, logical-conjunction-expressions -> paragraph 20 -> sentence 20
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 19 -> sentence 19
 *                expressions, when-expressions -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: when with && branch infers String
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(x: Int) {
    checkSubtype<String>(when {
        x > 0 && x < 10 -> "ok"
        else -> "no"
    })
}
