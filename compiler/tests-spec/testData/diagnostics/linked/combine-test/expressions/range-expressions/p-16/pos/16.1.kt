// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, range-expressions -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 16 -> sentence 16
 *                expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: extension operator rangeTo in infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class Tag(val id: Int)

operator fun Tag.rangeTo(o: Tag): IntRange = id..o.id

fun case1() {
    checkSubtype<Boolean>(2 in Tag(1)..Tag(3))
}
