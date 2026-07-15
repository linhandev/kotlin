// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 87 -> sentence 87
 * NUMBER: 2
 * DESCRIPTION: FINALLY token in try-catch-finally statement
 */
// TESTCASE NUMBER: 1
fun finallyAll87(): String {
    var finallyRan = false
    var result = "NOK"
    try {
        result = "body"
    } catch (_: Exception) {
        return "NOK"
    } finally {
        finallyRan = true
    }
    return if (finallyRan && result == "body") "finally-87-2" else "NOK"
}

fun box(): String { val ok = finallyAll87() == "finally-87-2"; return if (ok) "OK" else "NOK" }
