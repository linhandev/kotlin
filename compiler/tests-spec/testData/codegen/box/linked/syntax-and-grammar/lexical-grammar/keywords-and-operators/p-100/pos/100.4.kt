// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 100 -> sentence 100
 * NUMBER: 4
 * DESCRIPTION: OUT token in interface with multiple out type parameters
 */
// TESTCASE NUMBER: 1
interface PairSource100<out A, out B> {
    fun first(): A
    fun second(): B
}

class PairSourceImpl100 : PairSource100<String, Int> {
    override fun first(): String = "codegen-100-4"
    override fun second(): Int = 1
}

fun box(): String = if (PairSourceImpl100().first() == "codegen-100-4") "OK" else "NOK"
