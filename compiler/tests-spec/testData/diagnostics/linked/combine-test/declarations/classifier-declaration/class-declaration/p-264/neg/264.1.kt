// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 264 -> sentence 264
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 264 -> sentence 264
 * NUMBER: 1
 * DESCRIPTION: protected cannot modify a top-level class (WRONG_MODIFIER_CONTAINING_DECLARATION); contrasts with p-263 private top-level success and with protected members nested inside classes
 */

// TESTCASE NUMBER: 1
<!WRONG_MODIFIER_CONTAINING_DECLARATION!>protected<!> class Bad

// TESTCASE NUMBER: 2
<!WRONG_MODIFIER_CONTAINING_DECLARATION!>protected<!> open class BadOpen

// TESTCASE NUMBER: 3
<!WRONG_MODIFIER_CONTAINING_DECLARATION!>protected<!> class BadToken(val code: Int)
