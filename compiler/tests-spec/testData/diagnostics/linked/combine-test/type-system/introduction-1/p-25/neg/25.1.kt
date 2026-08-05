// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, class-literals -> paragraph 25 -> sentence 25
 *                expressions, when-expressions -> paragraph 25 -> sentence 25
 *                type-system, type-kinds, type-parameters -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: generic class member cannot use non-reified T::class inside when against other::class
 */

// TESTCASE NUMBER: 1
class Box56225<T> {
    fun sameClass56225(other: Any): Boolean = when {
        other::class == <!TYPE_PARAMETER_AS_REIFIED!>T::class<!> -> true
        else -> false
    }
}
