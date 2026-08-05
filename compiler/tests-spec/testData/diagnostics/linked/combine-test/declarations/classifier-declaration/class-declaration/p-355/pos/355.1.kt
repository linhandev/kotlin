// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 355 -> sentence 355
 * declarations, declaration-visibility -> paragraph 355 -> sentence 355
 * declarations, property-declaration -> paragraph 355 -> sentence 355
 * declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 355 -> sentence 355
 * NUMBER: 1
 * DESCRIPTION: 主构造 private val 生成私有属性 type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class User(private val id: Int) { fun get(): Int = id }

// TESTCASE NUMBER: 1
fun test(): Int = User(1).get()

fun case1() {
    checkSubtype<Int>(test())
}
