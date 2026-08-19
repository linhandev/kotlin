// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 39 -> sentence 39
 * PRIMARY LINKS: declarations, classifier-declaration, value-class-declaration -> paragraph 39 -> sentence 39
 *                inheritance, inheriting -> paragraph 39 -> sentence 39
 *                type-system, introduction-1 -> paragraph 39 -> sentence 39
 * NUMBER: 1
 * DESCRIPTION: value class upcast to interface boxes but keeps behavior
 */

// TESTCASE NUMBER: 1
interface HasRaw56239 {
    fun raw56239(): Int
}

@JvmInline
value class UserId56239(val v: Int) : HasRaw56239 {
    override fun raw56239(): Int = v
}

fun test56239(): Int {
    val h: HasRaw56239 = UserId56239(9)
    return h.raw56239()
}

fun box(): String {
    if (test56239() != 9) return "NOK"
    return "OK"
}
