// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 85 -> sentence 85
 * NUMBER: 3
 * DESCRIPTION: TRY token in try-catch-finally statement
 */
// TESTCASE NUMBER: 1
fun tryAll85(flag: Boolean): String {
    var finallyRan = false
    var result = "NOK"
    try {
        if (flag) result = "OK"
        else throw RuntimeException("fail")
    } catch (_: RuntimeException) {
        return "NOK"
    } finally {
        finallyRan = true
    }
    return if (finallyRan && result == "OK") "OK" else "NOK"
}

fun box(): String {
    if (tryAll85(true) != "OK") return "NOK"
    if (tryAll85(false) != "NOK") return "NOK"
    return "OK"
}
