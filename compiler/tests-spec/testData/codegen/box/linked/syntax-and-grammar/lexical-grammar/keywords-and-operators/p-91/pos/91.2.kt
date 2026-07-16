// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 91 -> sentence 91
 * NUMBER: 2
 * DESCRIPTION: THROW token in conditional throw statement
 */
// TESTCASE NUMBER: 1
fun throwIf91(value: Int): String {
    if (value != 42) throw IllegalArgumentException("bad")
    return "OK"
}

fun box(): String = throwIf91(42)
