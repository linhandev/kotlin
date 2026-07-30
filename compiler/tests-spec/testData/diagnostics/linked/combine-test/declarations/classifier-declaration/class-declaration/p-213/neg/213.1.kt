// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 213 -> sentence 213
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 213 -> sentence 213
 *                inheritance, overriding -> paragraph 213 -> sentence 213
 * NUMBER: 1
 * DESCRIPTION: final (non-open) class member body conflicting with interface default cannot be left unresolved (MANY_IMPL_MEMBER_NOT_IMPLEMENTED) and cannot be overridden (OVERRIDING_FINAL_MEMBER); covers fun/String/val and reversed order; contrasts with p-212 open class member + qualified super
 */

// TESTCASE NUMBER: 1
open class BaseFun {
    fun f(): Int = 0
}

interface IfaceFun {
    fun f(): Int = 1
}

<!MANY_IMPL_MEMBER_NOT_IMPLEMENTED!>class ConflictFun<!> : BaseFun(), IfaceFun

<!MANY_IMPL_MEMBER_NOT_IMPLEMENTED!>class ConflictFunReversed<!> : IfaceFun, BaseFun()

class TryOverrideFun : BaseFun(), IfaceFun {
    <!OVERRIDING_FINAL_MEMBER!>override<!> fun f(): Int = 2
}

// TESTCASE NUMBER: 2
open class BaseTag {
    fun tag(): String = "B"
}

interface IfaceTag {
    fun tag(): String = "I"
}

<!MANY_IMPL_MEMBER_NOT_IMPLEMENTED!>class ConflictTag<!> : BaseTag(), IfaceTag

class TryOverrideTag : BaseTag(), IfaceTag {
    <!OVERRIDING_FINAL_MEMBER!>override<!> fun tag(): String = "X"
}

// TESTCASE NUMBER: 3
open class BaseVal {
    val n: Int = 2
}

interface IfaceVal {
    val n: Int get() = 3
}

<!MANY_IMPL_MEMBER_NOT_IMPLEMENTED!>class ConflictVal<!> : BaseVal(), IfaceVal

class TryOverrideVal : BaseVal(), IfaceVal {
    <!OVERRIDING_FINAL_MEMBER!>override<!> val n: Int
        get() = 9
}
