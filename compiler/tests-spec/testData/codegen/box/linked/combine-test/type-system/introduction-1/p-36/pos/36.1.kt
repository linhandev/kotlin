// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 36 -> sentence 36
 * PRIMARY LINKS: declarations, classifier-declaration, value-class-declaration -> paragraph 36 -> sentence 36
 *                type-system, type-kinds, flexible-types, platform-types -> paragraph 36 -> sentence 36
 *                type-system, type-kinds, nullable-types -> paragraph 36 -> sentence 36
 * NUMBER: 1
 * DESCRIPTION: value class through Any? plus Elvis unpacks correctly
 */

// TESTCASE NUMBER: 1
@JvmInline
value class UserId56236(val raw: Int)

fun test56236(x: Any?): Int = (x as? UserId56236)?.raw ?: -1

fun box(): String {
    if (test56236(UserId56236(4)) != 4) return "NOK"
    if (test56236(null) != -1) return "NOK"
    if (test56236("x") != -1) return "NOK"
    return "OK"
}
