// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 127 -> sentence 127
 * NUMBER: 3
 * DESCRIPTION: EXPECT token used as backtick-escaped class name
 */
// TESTCASE NUMBER: 1
class `expect` {
    fun value(): String = "kw-127-127-3"
}

fun box(): String { val ok = `expect`().value() == "kw-127-127-3"; var out = if (ok) "OK" else "NOK"; return out }
