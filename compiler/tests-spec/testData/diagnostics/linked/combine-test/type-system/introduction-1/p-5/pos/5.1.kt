// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, class-literals -> paragraph 5 -> sentence 5
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 5 -> sentence 5
 *                type-system, introduction-1 -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: List values with different type arguments share ::class and both pass is List<*> due to erasure type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val a: Any = listOf(1, 2)
    val b: Any = listOf("x", "y")
    checkSubtype<Boolean>(a::class == b::class)
    checkSubtype<Boolean>(a is List<*>)
    checkSubtype<Boolean>(b is List<*>)
}
