// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 28 -> sentence 28
 * PRIMARY LINKS: runtime-type-information, runtime-available-types -> paragraph 28 -> sentence 28
 *                type-system, introduction-1, type-kinds -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: star projection type List<*> is a runtime-available type and is-check works correctly at runtime
 */

// TESTCASE NUMBER: 1
fun isStarProjectedList(value: Any?): Boolean = value is List<*>

fun box(): String {
    if (!isStarProjectedList(listOf(1, 2, 3))) return "NOK"
    if (!isStarProjectedList(emptyList<String>())) return "NOK"
    if (isStarProjectedList("hello")) return "NOK"
    if (isStarProjectedList(42)) return "NOK"
    if (isStarProjectedList(null)) return "NOK"
    return "OK"
}
