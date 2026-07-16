// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: case1 recurses on Expr512.Const512 and Expr512.Add512 branches
 */

sealed interface Expr512 {
    sealed class Const512(val value: Int) : Expr512
    data class Add512(val left: Expr512, val right: Expr512) : Expr512
}

// TESTCASE NUMBER: 1
fun case1(expr: Expr512): Int {
    return when (expr) {
        is Expr512.Const512 -> expr.value
        is Expr512.Add512 -> case1(expr.left) + case1(expr.right)
    }
}
