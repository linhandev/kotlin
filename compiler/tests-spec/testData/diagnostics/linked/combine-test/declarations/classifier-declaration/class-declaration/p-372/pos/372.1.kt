// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 372 -> sentence 372
 * declarations, declaration-visibility -> paragraph 372 -> sentence 372
 * declarations, property-declaration -> paragraph 372 -> sentence 372
 * declarations, classifier-declaration, data-class-declaration -> paragraph 372 -> sentence 372
 * NUMBER: 1
 * DESCRIPTION: data class 的 private val 不参与自动生成的对外 copy 参数暴露 type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class User(private val id: Int, val name: String)

// TESTCASE NUMBER: 1
fun test(): String = User(1, "A").name

fun case1() {
    checkSubtype<String>(test())
}
