// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -UNNECESSARY_SAFE_CALL
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, navigation-operators -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 19 -> sentence 19
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: safe call on non-null this still promotes result to nullable String? for member access
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class C {
    var p: String? = null
    fun f(): String? = this?.p
}

fun case1() {
    val c = C()
    c.p = "hello"
    checkSubtype<String?>(c.f())
}
