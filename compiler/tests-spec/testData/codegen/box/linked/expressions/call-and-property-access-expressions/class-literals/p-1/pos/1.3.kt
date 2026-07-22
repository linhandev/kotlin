// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, call-and-property-access-expressions, class-literals -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: List::class and Map::class without type arguments name raw types
 */

// TESTCASE NUMBER: 1

fun box(): String {
    if (List::class.simpleName != "List") return "NOK"
    if (Map::class.simpleName != "Map") return "NOK"
    return "OK"
}
