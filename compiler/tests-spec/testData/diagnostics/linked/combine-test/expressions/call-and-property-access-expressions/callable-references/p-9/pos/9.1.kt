// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: top-level function reference ::inc infers function type (Int) -> Int, verifying type inference
 * HELPERS: checkType
 */

fun inc(x: Int): Int = x + 1

// TESTCASE NUMBER: 1
fun case1() {
    val f: (Int) -> Int = ::inc
    checkSubtype<(Int) -> Int>(f)
}
