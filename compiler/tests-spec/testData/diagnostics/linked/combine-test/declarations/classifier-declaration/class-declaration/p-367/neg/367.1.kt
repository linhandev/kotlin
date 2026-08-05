// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 367 -> sentence 367
 * declarations, declaration-visibility -> paragraph 367 -> sentence 367
 * declarations, property-declaration -> paragraph 367 -> sentence 367
 * NUMBER: 1
 * DESCRIPTION: public val + private set 类外赋值失败
 */

// TESTCASE NUMBER: 1
class Counter { var count: Int = 0; private set }
fun test() { <!INVISIBLE_SETTER!>Counter().count<!> = 2 }
