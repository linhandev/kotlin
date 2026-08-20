// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 224 -> sentence 224
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 224 -> sentence 224
 *                inheritance, overriding -> paragraph 224 -> sentence 224
 * NUMBER: 1
 * DESCRIPTION: a sub-interface inheriting two conflicting defaults must override at the interface layer (MANY_INTERFACES_MEMBER_NOT_IMPLEMENTED); a class override of that sub-interface does not remove the interface-level error; contrasts with p-210 interface-level resolution and p-223 class-level override dispatch
 */

// TESTCASE NUMBER: 1
interface LeftFun {
    fun f(): Int = 1
}

interface RightFun {
    fun f(): Int = 2
}

<!MANY_INTERFACES_MEMBER_NOT_IMPLEMENTED!>interface BadFun<!> : LeftFun, RightFun

class ClassOverrideFun : BadFun {
    override fun f(): Int = 0
}

// TESTCASE NUMBER: 2
interface LeftTag {
    fun tag(): String = "L"
}

interface RightTag {
    fun tag(): String = "R"
}

<!MANY_INTERFACES_MEMBER_NOT_IMPLEMENTED!>interface BadTag<!> : LeftTag, RightTag

class ClassOverrideTag : BadTag {
    override fun tag(): String = "x"
}

// TESTCASE NUMBER: 3
interface LeftVal {
    val n: Int get() = 1
}

interface RightVal {
    val n: Int get() = 2
}

<!MANY_INTERFACES_MEMBER_NOT_IMPLEMENTED!>interface BadVal<!> : LeftVal, RightVal

class ClassOverrideVal : BadVal {
    override val n: Int get() = 0
}
