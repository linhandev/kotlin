// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 100 -> sentence 100
 * NUMBER: 1
 * DESCRIPTION: OUT token in interface type parameter out T
 */
// TESTCASE NUMBER: 1
interface OutProducer100<out T> {
    fun produce(): T
}

class StringOut100 : OutProducer100<String> {
    override fun produce(): String = "codegen-100-1"
}

fun box(): String = if (StringOut100().produce() == "codegen-100-1") "OK" else "NOK"
