// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: custom operator component1 supports lambda destructuring
 */

// TESTCASE NUMBER: 1
class Box(val v: Int) {
    operator fun component1(): Int = v
}

fun test(b: Box): Int = b.let { (x) -> x }

fun box(): String {
    if (test(Box(42)) != 42) return "NOK"
    return "OK"
}
