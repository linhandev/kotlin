// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 62 -> sentence 62
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 62 -> sentence 62
 * NUMBER: 1
 * DESCRIPTION: primary constructor var parameter declares mutable property
 */

// TESTCASE NUMBER: 1
class Counter(var n: Int)

fun test(): Int { val c = Counter(1); c.n = 2; return c.n }

fun box(): String {
    if (test() != 2) return "NOK"
    return "OK"
}
