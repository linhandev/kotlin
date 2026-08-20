// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 66 -> sentence 66
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 66 -> sentence 66
 * NUMBER: 1
 * DESCRIPTION: primary constructor default parameter may be omitted
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Config(val port: Int = 8080)

fun test(): Int = Config().port

fun case1() {
    checkSubtype<Int>(test())
}
