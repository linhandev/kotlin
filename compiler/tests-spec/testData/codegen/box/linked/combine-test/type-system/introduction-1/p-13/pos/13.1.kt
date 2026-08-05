// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 13 -> sentence 13
 *                declarations, declarations-with-type-parameters, reified-type-parameters -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: inline reified filterIsInstance uses erased class information to filter
 */

// TESTCASE NUMBER: 1
inline fun <reified T> only56213(xs: List<Any>): List<T> = xs.filterIsInstance<T>()

fun box(): String {
    val r = only56213<Int>(listOf(1, "a", 2))
    if (r != listOf(1, 2)) return "NOK"
    return "OK"
}
