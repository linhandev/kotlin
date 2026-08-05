// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 24 -> sentence 24
 * PRIMARY LINKS: type-system, type-kinds, flexible-types, platform-types -> paragraph 24 -> sentence 24
 *                type-system, type-kinds, nullable-types -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: java.lang.Integer platform type can be assigned to Kotlin Int
 */

// TESTCASE NUMBER: 1
fun test56224(): Int = java.lang.Integer.valueOf(7)

fun box(): String {
    if (test56224() != 7) return "NOK"
    return "OK"
}
