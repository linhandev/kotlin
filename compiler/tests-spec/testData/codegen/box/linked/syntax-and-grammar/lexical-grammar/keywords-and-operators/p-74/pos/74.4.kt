// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 74 -> sentence 74
 * NUMBER: 4
 * DESCRIPTION: CONSTRUCTOR token in multiple secondary constructors with delegation
 */
// TESTCASE NUMBER: 1

open class BaseCtor74(val base: String)

class DerivedCtor74 : BaseCtor74 {
    constructor() : super("kw-74-74-4")
}

fun box(): String {
    val expected = "kw-74-74-4"
    val result = DerivedCtor74().base
    if (result != expected) return "NOK"
    return "OK"
}
