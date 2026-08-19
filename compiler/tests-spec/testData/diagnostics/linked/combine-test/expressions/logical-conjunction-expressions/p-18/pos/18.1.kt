// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, logical-conjunction-expressions -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: takeIf result infers Int?
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    var n = 0
    checkSubtype<Int?>(1.takeIf { n++; false })
}
