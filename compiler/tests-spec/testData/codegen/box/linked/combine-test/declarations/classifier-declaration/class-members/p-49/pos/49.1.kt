// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 49 -> sentence 49
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 49 -> sentence 49
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 49 -> sentence 49
 * NUMBER: 1
 * DESCRIPTION: invoke returns current value of class property
 */

// TESTCASE NUMBER: 1
class Container(var value: Int) {
    operator fun invoke(): Int = value
}

fun test(): Int = Container(42)()

fun box(): String {
    if (test() != 42) return "NOK"
    val c = Container(1)
    c.value = 7
    if (c() != 7) return "NOK: updated"
    return "OK"
}
