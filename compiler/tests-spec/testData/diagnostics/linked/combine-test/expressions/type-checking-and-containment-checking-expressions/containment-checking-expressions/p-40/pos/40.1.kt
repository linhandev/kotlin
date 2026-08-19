// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 40 -> sentence 40
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 40 -> sentence 40
 *                type-system, introduction-1 -> paragraph 40 -> sentence 40
 * NUMBER: 1
 * DESCRIPTION: custom Iterable with member operator contains supports in operator and infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class MyColl(private val data: List<Int>) : Iterable<Int> {
    override fun iterator(): Iterator<Int> = data.iterator()
    operator fun contains(x: Int): Boolean = x in data
}

fun case1() {
    checkSubtype<Boolean>(2 in MyColl(listOf(1, 2)))
    checkSubtype<Boolean>(3 in MyColl(listOf(1, 2)))
}
