// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, class-literals -> paragraph 6 -> sentence 6
 *                type-system, type-kinds, flexible-types, platform-types -> paragraph 6 -> sentence 6
 *                type-system, introduction-1 -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: Java ArrayList class literals with different type arguments collapse to the same erased runtime class type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<Boolean>(java.util.ArrayList<String>()::class == java.util.ArrayList<Int>()::class)
    checkSubtype<Boolean>(java.util.ArrayList<String>()::class == java.util.ArrayList::class)
    checkSubtype<String?>(java.util.ArrayList<Int>()::class.simpleName)
}
