// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, object-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: object singleton identity at runtime
 */

// TESTCASE NUMBER: 1
object Config {
    val version = "1.0"
}

fun box(): String {
    val a = Config
    val b = Config
    return if (a === b && a.version == "1.0") "OK" else "NOK"
}
