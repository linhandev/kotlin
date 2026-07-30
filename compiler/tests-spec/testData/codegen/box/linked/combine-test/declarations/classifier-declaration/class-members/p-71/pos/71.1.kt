// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 71 -> sentence 71
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 71 -> sentence 71
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 71 -> sentence 71
 * NUMBER: 1
 * DESCRIPTION: no-arg invoke Boolean as if condition; both branches
 */

// TESTCASE NUMBER: 1

class NoArg(val flag: Boolean) {
    operator fun invoke(): Boolean = flag
}

fun test(n: NoArg): String = if (n()) "yes" else "no"

fun box(): String {
    if (test(NoArg(true)) != "yes") return "NOK: true"
    if (test(NoArg(false)) != "no") return "NOK: false"
    return "OK"
}
