// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 54 -> sentence 54
 * NUMBER: 4
 * DESCRIPTION: THIS_AT token in this@Outer calling outer member from inner class
 */
// TESTCASE NUMBER: 1

class Outer {
    fun value() = 21
    inner class Inner {
        fun doubled() = this@Outer.value() * 2
    }
}

fun box(): String {
    return if (Outer().Inner().doubled() == 42) "OK" else "NOK"
}
