// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, navigation-operators -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 3 -> sentence 3
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 3 -> sentence 3
 *                expressions, call-expressions -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: safe call on nullable receiver calling member function infers nullable result type String?
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class User(val name: String) {
    fun greet() = "hi $name"
}

fun case1(u: User?) {
    checkSubtype<String?>(u?.greet())
}
