// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, equality-expressions -> paragraph 27 -> sentence 27
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: == null short-circuits without calling equals
 */

// TESTCASE NUMBER: 1
class C {
    override fun equals(other: Any?): Boolean = other != null
}

fun test(): Boolean = C() == null

fun box(): String {
    if (test()) return "NOK"
    return "OK"
}
