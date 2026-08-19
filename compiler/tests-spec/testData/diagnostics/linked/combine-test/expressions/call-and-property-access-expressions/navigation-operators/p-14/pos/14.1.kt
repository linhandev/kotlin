// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, navigation-operators -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 14 -> sentence 14
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 14 -> sentence 14
 *                expressions, function-literals, lambda-literals -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: let with safe call infers non-null type inside lambda, Elvis converges result to non-null String
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class User(val name: String)

fun case1(u: User?) {
    checkSubtype<String>(u?.let { it.name } ?: "guest")
}
