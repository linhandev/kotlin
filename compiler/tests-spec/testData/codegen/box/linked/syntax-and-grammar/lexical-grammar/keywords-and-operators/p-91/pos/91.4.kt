// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 91 -> sentence 91
 * NUMBER: 4
 * DESCRIPTION: THROW token in when expression else branch
 */
// TESTCASE NUMBER: 1
fun throwInWhen91(value: Int): String {
    return when (value) {
        42 -> "OK"
        else -> throw IllegalStateException("unexpected")
    }
}

fun box(): String = throwInWhen91(42)
