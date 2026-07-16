// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 112 -> sentence 112
 * NUMBER: 1
 * DESCRIPTION: OPERATOR token in unaryPlus operator function
 */
// TESTCASE NUMBER: 1
class Counter112(private var value: Int) {
    operator fun unaryPlus(): Counter112 {
        value++
        return this
    }

    fun read(): Int = value
}

fun box(): String = if ((+Counter112(41)).read() == 42) "OK" else "NOK"
