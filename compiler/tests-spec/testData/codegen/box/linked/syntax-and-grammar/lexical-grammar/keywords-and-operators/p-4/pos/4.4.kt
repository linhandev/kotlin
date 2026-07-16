// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 4 -> sentence 4
 * NUMBER: 4
 * DESCRIPTION: LPAREN token used in constructor call MyClass()
 */
// TESTCASE NUMBER: 1

class Box(val value: Int)

fun box(): String {
    val b = Box(42)
    return if (b.value == 42) "OK" else "NOK"
}
