// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 64 -> sentence 64
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 64 -> sentence 64
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 64 -> sentence 64
 * NUMBER: 1
 * DESCRIPTION: member invoke returning Nothing is usable where Int expected
 */

// TESTCASE NUMBER: 1
class Fail {
    operator fun invoke(): Nothing = throw Exception()
}

fun case1(): Int = Fail()()
