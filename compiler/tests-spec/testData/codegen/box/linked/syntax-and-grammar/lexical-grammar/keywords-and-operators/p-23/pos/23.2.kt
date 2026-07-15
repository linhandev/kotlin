// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 23 -> sentence 23
 * NUMBER: 2
 * DESCRIPTION: ASSIGNMENT token used in class property initializer class C { var p = 42 }
 */
// TESTCASE NUMBER: 1

class Holder {
    var p = 42
}

fun box(): String {
    val holder = Holder()
    return if (holder.p == 42) "OK" else "NOK"
}
