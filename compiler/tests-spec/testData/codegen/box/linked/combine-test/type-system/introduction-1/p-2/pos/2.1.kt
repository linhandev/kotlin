// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 2 -> sentence 2
 *                type-system, type-kinds, type-parameters -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: is-check against List<*> is a valid star-projection runtime type test
 */

// TESTCASE NUMBER: 1
fun test56202(x: Any): Int = if (x is List<*>) x.size else -1

fun box(): String {
    if (test56202(listOf(1, 2)) != 2) return "NOK"
    if (test56202("x") != -1) return "NOK"
    if (test56202(emptyList<String>()) != 0) return "NOK"
    return "OK"
}
