// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 37 -> sentence 37
 * PRIMARY LINKS: declarations, classifier-declaration, value-class-declaration -> paragraph 37 -> sentence 37
 *                type-system, type-kinds, classifier-types, parameterized-classifier-types -> paragraph 37 -> sentence 37
 *                type-system, introduction-1 -> paragraph 37 -> sentence 37
 * NUMBER: 1
 * DESCRIPTION: List of value class elements unpacks correctly after generic storage
 */

// TESTCASE NUMBER: 1
@JvmInline
value class UserId56237(val raw: Int)

fun test56237(): Int {
    val xs: List<UserId56237> = listOf(UserId56237(3))
    return xs.first().raw
}

fun box(): String {
    if (test56237() != 3) return "NOK"
    return "OK"
}
