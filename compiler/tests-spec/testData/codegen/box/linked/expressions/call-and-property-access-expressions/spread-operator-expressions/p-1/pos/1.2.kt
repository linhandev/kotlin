// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, call-and-property-access-expressions, spread-operator-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: multiple spread arguments mixed with regular arguments in sequence
 */

// TESTCASE NUMBER: 1

fun foo(vararg c: String): String = c.joinToString("")

fun box(): String {
    val a = "a"
    val b = arrayOf("b", "c", "d")
    val c = "e"
    val d = arrayOf<String>()
    val e = arrayOf("f", "g")
    if (foo(a, *b, c, *d, *e) != "abcdefg") return "NOK"
    return "OK"
}
