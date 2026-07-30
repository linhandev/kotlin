// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 54 -> sentence 54
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 54 -> sentence 54
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 54 -> sentence 54
 *                declarations, declarations-with-type-parameters -> paragraph 54 -> sentence 54
 * NUMBER: 1
 * DESCRIPTION: generic class member invoke infers type argument
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Generic<T>(val value: T) {
    operator fun invoke(): T = value
}

fun case1() {
    checkSubtype<String>(Generic("hello")())
}
