// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, logical-conjunction-expressions -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: true && Nothing-returning call type-checks as Boolean
 */

// TESTCASE NUMBER: 1
fun boom(): Nothing = throw IllegalStateException()

fun case1() {
    val x: Boolean = true && boom()
}
