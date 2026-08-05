// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 27 -> sentence 27
 * PRIMARY LINKS: packages-and-imports, importing -> paragraph 27 -> sentence 27
 *                scopes-and-identifiers, linked-scopes -> paragraph 27 -> sentence 27
 *                declarations, function-declaration -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: same-file top-level declaration takes priority over an identically named imported function
 */
import kotlin.math.sqrt

fun sqrt(x: Int): Int = x

// TESTCASE NUMBER: 1
fun test(): Int = sqrt(4)

fun box(): String {
    if (test() != 4) return "NOK"
    if (sqrt(9) != 9) return "NOK"
    return "OK"
}
