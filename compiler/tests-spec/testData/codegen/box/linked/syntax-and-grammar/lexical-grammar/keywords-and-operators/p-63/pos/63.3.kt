// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 63 -> sentence 63
 * NUMBER: 3
 * DESCRIPTION: SETPARAM token in bracket use-site @setparam:[Suppress] on mutable property
 */
// TESTCASE NUMBER: 1

class SetparamBracket63 {
    @setparam:[Suppress("UNUSED_PARAMETER")]
    var token: String = "NOK"
        set(value) {
            field = value
        }
}

fun box(): String {
    val expected = "setparam-63-3"
    val holder = SetparamBracket63()
    holder.token = expected
    if (holder.token != expected) return "NOK"
    return "OK"
}
