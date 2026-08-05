// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -INCOMPATIBLE_MODIFIERS
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 401 -> sentence 401
 * declarations, declaration-visibility -> paragraph 401 -> sentence 401
 * declarations, function-declaration -> paragraph 401 -> sentence 401
 * NUMBER: 1
 * DESCRIPTION: 嵌套类不能实现声明类 private abstract fun
 */

// TESTCASE NUMBER: 1
abstract class A {
    private abstract fun core(): Int
    private class Impl : A() {
        <!CANNOT_OVERRIDE_INVISIBLE_MEMBER!>override<!> fun core(): Int = 1
    }
    companion object { fun run(): Int = Impl().core() }
}

// TESTCASE NUMBER: 1
fun test(): Int = A.run()
