// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 66 -> sentence 66
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 66 -> sentence 66
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 66 -> sentence 66
 * NUMBER: 1
 * DESCRIPTION: object invoke captures mutable local via function reference
 */

// TESTCASE NUMBER: 1
fun create(): () -> Int {
    var x = 0
    val o = object {
        operator fun invoke(): Int = ++x
    }
    return o::invoke
}

fun test(): Int = create().let { it() + it() }

fun box(): String {
    if (test() != 3) return "NOK"
    return "OK"
}
