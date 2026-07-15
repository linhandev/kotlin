// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 72 -> sentence 72
 * NUMBER: 3
 * DESCRIPTION: VAR token in var property with custom setter
 */
// TESTCASE NUMBER: 1

class VarSetter72 {
    private var backing = 0
    var score: Int
        get() = backing
        set(value) {
            backing = value
        }
}

fun box(): String {
    val holder = VarSetter72()
    holder.score = 42
    return if (holder.score == 42) "OK" else "NOK"
}
