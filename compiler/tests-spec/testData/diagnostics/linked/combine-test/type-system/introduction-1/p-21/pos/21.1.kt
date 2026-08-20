// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: type-system, type-kinds, flexible-types, platform-types -> paragraph 21 -> sentence 21
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 21 -> sentence 21
 *                type-inference, smart-casts -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: is String smart cast applies to a value originating from a platform type type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val v: Any = System.getProperty("user.name") ?: ""
    checkSubtype<Int>(if (v is String) v.length else -1)
}
