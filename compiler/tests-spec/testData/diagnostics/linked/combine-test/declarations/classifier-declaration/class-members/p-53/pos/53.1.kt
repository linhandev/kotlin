// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 53 -> sentence 53
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 53 -> sentence 53
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 53 -> sentence 53
 *                type-system, introduction-1 -> paragraph 53 -> sentence 53
 * NUMBER: 1
 * DESCRIPTION: safe call of member invoke infers nullable Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Callable {
    operator fun invoke(): Int = 42
}

fun case1(callable: Callable?) {
    checkSubtype<Int?>(callable?.invoke())
}
