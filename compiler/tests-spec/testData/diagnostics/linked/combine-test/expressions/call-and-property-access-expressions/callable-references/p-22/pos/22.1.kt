// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 22 -> sentence 22
 *                expressions, indexing-expressions -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: unbound operator member reference IntArray::get infers (IntArray, Int) -> Int, verifying type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val get: (IntArray, Int) -> Int = IntArray::get
    checkSubtype<(IntArray, Int) -> Int>(get)
}
