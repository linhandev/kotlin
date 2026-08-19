// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 37 -> sentence 37
 * PRIMARY LINKS: declarations, declarations-with-type-parameters, reified-type-parameters -> paragraph 37 -> sentence 37
 *                expressions, function-literals, lambda-literals -> paragraph 37 -> sentence 37
 * NUMBER: 1
 * DESCRIPTION: reified T is not accessible inside crossinline lambda body — reports UNRESOLVED_REFERENCE
 */

inline fun <reified T> checkCrossinline(crossinline block: () -> Boolean): Boolean = block()

// TESTCASE NUMBER: 1
fun case_1() {
    val x: Any = 42
    checkCrossinline<Int> { x is <!UNRESOLVED_REFERENCE!>T<!> }
}
