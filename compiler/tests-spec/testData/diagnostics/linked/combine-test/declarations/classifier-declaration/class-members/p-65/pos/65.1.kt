// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 65 -> sentence 65
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 65 -> sentence 65
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 65 -> sentence 65
 *                expressions, elvis-operator-expressions -> paragraph 65 -> sentence 65
 * NUMBER: 1
 * DESCRIPTION: elvis after nullable invoke infers Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1

class Callable(val result: Int?) {
    operator fun invoke(): Int? = result
}

fun case1(c: Callable) {
    checkSubtype<Int>(c() ?: 0)
}
