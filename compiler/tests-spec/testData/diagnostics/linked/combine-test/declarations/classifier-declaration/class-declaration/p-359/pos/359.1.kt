// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 359 -> sentence 359
 * declarations, declaration-visibility -> paragraph 359 -> sentence 359
 * declarations, property-declaration -> paragraph 359 -> sentence 359
 * declarations, classifier-declaration, classifier-initialization -> paragraph 359 -> sentence 359
 * NUMBER: 1
 * DESCRIPTION: init 块可读取主构造 private val type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class C(private val seed: Int) { val doubled: Int; init { doubled = seed * 2 }; fun test(): Int = doubled }

// TESTCASE NUMBER: 1
fun test(): Int = C(3).test()

fun case1() {
    checkSubtype<Int>(test())
}
