// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 29 -> sentence 29
 * NUMBER: 5
 * DESCRIPTION: ARROW token used in higher-order function parameter op: (Int) -> Int
 */
// TESTCASE NUMBER: 1

fun applyTwice(n: Int, op: (Int) -> Int): Int = op(op(n))

fun box(): String {
    val result = applyTwice(3) { x -> x + 1 }
    return if (result == 5) "OK" else "NOK"
}
