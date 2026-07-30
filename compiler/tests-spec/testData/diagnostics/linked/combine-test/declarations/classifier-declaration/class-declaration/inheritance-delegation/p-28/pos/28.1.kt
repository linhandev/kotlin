// WITH_STDLIB
// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 28 -> sentence 28
 * PRIMARY LINKS: declarations, property-declaration, delegated-property-declaration -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: local delegated property inside run lambda
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun test() = run {
    val x: Int by lazy { 42 }
    x
}

fun case_1() {
    checkSubtype<Int>(test())
}
