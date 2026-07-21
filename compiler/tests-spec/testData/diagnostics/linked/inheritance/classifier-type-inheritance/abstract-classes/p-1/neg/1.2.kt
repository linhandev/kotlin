// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: inheritance, classifier-type-inheritance, abstract-classes -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Partial511 missing bar and Partial511b missing foo report ABSTRACT_CLASS_MEMBER_NOT_IMPLEMENTED
 */

// TESTCASE NUMBER: 1
abstract class Base511 {
    abstract fun foo(): Int
    abstract val bar: String
}

<!ABSTRACT_CLASS_MEMBER_NOT_IMPLEMENTED!>class Partial511<!> : Base511() {
    override fun foo(): Int = 1
}

// TESTCASE NUMBER: 2
<!ABSTRACT_CLASS_MEMBER_NOT_IMPLEMENTED!>class Partial511b<!> : Base511() {
    override val bar: String = "x"
}
