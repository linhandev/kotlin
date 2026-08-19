// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, class-literals -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: KClass.isInstance performs reflective type checks, verifying runtime semantics
 */

// TESTCASE NUMBER: 1
fun test(x: Any): Boolean = String::class.isInstance(x)

fun box(): String {
    if (!test("hello")) return "NOK1"
    if (test(42)) return "NOK2"
    return "OK"
}
