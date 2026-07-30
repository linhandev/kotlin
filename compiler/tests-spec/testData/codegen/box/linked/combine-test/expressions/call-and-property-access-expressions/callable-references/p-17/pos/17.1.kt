// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 17 -> sentence 17
 *                overload-resolution, resolving-callable-references -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: expected function type (Int) -> Int helps select the Int overload among callable references, verifying runtime semantics
 */

fun f(x: Int): Int = x
fun f(x: String): String = x

val g: (Int) -> Int = ::f

// TESTCASE NUMBER: 1
fun test(): Int = g(1)

fun box(): String {
    if (test() != 1) return "NOK"
    if (g(42) != 42) return "NOK"
    return "OK"
}
