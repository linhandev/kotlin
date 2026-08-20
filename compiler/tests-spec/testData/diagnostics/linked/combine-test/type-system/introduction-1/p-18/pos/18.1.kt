// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: type-system, type-kinds, flexible-types, platform-types -> paragraph 18 -> sentence 18
 *                type-system, type-kinds, nullable-types -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: passing a null platform type into a non-null Kotlin parameter may NPE at runtime type inference
 * HELPERS: checkType
 */

fun need56218(s: String): Int = s.length

// TESTCASE NUMBER: 1
fun case_1() {
    // platform type from getProperty is accepted by non-null String at compile time
    checkSubtype<Int>(need56218(System.getProperty("user.name") ?: "x"))
}
