/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, determining-function-applicability-for-a-specific-call, description -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: extension callable remains applicable for Nothing receiver at compile time
 */

fun Nothing.ext11302N(): String = "ext"

fun invokeExt11302N(n: Nothing): String = n.ext11302N()

// TESTCASE NUMBER: 1
fun box(): String {
    return try {
        invokeExt11302N(throw AssertionError("stop"))
        "NOK"
    } catch (_: AssertionError) {
        "OK"
    }
}
