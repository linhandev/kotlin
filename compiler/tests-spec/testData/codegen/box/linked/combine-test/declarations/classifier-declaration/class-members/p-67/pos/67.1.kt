// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 67 -> sentence 67
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 67 -> sentence 67
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 67 -> sentence 67
 * NUMBER: 1
 * DESCRIPTION: more specific invoke(Int) preferred over invoke(Number)
 */

// TESTCASE NUMBER: 1
class Multi {
    operator fun invoke(x: Int): Int = x
    operator fun invoke(x: Number): Int = x.toInt() + 1000
}

fun test(): Int = Multi()(42)

fun box(): String {
    if (test() != 42) return "NOK"
    return "OK"
}
