// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, function-literals, anonymous-function-declarations -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: anonymous function cannot have type parameters
 */

// TESTCASE NUMBER: 1
fun case1() {
    val f = fun<!TYPE_PARAMETERS_NOT_ALLOWED!><T><!>(x: T): T = x
}
