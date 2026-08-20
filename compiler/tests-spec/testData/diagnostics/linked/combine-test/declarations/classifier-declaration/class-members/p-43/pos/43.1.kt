// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 43 -> sentence 43
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 43 -> sentence 43
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 43 -> sentence 43
 * NUMBER: 1
 * DESCRIPTION: invoke String return type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Factory {
    operator fun invoke(): String = "hello"
}

fun test(): String = Factory()()

fun case1() {
    checkSubtype<String>(test())
}
