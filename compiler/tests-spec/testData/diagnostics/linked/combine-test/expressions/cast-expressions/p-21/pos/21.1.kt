// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, cast-expressions -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 21 -> sentence 21
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: Number as String reports CAST_NEVER_SUCCEEDS
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(x: Number) {
    checkSubtype<Int>((x <!CAST_NEVER_SUCCEEDS!>as<!> String).length)
}
