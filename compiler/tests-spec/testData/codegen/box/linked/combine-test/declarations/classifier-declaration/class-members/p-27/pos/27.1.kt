// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 27 -> sentence 27
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 27 -> sentence 27
 *                expressions, comparison-expressions -> paragraph 27 -> sentence 27
 *                expressions, when-expressions -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: class member compareTo used in when expression branch selection
 */

// TESTCASE NUMBER: 1
class Vector(val x: Int) {
    operator fun compareTo(other: Vector) = x.compareTo(other.x)
}

fun test(v: Vector): String = when {
    v > Vector(5) -> "big"
    else -> "small"
}

fun box(): String {
    if (test(Vector(6)) != "big") return "NOK"
    if (test(Vector(5)) != "small") return "NOK"
    if (test(Vector(3)) != "small") return "NOK"
    return "OK"
}
