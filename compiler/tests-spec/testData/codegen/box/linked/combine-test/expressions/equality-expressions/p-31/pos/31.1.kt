// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, equality-expressions -> paragraph 31 -> sentence 31
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 31 -> sentence 31
 * NUMBER: 1
 * DESCRIPTION: default Int? param and List boxed element equal literal 1
 */

// TESTCASE NUMBER: 1
fun withDefault(n: Int? = 1): Boolean = n == 1

fun fromList(xs: List<Int?>): Boolean = xs.first() == 1

fun box(): String {
    if (!withDefault()) return "NOK"
    if (!withDefault(1)) return "NOK"
    if (withDefault(null)) return "NOK"
    if (!fromList(listOf(1, null))) return "NOK"
    if (fromList(listOf(null, 1))) return "NOK"
    return "OK"
}
