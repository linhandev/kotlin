// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, local-class-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: local class captures outer variable at runtime
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val x = 2
    class Local {
        val y = x
    }
    return if (Local().y == 2) "OK" else "NOK"
}
