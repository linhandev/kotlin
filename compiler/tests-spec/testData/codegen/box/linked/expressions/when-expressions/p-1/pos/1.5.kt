// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, when-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 5
 * DESCRIPTION: when (1) { 1 -> executed = true } without else runs as statement
 */

// TESTCASE NUMBER: 1

fun box(): String {
    var executed = false
    when (1) {
        1 -> { executed = true }
    }
    return if (executed) "OK" else "NOK"
}
