// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: object singleton const property is readable at runtime
 */

// TESTCASE NUMBER: 1
object Singleton {
    const val MARKER = "OK"
}

fun box(): String {
    return if (Singleton.MARKER == "OK") "OK" else "NOK"
}
