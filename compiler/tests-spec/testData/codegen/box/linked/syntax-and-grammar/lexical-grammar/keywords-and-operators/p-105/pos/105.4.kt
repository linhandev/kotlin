// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 105 -> sentence 105
 * NUMBER: 4
 * DESCRIPTION: INTERNAL token in internal object declaration
 */
internal object InternalSingleton105 {
    fun value(): String = "codegen-105-4"
}

// TESTCASE NUMBER: 1
fun box(): String = if (InternalSingleton105.value() == "codegen-105-4") "OK" else "NOK"
