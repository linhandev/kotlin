// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 8 -> sentence 8
 * NUMBER: 3
 * DESCRIPTION: LCURL token used in lambda expression { x -> x + 1 }
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val numbers = listOf(1, 2, 3)
    val doubled = numbers.map { it * 2 }
    return if (doubled == listOf(2, 4, 6)) "OK" else "NOK"
}
