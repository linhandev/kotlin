// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 23 -> sentence 23
 * PRIMARY LINKS: type-system, type-kinds, flexible-types, platform-types -> paragraph 23 -> sentence 23
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: Java HashMap mutable methods are callable on platform-typed receivers
 */

// TESTCASE NUMBER: 1
fun test56223(): Int {
    val m = java.util.HashMap<String, String>()
    m["k"] = "v"
    return m.size
}

fun box(): String {
    if (test56223() != 1) return "NOK"
    return "OK"
}
