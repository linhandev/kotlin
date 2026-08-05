// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: type-system, type-kinds, flexible-types, platform-types -> paragraph 21 -> sentence 21
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 21 -> sentence 21
 *                type-inference, smart-casts -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: is String smart cast applies to a value originating from a platform type
 */

// TESTCASE NUMBER: 1
fun test56221(x: Any): Int = if (x is String) x.length else -1

fun box(): String {
    val fromPlatform: Any = System.getProperty("user.name") ?: ""
    val n = test56221(fromPlatform)
    if (n != (fromPlatform as String).length) return "NOK"
    if (test56221(42) != -1) return "NOK"
    if (test56221("abc") != 3) return "NOK"
    return "OK"
}
