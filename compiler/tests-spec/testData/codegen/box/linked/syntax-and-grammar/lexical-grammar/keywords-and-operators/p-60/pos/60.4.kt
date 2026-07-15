// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 60 -> sentence 60
 * NUMBER: 4
 * DESCRIPTION: SET token in local object property setter set(value)
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val expected = "set-60-4"
    var backing = "NOK"
    val holder = object {
        var token: String
            get() = backing
            set(value) {
                backing = value
            }
    }
    holder.token = expected
    if (holder.token != expected) return "NOK"
    return "OK"
}
