// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 17 -> sentence 17
 *                overload-resolution, resolving-callable-references -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: expected function type (Int) -> Int helps select the Int overload among callable references, verifying type inference
 * HELPERS: checkType
 */

fun f(x: Int): Int = x
fun f(x: String): String = x

// TESTCASE NUMBER: 1
fun case1() {
    val g: (Int) -> Int = ::f
    checkSubtype<(Int) -> Int>(g)
}
