// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: type-system, type-kinds, flexible-types, platform-types -> paragraph 22 -> sentence 22
 *                type-inference, introduction-1 -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: Java Arrays.asList is inferred as a List with platform element types
 */

// TESTCASE NUMBER: 1
fun test56222(): Any = java.util.Arrays.asList("a", "b").first()

fun box(): String {
    if (test56222() != "a") return "NOK"
    return "OK"
}
