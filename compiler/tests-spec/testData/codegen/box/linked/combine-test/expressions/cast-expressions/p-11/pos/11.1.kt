// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, cast-expressions -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 11 -> sentence 11
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 11 -> sentence 11
 *                type-inference, smart-casts -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: is-safe access vs as failure path
 */

// TESTCASE NUMBER: 1
fun viaIs(x: Any): Int = if (x is String) x.length else -1

fun viaAs(x: Any): Int = try {
    (x as String).length
} catch (_: ClassCastException) {
    -1
}

fun box(): String {
    if (viaIs("hi") != 2) return "NOK"
    if (viaIs(1) != -1) return "NOK"
    if (viaAs("hi") != 2) return "NOK"
    if (viaAs(1) != -1) return "NOK"
    return "OK"
}
