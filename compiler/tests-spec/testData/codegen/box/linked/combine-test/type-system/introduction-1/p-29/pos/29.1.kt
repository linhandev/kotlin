// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 29 -> sentence 29
 * PRIMARY LINKS: expressions, cast-expressions -> paragraph 29 -> sentence 29
 *                type-system, type-kinds, flexible-types, platform-types -> paragraph 29 -> sentence 29
 * NUMBER: 1
 * DESCRIPTION: casting Array<Any?> to Array<String> may fail at runtime
 */

// TESTCASE NUMBER: 1
@Suppress("UNCHECKED_CAST")
fun test56229(): String = (arrayOf<Any?>(1) as Array<String>)[0]

fun box(): String {
    return try {
        test56229()
        "NOK"
    } catch (e: ClassCastException) {
        "OK"
    }
}
