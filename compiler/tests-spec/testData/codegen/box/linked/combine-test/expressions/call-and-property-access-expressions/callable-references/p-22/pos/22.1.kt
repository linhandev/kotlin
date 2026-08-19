// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 22 -> sentence 22
 *                expressions, indexing-expressions -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: unbound operator member reference IntArray::get infers (IntArray, Int) -> Int and reads element by index, verifying runtime semantics
 */

val get: (IntArray, Int) -> Int = IntArray::get

// TESTCASE NUMBER: 1
fun test(a: IntArray): Int = get(a, 0)

fun box(): String {
    if (test(intArrayOf(42, 99)) != 42) return "NOK"
    if (test(intArrayOf(7, 8)) != 7) return "NOK"
    return "OK"
}
