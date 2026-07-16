// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, when-expressions, exhaustive-when-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: exhaustive when on kotlin.Boolean with true and false constant branches
 */

// TESTCASE NUMBER: 1

fun box(): String {
    fun f(b: Boolean) = when (b) {
        true -> "T"
        false -> "F"
    }
    return if (f(true) == "T" && f(false) == "F") "OK" else "NOK"
}
