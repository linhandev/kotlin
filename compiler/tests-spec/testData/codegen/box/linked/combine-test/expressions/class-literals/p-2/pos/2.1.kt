// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, class-literals -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: class literal String::class.isInstance checks runtime type reflectively, verifying runtime semantics
 */

// TESTCASE NUMBER: 1
fun test(x: Any): Boolean = String::class.isInstance(x)

fun box(): String {
    if (!test("hello")) return "NOK1"
    if (test(42)) return "NOK2"
    if (!test("")) return "NOK3"
    return "OK"
}
