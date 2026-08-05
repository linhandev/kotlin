// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: packages-and-imports, importing -> paragraph 5 -> sentence 5
 *                declarations, function-declaration -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: imported top-level function can be called without an explicit receiver
 */
import kotlin.math.max

// TESTCASE NUMBER: 1
fun test(): Int = max(3, 5)

fun box(): String {
    if (test() != 5) return "NOK"
    if (max(9, 2) != 9) return "NOK"
    return "OK"
}
