// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, function-declaration -> paragraph 20 -> sentence 20
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 20 -> sentence 20
 *                declarations, declaration-visibility -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: 局部函数不能使用 private/public 等可见性修饰符
 */

// TESTCASE NUMBER: 1
fun outer(): Int {
    <!WRONG_MODIFIER_TARGET!>private<!> fun local(): Int = 1
    return local()
}

fun test() = outer()
