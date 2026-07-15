// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, upper-and-lower-bounds, least-upper-bound -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: type-system, type-kinds, union-types -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Conditional expressions infer least upper bound of branch types for class hierarchies
 * HELPERS: checkType
 */

open class Base1
class Derived1A : Base1()
class Derived1B : Base1()

// TESTCASE NUMBER: 1
fun case_1(flag: Boolean): Base1 = if (flag) Derived1A() else Derived1B()

// TESTCASE NUMBER: 2
fun case_2(flag: Boolean) {
    val x = if (flag) Derived1A() else Derived1B()
    checkSubtype<Base1>(x)
}
