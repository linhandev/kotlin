// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 14 -> sentence 14
 * NUMBER: 2
 * DESCRIPTION: SUB token used in operator overloading a.minus(b); member minus returns expected difference
 */

// TESTCASE NUMBER: 1
class Sub14(private val value: Int) {
    fun minus(other: Int): Int = value - other
}

fun box(): String {
    val result = Sub14(20).minus(7)
    return if (result == 13) "OK" else "NOK"
}
