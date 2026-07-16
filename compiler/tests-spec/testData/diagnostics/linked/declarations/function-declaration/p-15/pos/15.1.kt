// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: extension function declaration introduces a receiver parameter before the function name
 */

// TESTCASE NUMBER: 1
fun String.lastChar(): Char = this[length - 1]

// TESTCASE NUMBER: 2
fun <T> List<T>.head(): T? = if (isEmpty()) null else first()

// TESTCASE NUMBER: 3
fun <T> T.identity(): T = this
