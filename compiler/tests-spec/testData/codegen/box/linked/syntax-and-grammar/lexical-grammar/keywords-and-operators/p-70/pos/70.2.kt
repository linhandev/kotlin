// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 70 -> sentence 70
 * NUMBER: 2
 * DESCRIPTION: OBJECT token in anonymous object expression
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val expected = "object-70"
    val holder = object {
        fun value(): String = expected
    }
    if (holder.value() != expected) return "NOK"
    return "OK"
}
