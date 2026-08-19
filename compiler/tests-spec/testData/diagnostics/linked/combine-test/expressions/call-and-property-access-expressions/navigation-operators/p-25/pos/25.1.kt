// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, navigation-operators -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 25 -> sentence 25
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: safe call on nullable receiver with nullable-returning member infers nullable String? result
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class C {
    fun maybe(): String? = null
}

fun case1(c: C?) {
    checkSubtype<String?>(c?.maybe())
}
