// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 373 -> sentence 373
 * declarations, declaration-visibility -> paragraph 373 -> sentence 373
 * declarations, property-declaration -> paragraph 373 -> sentence 373
 * declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 373 -> sentence 373
 * NUMBER: 1
 * DESCRIPTION: 次构造器可通过 this 访问主构造 private val type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class User(private val id: Int) { constructor(tag: String): this(tag.hashCode()); fun get(): Int = id }

// TESTCASE NUMBER: 1
fun test(): Int = User("a").get()

fun case1() {
    checkSubtype<Int>(test())
}
