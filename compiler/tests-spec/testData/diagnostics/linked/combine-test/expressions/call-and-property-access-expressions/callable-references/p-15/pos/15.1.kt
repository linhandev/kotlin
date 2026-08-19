// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 15 -> sentence 15
 *                declarations, declarations-with-type-parameters -> paragraph 15 -> sentence 15
 *                overload-resolution, resolving-callable-references -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: generic top-level function reference ::id disambiguated to id<Int> via expected function type (Int) -> Int, verifying type inference
 * HELPERS: checkType
 */

fun <T> id(x: T): T = x

// TESTCASE NUMBER: 1
fun case1() {
    val f: (Int) -> Int = ::id
    checkSubtype<(Int) -> Int>(f)
}
