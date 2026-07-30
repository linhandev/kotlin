// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 209 -> sentence 209
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 209 -> sentence 209
 *                inheritance, overriding -> paragraph 209 -> sentence 209
 * NUMBER: 1
 * DESCRIPTION: class declaration inheriting one interface default and one abstract same-named member still requires explicit override (MANY_INTERFACES_MEMBER_NOT_IMPLEMENTED); covers fun/String/val and reversed interface order; contrasts with p-206 dual-default conflict and with later override + super resolution of a single default
 */

// TESTCASE NUMBER: 1
interface DefaultFun {
    fun f(): Int = 1
}

interface AbstractFun {
    fun f(): Int
}

<!MANY_INTERFACES_MEMBER_NOT_IMPLEMENTED!>class InheritDefaultFun<!> : DefaultFun, AbstractFun

<!MANY_INTERFACES_MEMBER_NOT_IMPLEMENTED!>class InheritDefaultFunReversed<!> : AbstractFun, DefaultFun

// TESTCASE NUMBER: 2
interface DefaultTag {
    fun tag(): String = "D"
}

interface AbstractTag {
    fun tag(): String
}

<!MANY_INTERFACES_MEMBER_NOT_IMPLEMENTED!>class InheritDefaultTag<!> : DefaultTag, AbstractTag

// TESTCASE NUMBER: 3
interface DefaultVal {
    val n: Int get() = 7
}

interface AbstractVal {
    val n: Int
}

<!MANY_INTERFACES_MEMBER_NOT_IMPLEMENTED!>class InheritDefaultVal<!> : DefaultVal, AbstractVal
