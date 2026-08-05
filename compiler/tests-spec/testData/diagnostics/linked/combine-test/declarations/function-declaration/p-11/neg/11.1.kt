// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -NO_VALUE_FOR_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, function-declaration -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 11 -> sentence 11
 *                expressions, call-expressions -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: 调用局部函数时命名实参之后不能再跟位置实参
 */

// TESTCASE NUMBER: 1
fun outer(): Int {
    fun g(a: Int, b: Int, c: Int): Int = a + b + c
    return g(c = 1, <!MIXING_NAMED_AND_POSITIONED_ARGUMENTS!>2<!>, <!MIXING_NAMED_AND_POSITIONED_ARGUMENTS!>3<!>)
}

fun test() = outer()
