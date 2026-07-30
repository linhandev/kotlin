// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -UNNECESSARY_SAFE_CALL
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, navigation-operators -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 26 -> sentence 26
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 26 -> sentence 26
 *                declarations, property-declaration, late-initialized-properties -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: lateinit property has non-null type String, safe call on non-null receiver promotes to nullable Int?
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class C {
    lateinit var s: String
}

fun case1(c: C) {
    checkSubtype<Int?>(c.s?.length)
}
