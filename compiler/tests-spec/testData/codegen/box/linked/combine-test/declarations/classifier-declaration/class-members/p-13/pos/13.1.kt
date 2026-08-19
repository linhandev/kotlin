// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 13 -> sentence 13
 *                expressions, prefix-expressions, prefix-increment-expressions -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: class member operator fun inc called as member and returns incremented Counter
 */

// TESTCASE NUMBER: 1
class Counter(var value: Int) {
    operator fun inc() = Counter(++value)
}

fun test(): Int = Counter(0).inc().value

fun box(): String {
    if (test() != 1) return "NOK"
    return "OK"
}
