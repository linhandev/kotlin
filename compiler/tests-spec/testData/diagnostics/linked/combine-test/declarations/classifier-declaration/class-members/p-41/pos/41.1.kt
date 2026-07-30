// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 41 -> sentence 41
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 41 -> sentence 41
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 41 -> sentence 41
 * NUMBER: 1
 * DESCRIPTION: invoke result in arithmetic infers Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1

class Callable {
    operator fun invoke(): Int = 40
}

fun case1() {
    checkSubtype<Int>(Callable()() + 2)
}
