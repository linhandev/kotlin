// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 42 -> sentence 42
 * PRIMARY LINKS: declarations, classifier-declaration, value-class-declaration -> paragraph 42 -> sentence 42
 *                type-system, type-kinds, type-parameters -> paragraph 42 -> sentence 42
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 42 -> sentence 42
 * NUMBER: 1
 * DESCRIPTION: Holder parameterized by a value class still cannot use non-reified is T on members
 */

@JvmInline
value class UserId56242(val raw: Int)

// TESTCASE NUMBER: 1
class Holder56242<T> {
    fun matches56242(x: Any): Boolean = x is <!CANNOT_CHECK_FOR_ERASED!>T<!>
}

fun case_1(): Boolean = Holder56242<UserId56242>().matches56242(UserId56242(1))
