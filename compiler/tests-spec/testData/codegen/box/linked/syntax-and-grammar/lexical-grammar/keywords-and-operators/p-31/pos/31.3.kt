// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 31 -> sentence 31
 * NUMBER: 3
 * DESCRIPTION: RANGE token used in contains check 5 in 1..10
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val contained = 5 in 1..10
    val notContained = 11 in 1..10
    return if (contained && !notContained) "OK" else "NOK"
}
