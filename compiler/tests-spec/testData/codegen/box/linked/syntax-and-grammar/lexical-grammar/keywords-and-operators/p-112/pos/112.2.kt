// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 112 -> sentence 112
 * NUMBER: 2
 * DESCRIPTION: OPERATOR token in plus binary operator function
 */
data class Money112(val cents: Int) {
    operator fun plus(other: Money112): Money112 = Money112(cents + other.cents)
}

// TESTCASE NUMBER: 1
fun box(): String = if ((Money112(40) + Money112(2)).cents == 42) "OK" else "NOK"
