// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, logical-conjunction-expressions -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: false && side-effecting call infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun boom(): Boolean = throw IllegalStateException()

fun case1() {
    checkSubtype<Boolean>(false && boom())
}
