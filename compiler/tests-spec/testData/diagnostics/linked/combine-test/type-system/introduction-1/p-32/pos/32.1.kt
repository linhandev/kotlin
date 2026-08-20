// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 32 -> sentence 32
 * PRIMARY LINKS: declarations, classifier-declaration, value-class-declaration -> paragraph 32 -> sentence 32
 *                type-system, introduction-1 -> paragraph 32 -> sentence 32
 *                type-system, type-kinds, type-parameters -> paragraph 32 -> sentence 32
 *                expressions, when-expressions -> paragraph 32 -> sentence 32
 * NUMBER: 1
 * DESCRIPTION: value class through generic id<T> then when on raw keeps value semantics type inference
 * HELPERS: checkType
 */

@JvmInline
value class UserId56232(val raw: Int)

fun <T> id56232(x: T): T = x

// TESTCASE NUMBER: 1
fun case_1() {
    val v = id56232(UserId56232(1))
    checkSubtype<Int>(when {
        v.raw > 0 -> v.raw
        else -> -1
    })
}
