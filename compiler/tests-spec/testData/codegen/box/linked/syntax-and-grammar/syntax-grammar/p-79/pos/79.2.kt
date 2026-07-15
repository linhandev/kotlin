// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 79 -> sentence 79
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 80 -> sentence 80
 * NUMBER: 2
 * DESCRIPTION: disjunction newline around or operator
 */
package syntax.grammar.p79.pos2

// TESTCASE NUMBER: 1
fun box(): String {
    val case1 = false
        || true

    val case2 = false ||
        true

    val case3 = false
        ||
        true

    val truthTable =
        (false || false) == false &&
        (false || true) == true &&
        (true || false) == true &&
        (true || true) == true

    return if (case1 && case2 && case3 && truthTable) "OK" else "NOK"
}
