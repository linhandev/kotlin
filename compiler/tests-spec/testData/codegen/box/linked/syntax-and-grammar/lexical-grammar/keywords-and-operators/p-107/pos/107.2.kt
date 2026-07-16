// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 107 -> sentence 107
 * NUMBER: 2
 * DESCRIPTION: SEALED token in sealed interface hierarchy
 */
// TESTCASE NUMBER: 1
sealed interface Expr107 {
    data class Const107(val value: Int) : Expr107
    data class Neg107(val value: Expr107) : Expr107
}

fun eval107(expr: Expr107): Int = when (expr) {
    is Expr107.Const107 -> expr.value
    is Expr107.Neg107 -> -eval107(expr.value)
}

fun box(): String = if (eval107(Expr107.Const107(42)) == 42) "OK" else "NOK"
