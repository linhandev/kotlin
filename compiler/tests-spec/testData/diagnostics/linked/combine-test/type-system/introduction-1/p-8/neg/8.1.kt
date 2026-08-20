// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: type-system, type-kinds, classifier-types, parameterized-classifier-types -> paragraph 8 -> sentence 8
 *                inheritance, inheriting -> paragraph 8 -> sentence 8
 *                overload-resolution, building-the-overload-candidate-set-ocs, call-without-an-explicit-receiver -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: inheriting two interfaces with List type-arg overloads requires both overrides; implementing only one reports ABSTRACT_MEMBER_NOT_IMPLEMENTED
 */

interface A56208 {
    fun f56208(x: List<Int>)
}

interface B56208 {
    fun f56208(x: List<String>)
}

// TESTCASE NUMBER: 1
<!ABSTRACT_MEMBER_NOT_IMPLEMENTED!>class C56208Missing<!> : A56208, B56208

<!ABSTRACT_MEMBER_NOT_IMPLEMENTED!>class C56208OnlyInt<!> : A56208, B56208 {
    override fun f56208(x: List<Int>) {}
}
