// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, navigation-operators -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 19 -> sentence 19
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: safe call on non-null this still promotes result to nullable String? for member access
 */

// TESTCASE NUMBER: 1
class C {
    var p: String? = null
    fun f(): String? = this?.p
}

fun box(): String {
    val c = C()
    if (c.f() != null) return "NOK: default null property returns null"
    c.p = "hello"
    if (c.f() != "hello") return "NOK: set property returns value"
    return "OK"
}
