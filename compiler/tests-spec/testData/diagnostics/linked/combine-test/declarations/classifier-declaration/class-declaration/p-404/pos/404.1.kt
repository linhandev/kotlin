// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 404 -> sentence 404
 * declarations, declaration-visibility -> paragraph 404 -> sentence 404
 * declarations, function-declaration -> paragraph 404 -> sentence 404
 * NUMBER: 1
 * DESCRIPTION: 类内匿名对象可以访问外部类 private fun type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Host {
    private fun secret(): Int = 1
    fun run(): Int = object { fun go(): Int = secret() }.go()
}

// TESTCASE NUMBER: 1
fun test(): Int = Host().run()

fun case1() {
    checkSubtype<Int>(test())
}
