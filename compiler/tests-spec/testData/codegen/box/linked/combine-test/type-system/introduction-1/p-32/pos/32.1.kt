// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 32 -> sentence 32
 * PRIMARY LINKS: declarations, classifier-declaration, value-class-declaration -> paragraph 32 -> sentence 32
 *                type-system, introduction-1 -> paragraph 32 -> sentence 32
 *                type-system, type-kinds, type-parameters -> paragraph 32 -> sentence 32
 *                expressions, when-expressions -> paragraph 32 -> sentence 32
 * NUMBER: 1
 * DESCRIPTION: value class through generic id<T> then when on raw keeps value semantics
 */

// TESTCASE NUMBER: 1
@JvmInline
value class UserId56232(val raw: Int)

fun <T> id56232(x: T): T = x

fun test56232(x: UserId56232): Int {
    val v = id56232(x)
    return when {
        v.raw > 0 -> v.raw
        else -> -1
    }
}

fun box(): String {
    if (test56232(UserId56232(1)) != 1) return "NOK"
    if (id56232(UserId56232(2)) != UserId56232(2)) return "NOK"
    return "OK"
}
