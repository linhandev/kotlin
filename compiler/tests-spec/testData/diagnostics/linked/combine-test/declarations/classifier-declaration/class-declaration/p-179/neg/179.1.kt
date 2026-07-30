// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 179 -> sentence 179
 * PRIMARY LINKS: inheritance, overriding -> paragraph 179 -> sentence 179
 *                declarations, classifier-declaration, class-declaration, abstract-classes -> paragraph 179 -> sentence 179
 *                inheritance, inheriting -> paragraph 179 -> sentence 179
 * NUMBER: 1
 * DESCRIPTION: a concrete subclass that inherits abstract class members without overriding them is rejected (ABSTRACT_CLASS_MEMBER_NOT_IMPLEMENTED) in a class declaration
 */

// TESTCASE NUMBER: 1
abstract class Base {
    abstract fun f(): Int
}

<!ABSTRACT_CLASS_MEMBER_NOT_IMPLEMENTED!>class Child<!> : Base()

// TESTCASE NUMBER: 2
abstract class Holder {
    abstract val code: Int
}

<!ABSTRACT_CLASS_MEMBER_NOT_IMPLEMENTED!>class EmptyHolder<!> : Holder()

// TESTCASE NUMBER: 3
abstract class Dual {
    abstract fun left(): Int
    abstract fun right(): Int
}

<!ABSTRACT_CLASS_MEMBER_NOT_IMPLEMENTED!>class Partial<!> : Dual() {
    override fun left(): Int = 1
}
