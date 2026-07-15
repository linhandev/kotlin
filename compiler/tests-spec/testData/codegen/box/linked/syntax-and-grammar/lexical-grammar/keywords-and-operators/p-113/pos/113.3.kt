// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 113 -> sentence 113
 * NUMBER: 3
 * DESCRIPTION: INLINE token in inline function with multiple function parameters
 */
inline fun select113(flag: Boolean, whenTrue: () -> String, whenFalse: () -> String): String {
    return if (flag) whenTrue() else whenFalse()
}

// TESTCASE NUMBER: 1
fun box(): String = select113(true, { "OK" }, { "NOK" })
