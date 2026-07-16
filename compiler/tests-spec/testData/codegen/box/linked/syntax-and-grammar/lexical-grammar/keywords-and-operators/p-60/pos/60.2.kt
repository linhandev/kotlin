// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 60 -> sentence 60
 * NUMBER: 2
 * DESCRIPTION: SET token in property setter with backing field and runtime validation
 */
// TESTCASE NUMBER: 1

class SetterValidate60 {
    private var backing = 0
    var score: Int
        get() = backing
        set(value) {
            backing = value * 2
        }
}

fun box(): String {
    val holder = SetterValidate60()
    holder.score = 21
    return if (holder.score == 42) "OK" else "NOK"
}
