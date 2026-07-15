// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: kotlinFile runtime: multiple topLevelObject class and function interact
 */
// TESTCASE NUMBER: 1
package tokens.spec.p1

class Holder(val value: Int)

fun box(): String {
    val h = Holder(7)
    return if (h.value == 7) "OK" else "NOK"
}
