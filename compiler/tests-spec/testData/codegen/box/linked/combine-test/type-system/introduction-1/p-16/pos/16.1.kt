// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: type-system, type-kinds, flexible-types, platform-types -> paragraph 16 -> sentence 16
 *                type-system, type-kinds, nullable-types -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: Java System.getProperty platform type can be assigned to String?
 */

// TESTCASE NUMBER: 1
fun test56216(key: String): String? = System.getProperty(key)

fun box(): String {
    val missing: String? = test56216("any.key.56216.missing")
    if (missing != null) return "NOK"
    val present: String? = test56216("java.home")
    if (present == null || present.isEmpty()) return "NOK"
    return "OK"
}
