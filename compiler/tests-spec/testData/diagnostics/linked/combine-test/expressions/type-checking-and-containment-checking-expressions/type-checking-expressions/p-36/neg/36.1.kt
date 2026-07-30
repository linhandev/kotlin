// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 36 -> sentence 36
 * PRIMARY LINKS: declarations, declarations-with-type-parameters, reified-type-parameters -> paragraph 36 -> sentence 36
 *                expressions, function-literals, lambda-literals -> paragraph 36 -> sentence 36
 * NUMBER: 1
 * DESCRIPTION: reified T is not accessible inside lambda argument body — reports UNRESOLVED_REFERENCE
 */

inline fun <reified T> checkInLambda(block: () -> Boolean): Boolean = block()

// TESTCASE NUMBER: 1
fun case_1() {
    val x: Any = 42
    checkInLambda<Int> { x is <!UNRESOLVED_REFERENCE!>T<!> }
}
