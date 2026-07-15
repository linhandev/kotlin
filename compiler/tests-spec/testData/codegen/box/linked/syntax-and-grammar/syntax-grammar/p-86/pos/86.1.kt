// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 86 -> sentence 86
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 85 -> sentence 85
 * NUMBER: 1
 * DESCRIPTION: elvis question colon operator
 */
package syntax.grammar.p86.pos1

// TESTCASE NUMBER: 1
fun fallback86(): String = "elvis-86-1"

fun box(): String {
    val nullable: String? = null
    val result = nullable ?: fallback86()
    if (result != "elvis-86-1") return "NOK"
    return "OK"
}
