// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 33 -> sentence 33
 * PRIMARY LINKS: declarations, classifier-declaration, value-class-declaration -> paragraph 33 -> sentence 33
 *                expressions, cast-expressions -> paragraph 33 -> sentence 33
 *                expressions, when-expressions -> paragraph 33 -> sentence 33
 *                type-system, introduction-1 -> paragraph 33 -> sentence 33
 * NUMBER: 1
 * DESCRIPTION: value class boxed as Any cast back then when reads raw type inference
 * HELPERS: checkType
 */

@JvmInline
value class UserId56233(val raw: Int)

// TESTCASE NUMBER: 1
fun case_1() {
    val a: Any = UserId56233(7)
    val id = a as UserId56233
    checkSubtype<Int>(when {
        id.raw > 0 -> id.raw
        else -> -1
    })
}
