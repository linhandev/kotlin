// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, class-literals -> paragraph 6 -> sentence 6
 *                type-system, type-kinds, flexible-types, platform-types -> paragraph 6 -> sentence 6
 *                type-system, introduction-1 -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: Java ArrayList class literals with different type arguments collapse to the same erased runtime class
 */

// TESTCASE NUMBER: 1
fun box(): String {
    if (java.util.ArrayList<String>()::class != java.util.ArrayList<Int>()::class) return "NOK"
    if (java.util.ArrayList<String>()::class != java.util.ArrayList::class) return "NOK"
    if (java.util.ArrayList<Int>()::class.simpleName != "ArrayList") return "NOK"
    return "OK"
}
