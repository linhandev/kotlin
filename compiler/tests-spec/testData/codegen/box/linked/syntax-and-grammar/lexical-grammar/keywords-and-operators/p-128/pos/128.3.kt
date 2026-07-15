// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 128 -> sentence 128
 * NUMBER: 3
 * DESCRIPTION: ACTUAL token used as backtick-escaped class name
 */
// TESTCASE NUMBER: 1
class `actual` {
    fun value(): String = "kw-128-128-3"
}

fun box(): String { val ok = `actual`().value() == "kw-128-128-3"; return if (ok) "OK" else "NOK" }
