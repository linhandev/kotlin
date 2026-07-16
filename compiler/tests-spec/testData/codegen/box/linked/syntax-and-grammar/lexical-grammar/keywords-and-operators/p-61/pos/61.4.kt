// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 61 -> sentence 61
 * NUMBER: 4
 * DESCRIPTION: RECEIVER token on extension function with block body and runtime check
 */
// TESTCASE NUMBER: 1

fun @receiver:Suppress("UNUSED_PARAMETER") StringBuilder.appendOk(): StringBuilder {
    append("kw-61-61-4")
    return this
}

fun box(): String {
    val expected = "kw-61-61-4"
    val result = StringBuilder().appendOk().toString()
    if (result != expected) return "NOK"
    return "OK"
}
