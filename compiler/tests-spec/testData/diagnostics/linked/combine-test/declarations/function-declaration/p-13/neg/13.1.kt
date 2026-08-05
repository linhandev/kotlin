// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, function-declaration -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 13 -> sentence 13
 *                expressions, call-expressions -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: 类外不能调用成员函数内的局部函数
 */

// TESTCASE NUMBER: 1
class C {
    fun work(): Int {
        fun helper(): Int = 1
        return helper()
    }
}

fun test(): Int = <!UNRESOLVED_REFERENCE!>helper<!>()
