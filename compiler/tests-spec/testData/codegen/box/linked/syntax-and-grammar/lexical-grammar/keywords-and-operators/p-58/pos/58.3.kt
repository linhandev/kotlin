// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 58 -> sentence 58
 * NUMBER: 3
 * DESCRIPTION: PROPERTY token in bracket use-site @property:[Suppress]
 */
// TESTCASE NUMBER: 1

class PropertyBracket58 {
    @property:[Suppress("UNUSED_VARIABLE")]
    val flag = true
}

fun box(): String {
    return if (PropertyBracket58().flag) "OK" else "NOK"
}
