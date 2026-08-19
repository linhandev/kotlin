// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, logical-disjunction-expressions -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: true || side-effecting call infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun boom(): Boolean = throw IllegalStateException()

fun case1() {
    checkSubtype<Boolean>(true || boom())
}
