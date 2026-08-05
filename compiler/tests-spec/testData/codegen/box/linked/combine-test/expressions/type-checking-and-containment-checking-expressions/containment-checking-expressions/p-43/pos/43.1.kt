// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 43 -> sentence 43
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 43 -> sentence 43
 *                declarations, function-declaration, extension-function-declaration -> paragraph 43 -> sentence 43
 *                type-system, introduction-1 -> paragraph 43 -> sentence 43
 * NUMBER: 1
 * DESCRIPTION: extension contains on smart-cast non-null nullable Box receiver at runtime
 */

// TESTCASE NUMBER: 1
class Box(val ok: Boolean)

operator fun Box.contains(x: Int): Boolean = ok

fun test(b: Box?): Boolean = if (b != null) 1 in b else false

fun box(): String {
    if (test(null)) return "NOK: null receiver must not use in"
    if (!test(Box(true))) return "NOK: smart-cast receiver with ok=true"
    if (test(Box(false))) return "NOK: smart-cast receiver with ok=false"
    return "OK"
}
