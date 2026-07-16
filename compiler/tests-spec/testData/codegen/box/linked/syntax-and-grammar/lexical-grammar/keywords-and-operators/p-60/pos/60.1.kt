// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 60 -> sentence 60
 * NUMBER: 1
 * DESCRIPTION: SET token in property setter set(value) { block } form
 */
// TESTCASE NUMBER: 1

class SetterBlock60 {
    var stored = "NOK"
        set(value) {
            field = value
        }
}

fun box(): String {
    val expected = "set-60"
    val holder = SetterBlock60()
    holder.stored = expected
    if (holder.stored != expected) return "NOK"
    return "OK"
}
