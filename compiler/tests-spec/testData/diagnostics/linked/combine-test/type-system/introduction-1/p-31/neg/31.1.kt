// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 31 -> sentence 31
 * PRIMARY LINKS: type-system, type-kinds, classifier-types, parameterized-classifier-types -> paragraph 31 -> sentence 31
 *                inheritance, inheriting -> paragraph 31 -> sentence 31
 *                overload-resolution, building-the-overload-candidate-set-ocs, call-without-an-explicit-receiver -> paragraph 31 -> sentence 31
 * NUMBER: 1
 * DESCRIPTION: abstract base that implements only f(List<Int>) still leaves f(List<String>) abstract for subclasses
 */

interface Host56231 {
    fun f56231(x: List<Int>): Int
    fun f56231(x: List<String>): Int
}

abstract class Base56231 : Host56231 {
    override fun f56231(x: List<Int>) = 1
}

// TESTCASE NUMBER: 1
<!ABSTRACT_CLASS_MEMBER_NOT_IMPLEMENTED!>class Host56231Impl<!> : Base56231()
