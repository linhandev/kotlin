// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: runtime-type-information, runtime-available-types -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: reified type parameters inline to runtime-available types for is checks
 */
// TESTCASE NUMBER: 1

inline fun <reified T> isInstance1513(value: Any?): Boolean = value is T

fun box(): String {
    if (!isInstance1513<String>("ok")) return "NOK String"
    if (isInstance1513<Int>("ok")) return "NOK Int"
    return "OK"
}
