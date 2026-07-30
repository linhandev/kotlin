// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 6 -> sentence 6
 *                declarations, function-declaration, extension-function-declaration -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: generic extension contains function is resolved by in operator at runtime
 */

// TESTCASE NUMBER: 1
class Box<T>(val items: Set<T>)

var extensionResolved = false

operator fun <T> Box<T>.contains(x: T): Boolean {
    extensionResolved = true
    return x in items
}

fun testInt(x: Int): Boolean = x in Box(setOf(1, 2, 3))
fun testString(x: String): Boolean = x in Box(setOf("a", "b"))

fun box(): String {
    extensionResolved = false
    if (!testInt(2)) return "NOK: Int extension contains"
    if (!extensionResolved) return "NOK: Int in did not resolve generic extension contains"
    extensionResolved = false
    if (testInt(9)) return "NOK: Int absent element"
    if (!extensionResolved) return "NOK: Int false branch did not resolve extension contains"
    extensionResolved = false
    if (!testString("a")) return "NOK: String extension contains"
    if (!extensionResolved) return "NOK: String in did not resolve generic extension contains"
    extensionResolved = false
    if (testString("z")) return "NOK: String absent element"
    if (!extensionResolved) return "NOK: String false branch did not resolve extension contains"
    return "OK"
}
