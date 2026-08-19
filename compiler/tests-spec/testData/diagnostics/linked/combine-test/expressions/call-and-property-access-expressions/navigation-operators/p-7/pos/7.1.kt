// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, navigation-operators -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 7 -> sentence 7
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: non-null receiver direct member access infers non-null result type String
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class User(val name: String)
fun case1(u: User) {
    checkSubtype<String>(u.name)
}
