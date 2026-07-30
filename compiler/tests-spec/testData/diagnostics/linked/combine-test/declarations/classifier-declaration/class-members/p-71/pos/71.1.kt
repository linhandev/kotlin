// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 71 -> sentence 71
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 71 -> sentence 71
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 71 -> sentence 71
 * NUMBER: 1
 * DESCRIPTION: no-arg invoke as if condition yields String on both branches
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1

class NoArg(val flag: Boolean) {
    operator fun invoke(): Boolean = flag
}

fun case1(n: NoArg) {
    checkSubtype<String>(if (n()) "yes" else "no")
}
