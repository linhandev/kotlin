// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: type-system, type-kinds, flexible-types, platform-types -> paragraph 17 -> sentence 17
 *                type-system, type-kinds, nullable-types -> paragraph 17 -> sentence 17
 *                expressions, elvis-operator-expressions -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: platform type can be assigned to Kotlin String when null is handled with Elvis
 */

// TESTCASE NUMBER: 1
fun test56217(key: String): String = System.getProperty(key) ?: "fallback"

fun box(): String {
    if (test56217("no.such.prop.56217") != "fallback") return "NOK"
    val home = test56217("java.home")
    if (home.isEmpty() || home == "fallback") return "NOK"
    return "OK"
}
