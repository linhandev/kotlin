// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: type-system, type-kinds, flexible-types, platform-types -> paragraph 18 -> sentence 18
 *                type-system, type-kinds, nullable-types -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: passing a null platform type into a non-null Kotlin parameter may NPE at runtime
 */

// TESTCASE NUMBER: 1
fun need56218(s: String): Int = s.length

fun box(): String {
    return try {
        need56218(System.getProperty("no.such.prop.abc.56218"))
        "NOK"
    } catch (e: NullPointerException) {
        "OK"
    }
}
