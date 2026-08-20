// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 268 -> sentence 268
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 268 -> sentence 268
 * NUMBER: 1
 * DESCRIPTION: protected members are invisible outside the declaring class hierarchy (INVISIBLE_REFERENCE); covers body property, member function, and primary-constructor property; contrasts with p-267 subclass success and with declaration-visibility p-5
 */

// TESTCASE NUMBER: 1
open class TokenBase {
    protected val token = 1
}

fun case1(b: TokenBase = TokenBase()): Int = b.<!INVISIBLE_REFERENCE!>token<!>

// TESTCASE NUMBER: 2
open class CodeBase {
    protected fun code(): Int = 2
}

fun case2(b: CodeBase = CodeBase()): Int = b.<!INVISIBLE_REFERENCE!>code<!>()

// TESTCASE NUMBER: 3
open class LabelBase(protected val label: String)

fun case3(b: LabelBase = LabelBase("x")): String = b.<!INVISIBLE_REFERENCE!>label<!>
