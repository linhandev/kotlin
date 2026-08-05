// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 387 -> sentence 387
 * declarations, declaration-visibility -> paragraph 387 -> sentence 387
 * declarations, function-declaration -> paragraph 387 -> sentence 387
 * declarations, function-declaration, extension-function-declaration -> paragraph 387 -> sentence 387
 * NUMBER: 1
 * DESCRIPTION: 扩展函数不能调用接收者 private fun
 */

// TESTCASE NUMBER: 1
class C { private fun secret(): Int = 1 }
fun C.expose(): Int = <!INVISIBLE_MEMBER!>secret<!>()
fun test() = C().expose()
