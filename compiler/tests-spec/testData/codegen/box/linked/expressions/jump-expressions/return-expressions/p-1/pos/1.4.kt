// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, jump-expressions, return-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: return@label returns from labeled lambda literal
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val read = label@ {
        return@label "labeled-value"
    }
    return if (read() == "labeled-value") "OK" else "NOK"
}
