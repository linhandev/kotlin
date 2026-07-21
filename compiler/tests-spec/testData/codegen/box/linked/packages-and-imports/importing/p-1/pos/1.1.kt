// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: packages-and-imports, importing -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: kotlin.collections is implicitly imported without explicit importHeader
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val xs = listOf(1, 2, 3)
    return if (xs.size == 3) "OK" else "NOK"
}
