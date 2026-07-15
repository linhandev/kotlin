// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 57 -> sentence 57
 * NUMBER: 4
 * DESCRIPTION: FIELD token in stacked @field annotations on one property
 */
// TESTCASE NUMBER: 1

class FieldStack57 {
    @field:Suppress("UNUSED_VARIABLE")
    @field:JvmField
    var score = 42
}

fun box(): String {
    return if (FieldStack57().score == 42) "OK" else "NOK"
}
