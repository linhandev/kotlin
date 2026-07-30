// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 44 -> sentence 44
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 44 -> sentence 44
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 44 -> sentence 44
 * NUMBER: 1
 * DESCRIPTION: invoke overload resolution prefers invoke(Int) when argument present
 */

// TESTCASE NUMBER: 1
class Multi {
    operator fun invoke(): Int = 1
    operator fun invoke(x: Int): Int = x
}

fun test(): Int = Multi()(42)

fun box(): String {
    if (test() != 42) return "NOK: invoke(Int)"
    if (Multi()() != 1) return "NOK: invoke()"
    return "OK"
}
