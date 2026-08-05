// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: packages-and-imports, importing -> paragraph 6 -> sentence 6
 *                declarations, property-declaration -> paragraph 6 -> sentence 6
 *                expressions, call-expressions -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: imported top-level property used as call argument by short name
 */
import kotlin.math.PI

fun scale56006(x: Double): Double = x * 2

// TESTCASE NUMBER: 1
fun test(): Double = scale56006(PI)

fun box(): String {
    if (test() <= 6.0 || test() >= 8.0) return "NOK"
    return "OK"
}
