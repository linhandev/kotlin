// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 11 -> sentence 11
 * NUMBER: 2
 * DESCRIPTION: MOD token used in operator overloading a.rem(b)
 */

data class WrappedInt(val value: Int) {
    operator fun rem(other: WrappedInt): WrappedInt = WrappedInt(value % other.value)
}

// TESTCASE NUMBER: 1
fun box(): String {
    val a = WrappedInt(17)
    val b = WrappedInt(5)
    val c = a % b
    return if (c.value == 2) "OK" else "NOK"
}
