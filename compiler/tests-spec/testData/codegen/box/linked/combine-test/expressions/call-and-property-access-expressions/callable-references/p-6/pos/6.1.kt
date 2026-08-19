// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 6 -> sentence 6
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: mutable property reference Box::v reads the current property value after mutation, verifying runtime semantics
 */

class Box(var v: Int)

// TESTCASE NUMBER: 1
fun test(b: Box): Int {
    b.v = 2
    val read: (Box) -> Int = Box::v
    return read(b)
}

fun box(): String {
    val b = Box(1)
    if (test(b) != 2) return "NOK"
    return "OK"
}
