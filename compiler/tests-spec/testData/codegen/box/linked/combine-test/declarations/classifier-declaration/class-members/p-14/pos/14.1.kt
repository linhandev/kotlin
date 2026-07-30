// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 14 -> sentence 14
 *                expressions, prefix-expressions, prefix-decrement-expressions -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: class member operator fun dec called as member and returns decremented Counter
 */

// TESTCASE NUMBER: 1
class Counter(var value: Int) {
    operator fun dec() = Counter(--value)
}

fun test(): Int = Counter(1).dec().value

fun box(): String {
    if (test() != 0) return "NOK"
    return "OK"
}
