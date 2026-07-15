// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 122 -> sentence 122
 * NUMBER: 1
 * DESCRIPTION: LATEINIT token in lateinit var property declaration
 */
// TESTCASE NUMBER: 1
class LateInitHolder122 {
    lateinit var token122: String

    fun init122(value: String) {
        token122 = value
    }

    fun read122(): String = token122
}

fun box(): String {
    val expected = "lateinit-122"
    val holder = LateInitHolder122()
    holder.init122(expected)
    if (holder.read122() != expected) return "NOK"
    return "OK"
}
