// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 51 -> sentence 51
 * NUMBER: 3
 * DESCRIPTION: RETURN_AT token in return@outer from inline function lambda
 */

inline fun compute(block: () -> String): String = block()

// TESTCASE NUMBER: 1
fun box(): String {
    return compute outer@ {
        return@outer "OK"
    }
}
