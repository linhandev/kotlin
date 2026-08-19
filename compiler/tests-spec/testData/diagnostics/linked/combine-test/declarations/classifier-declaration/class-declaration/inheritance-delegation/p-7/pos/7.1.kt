// WITH_STDLIB
// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: declarations, property-declaration, delegated-property-declaration -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: lazy Int property delegate
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box {
    val x: Int by lazy { 42 }
}

fun case_1() {
    checkSubtype<Int>(Box().x)
}
