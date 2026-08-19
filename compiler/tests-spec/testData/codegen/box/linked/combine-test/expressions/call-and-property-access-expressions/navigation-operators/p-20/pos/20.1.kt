// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, navigation-operators -> paragraph 20 -> sentence 20
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 20 -> sentence 20
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: safe call on nullable outer instance creates inner class and accesses member, null short-circuits
 */

// TESTCASE NUMBER: 1
class Outer {
    inner class Inner(val v: Int)
}

fun test(o: Outer?): Int? = o?.Inner(42)?.v

fun box(): String {
    val o = Outer()
    if (test(o) != 42) return "NOK: non-null outer returns inner value"
    if (test(null) != null) return "NOK: null outer returns null"
    return "OK"
}
