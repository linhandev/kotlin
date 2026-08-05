// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 383 -> sentence 383
 * declarations, declaration-visibility -> paragraph 383 -> sentence 383
 * declarations, function-declaration -> paragraph 383 -> sentence 383
 * inheritance, inheriting -> paragraph 383 -> sentence 383
 * NUMBER: 1
 * DESCRIPTION: 子类不能调用父类 private fun
 */

// TESTCASE NUMBER: 1
open class Base { private fun token(): Int = 1 }
class Sub : Base() { fun read(): Int = <!INVISIBLE_MEMBER!>token<!>() }
fun test() = Sub().read()
