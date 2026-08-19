// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, navigation-operators -> paragraph 29 -> sentence 29
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 29 -> sentence 29
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 29 -> sentence 29
 *                expressions, when-expressions -> paragraph 29 -> sentence 29
 *                type-inference, smart-casts -> paragraph 29 -> sentence 29
 * NUMBER: 1
 * DESCRIPTION: when null check branch smart-casts to non-null type in else branch, infers String result
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class User(val name: String)

fun case1(u: User?) {
    val r = when (u) {
        null -> "guest"
        else -> u.name
    }
    checkSubtype<String>(r)
}
