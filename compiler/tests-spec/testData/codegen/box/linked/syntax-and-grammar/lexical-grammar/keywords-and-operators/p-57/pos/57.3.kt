// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 57 -> sentence 57
 * NUMBER: 3
 * DESCRIPTION: FIELD token in bracket use-site @field:[JvmField Suppress]
 */
// TESTCASE NUMBER: 1

class FieldBracket57 {
    @field:[JvmField Suppress("UNUSED_VARIABLE")]
    var data = "kw-57-57-3"
}

fun box(): String {
    val expected = "kw-57-57-3"
    val result = FieldBracket57().data
    if (result != expected) return "NOK"
    return "OK"
}
