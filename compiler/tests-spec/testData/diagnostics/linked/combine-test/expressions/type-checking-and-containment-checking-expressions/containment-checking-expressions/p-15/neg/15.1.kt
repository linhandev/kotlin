// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: type-system, type-kinds, type-parameters -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: covariant out type parameter cannot appear in in-position of member contains used by in operator
 */

// TESTCASE NUMBER: 1
class Box<out T>(val items: List<T>) {
    operator fun contains(x: <!TYPE_VARIANCE_CONFLICT_ERROR!>T<!>): Boolean = x in items
}
