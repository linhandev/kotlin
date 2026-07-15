// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 137 -> sentence 137
 * NUMBER: 3
 * DESCRIPTION: jumpExpression break and continue
 */
package syntax.grammar.p137.pos3

// TESTCASE NUMBER: 1
fun box(): String {
    var i = 0
    while (i < 2) {
        i++
        continue
    }
    while (true) {
        break
    }
    if (i != 2) return "NOK"
    return "OK"
}
