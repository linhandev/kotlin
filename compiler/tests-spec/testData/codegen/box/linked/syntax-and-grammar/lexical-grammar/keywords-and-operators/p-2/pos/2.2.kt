// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: DOT token used for method invocation (obj.method())
 */
// TESTCASE NUMBER: 1

class Calculator {
    fun add(a: Int, b: Int): Int = a + b
}

fun box(): String {
    val calc = Calculator()
    return if (calc.add(2, 3) == 5) "OK" else "NOK"
}
