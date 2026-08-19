// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 47 -> sentence 47
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 47 -> sentence 47
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 47 -> sentence 47
 * NUMBER: 1
 * DESCRIPTION: invoke delegates to stored lambda property
 */

// TESTCASE NUMBER: 1
class Wrapper(val fn: () -> Int) {
    operator fun invoke(): Int = fn()
}

fun test(): Int = Wrapper { 42 }()

fun box(): String {
    if (test() != 42) return "NOK"
    return "OK"
}
