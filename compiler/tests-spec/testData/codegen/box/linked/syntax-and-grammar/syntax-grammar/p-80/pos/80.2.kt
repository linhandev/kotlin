// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 80 -> sentence 80
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 81 -> sentence 81
 * NUMBER: 2
 * DESCRIPTION: conjunction newline around and operator
 */
package syntax.grammar.p80.pos2

// TESTCASE NUMBER: 1
fun box(): String {
    val case1 = true
        && true

    val case2 = true &&
        true

    val case3 = true
        &&
        true

    // 完整真值表验证
    val truthTable =
        (true && true) == true &&
        (true && false) == false &&
        (false && true) == false &&
        (false && false) == false

    return if (case1 && case2 && case3 && truthTable) "OK" else "NOK"
}
