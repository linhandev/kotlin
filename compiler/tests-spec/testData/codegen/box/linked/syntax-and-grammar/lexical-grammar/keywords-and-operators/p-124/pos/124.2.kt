// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 124 -> sentence 124
 * NUMBER: 2
 * DESCRIPTION: NOINLINE token in inline function with multiple noinline parameters
 */
inline fun select124(noinline onTrue: () -> String, noinline onFalse: () -> String, flag: Boolean): String {
    return if (flag) onTrue() else onFalse()
}

// TESTCASE NUMBER: 1
fun box(): String = select124({ "OK" }, { "NOK" }, true)
