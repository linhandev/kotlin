// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, string-interpolation-expressions -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: multiline string interpolation type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val v = 7
    checkSubtype<String>("""line=${v}""")
}
