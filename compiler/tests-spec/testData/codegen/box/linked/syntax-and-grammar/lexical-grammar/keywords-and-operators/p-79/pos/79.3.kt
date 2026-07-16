// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 79 -> sentence 79
 * NUMBER: 3
 * DESCRIPTION: SUPER token in secondary constructor delegation super(...)
 */
// TESTCASE NUMBER: 1

open class BaseCtorSuper79(val base: String)

class DerivedCtorSuper79 : BaseCtorSuper79 {
    constructor() : super("kw-79-79-3")
}

fun box(): String {
    val expected = "kw-79-79-3"
    val result = DerivedCtorSuper79().base
    if (result != expected) return "NOK"
    return "OK"
}
