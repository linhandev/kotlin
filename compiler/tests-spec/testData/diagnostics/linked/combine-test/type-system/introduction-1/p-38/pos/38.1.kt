// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 38 -> sentence 38
 * PRIMARY LINKS: declarations, classifier-declaration, value-class-declaration -> paragraph 38 -> sentence 38
 *                type-system, type-kinds, type-parameters -> paragraph 38 -> sentence 38
 *                type-system, introduction-1 -> paragraph 38 -> sentence 38
 *                expressions, when-expressions -> paragraph 38 -> sentence 38
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 38 -> sentence 38
 * NUMBER: 1
 * DESCRIPTION: List<*> of value-class elements refined by when-is back to value class type inference
 * HELPERS: checkType
 */

@JvmInline
value class UserId56238(val raw: Int)

// TESTCASE NUMBER: 1
fun case_1() {
    val xs: List<*> = listOf(UserId56238(1))
    checkSubtype<Int>(when (val e = xs.first()) {
        is UserId56238 -> e.raw
        else -> -1
    })
}
