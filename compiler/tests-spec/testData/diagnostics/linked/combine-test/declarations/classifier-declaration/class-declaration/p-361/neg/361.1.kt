// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 361 -> sentence 361
 * declarations, declaration-visibility -> paragraph 361 -> sentence 361
 * declarations, property-declaration -> paragraph 361 -> sentence 361
 * inheritance, inheriting -> paragraph 361 -> sentence 361
 * NUMBER: 1
 * DESCRIPTION: 子类不能访问父类 private val
 */

// TESTCASE NUMBER: 1
open class Base { private val token = 1 }
class Sub : Base() { fun read(): Int = <!INVISIBLE_MEMBER!>token<!> }
fun test() = Sub().read()
