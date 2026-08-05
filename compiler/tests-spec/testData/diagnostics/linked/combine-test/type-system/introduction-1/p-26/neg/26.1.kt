// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 26 -> sentence 26
 *                expressions, when-expressions -> paragraph 26 -> sentence 26
 *                type-system, type-kinds, type-parameters -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: generic class member cannot use is T inside a when branch due to erasure
 */

// TESTCASE NUMBER: 1
class Box56226<T> {
    fun rank56226(x: Any): Int = when (x) {
        is <!CANNOT_CHECK_FOR_ERASED!>T<!> -> 1
        else -> 0
    }
}
