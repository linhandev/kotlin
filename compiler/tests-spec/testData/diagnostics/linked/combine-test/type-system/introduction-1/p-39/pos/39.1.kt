// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 39 -> sentence 39
 * PRIMARY LINKS: declarations, classifier-declaration, value-class-declaration -> paragraph 39 -> sentence 39
 *                inheritance, inheriting -> paragraph 39 -> sentence 39
 *                type-system, introduction-1 -> paragraph 39 -> sentence 39
 * NUMBER: 1
 * DESCRIPTION: value class upcast to interface boxes but keeps behavior type inference
 * HELPERS: checkType
 */

interface HasRaw56239 {
    fun raw56239(): Int
}

@JvmInline
value class UserId56239(val v: Int) : HasRaw56239 {
    override fun raw56239(): Int = v
}

// TESTCASE NUMBER: 1
fun case_1() {
    val h: HasRaw56239 = UserId56239(9)
    checkSubtype<Int>(h.raw56239())
}
