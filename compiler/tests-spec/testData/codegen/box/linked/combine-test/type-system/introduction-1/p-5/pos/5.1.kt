// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, class-literals -> paragraph 5 -> sentence 5
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 5 -> sentence 5
 *                type-system, introduction-1 -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: List values with different type arguments share ::class and both pass is List<*> due to erasure
 */

// TESTCASE NUMBER: 1
fun test56205(): Boolean {
    val a: Any = listOf(1, 2)
    val b: Any = listOf("x", "y")
    return a::class == b::class && a is List<*> && b is List<*>
}

fun box(): String {
    if (!test56205()) return "NOK"
    if ((listOf(1) as Any)::class != (listOf("a") as Any)::class) return "NOK"
    return "OK"
}
