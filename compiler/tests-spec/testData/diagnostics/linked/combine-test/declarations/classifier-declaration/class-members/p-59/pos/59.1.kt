// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 59 -> sentence 59
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 59 -> sentence 59
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 59 -> sentence 59
 * NUMBER: 1
 * DESCRIPTION: inner class member invoke infers String
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Outer {
    inner class Inner {
        operator fun invoke(): String = "inner"
    }
}

fun case1() {
    checkSubtype<String>(Outer().Inner()())
}
