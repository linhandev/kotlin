// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 29 -> sentence 29
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 29 -> sentence 29
 *                expressions, function-literals, lambda-literals -> paragraph 29 -> sentence 29
 * NUMBER: 1
 * DESCRIPTION: data class componentN used both via lambda destructuring and explicit componentN calls
 */

// TESTCASE NUMBER: 1
data class Pt(val x: Int, val y: Int)

fun viaDestructure(xs: List<Pt>): Int =
    xs.map { (a, b) -> a * b }.sum()

fun viaComponentN(xs: List<Pt>): Int =
    xs.map { it.component1() * it.component2() }.sum()

fun box(): String {
    val xs = listOf(Pt(2, 3), Pt(4, 5))
    if (viaDestructure(xs) != 26) return "NOK: destructure"
    if (viaComponentN(xs) != 26) return "NOK: componentN"
    if (viaDestructure(xs) != viaComponentN(xs)) return "NOK: mismatch"
    return "OK"
}
