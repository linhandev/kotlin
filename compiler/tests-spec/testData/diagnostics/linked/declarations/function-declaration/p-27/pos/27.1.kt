// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: infix extension function with single parameter may be called in infix form
 */

// TESTCASE NUMBER: 1
infix fun Int.plus(x: Int): Int = this + x

fun useInfixAddition(): Int = 1 plus 2

// TESTCASE NUMBER: 2
class Counter(val value: Int) {
    infix fun combineWith(other: Int): Int = value + other
}

fun useMemberInfix(counter: Counter): Int = counter combineWith 3
