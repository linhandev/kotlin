// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 35 -> sentence 35
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 35 -> sentence 35
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 35 -> sentence 35
 * NUMBER: 1
 * DESCRIPTION: no-arg member invoke infers Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1

class Generator {
    operator fun invoke() = 42
}

fun case1() {
    checkSubtype<Int>(Generator()())
}
