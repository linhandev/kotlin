// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 38 -> sentence 38
 * PRIMARY LINKS: declarations, classifier-declaration, value-class-declaration -> paragraph 38 -> sentence 38
 *                type-system, type-kinds, type-parameters -> paragraph 38 -> sentence 38
 *                type-system, introduction-1 -> paragraph 38 -> sentence 38
 *                expressions, when-expressions -> paragraph 38 -> sentence 38
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 38 -> sentence 38
 * NUMBER: 1
 * DESCRIPTION: List<*> of value-class elements refined by when-is back to value class
 */

// TESTCASE NUMBER: 1
@JvmInline
value class UserId56238(val raw: Int)

fun test56238(): Int {
    val xs: List<*> = listOf(UserId56238(1))
    return when (val e = xs.first()) {
        is UserId56238 -> e.raw
        else -> -1
    }
}

fun box(): String {
    if (test56238() != 1) return "NOK"
    return "OK"
}
