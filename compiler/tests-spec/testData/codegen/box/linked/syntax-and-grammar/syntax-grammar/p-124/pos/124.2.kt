// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 124 -> sentence 124
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 15 -> sentence 15
 * syntax-and-grammar, syntax-grammar -> paragraph 18 -> sentence 18
 * NUMBER: 2
 * DESCRIPTION: objectLiteral delegation specifiers
 */
package syntax.grammar.p124.pos2

// TESTCASE NUMBER: 1
fun box(): String {
    val o = object : Runnable {
        override fun run() {}
    }
    return if (o is Runnable) "OK" else "NOK"
}
