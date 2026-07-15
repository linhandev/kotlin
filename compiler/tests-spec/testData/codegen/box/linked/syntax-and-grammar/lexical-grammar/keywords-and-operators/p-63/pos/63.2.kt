// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 63 -> sentence 63
 * NUMBER: 2
 * DESCRIPTION: SETPARAM token with @set and @setparam on same mutable property
 */
// TESTCASE NUMBER: 1

class SetparamCombo63 {
    @set:Suppress("UNUSED")
    @setparam:Suppress("UNUSED_PARAMETER")
    var score: Int = 0
        set(value) {
            field = value + 1
        }
}

fun box(): String {
    val holder = SetparamCombo63()
    holder.score = 41
    return if (holder.score == 42) "OK" else "NOK"
}
