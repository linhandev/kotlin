// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 175 -> sentence 175
 * NUMBER: 3
 * DESCRIPTION: identifier NL between dot-separated simpleIdentifier parts
 */
package syntax.grammar.p175.pos3

import kotlin
    .math.max

// TESTCASE NUMBER: 1
fun box(): String = if (max(1, 5) == 5) "OK" else "NOK"
