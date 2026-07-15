// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 64 -> sentence 64
 * NUMBER: 1
 * DESCRIPTION: DELEGATE token in use-site annotation @delegate:Suppress on delegated property
 */
// TESTCASE NUMBER: 1

class DelegateLazy64 {
    @delegate:Suppress("UNUSED")
    val answer: String by lazy { "kw-64-64-1" }
}

fun box(): String {
    val expected = "kw-64-64-1"
    val result = DelegateLazy64().answer
    if (result != expected) return "NOK"
    return "OK"
}
