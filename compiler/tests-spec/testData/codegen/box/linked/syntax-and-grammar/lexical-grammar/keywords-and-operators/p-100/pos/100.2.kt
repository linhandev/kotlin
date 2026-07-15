// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 100 -> sentence 100
 * NUMBER: 2
 * DESCRIPTION: OUT token in class type parameter out T
 */
// TESTCASE NUMBER: 1
class CovariantHolder100<out T>(private val value: T) {
    fun get(): T = value
}

fun box(): String = if (CovariantHolder100("codegen-100-2").get() == "codegen-100-2") "OK" else "NOK"
