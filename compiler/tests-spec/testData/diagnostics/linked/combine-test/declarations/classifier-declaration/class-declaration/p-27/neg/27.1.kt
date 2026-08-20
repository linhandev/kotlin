// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 27 -> sentence 27
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: type parameter cannot be constructed via T()
 */

// TESTCASE NUMBER: 1
class Factory<T> { fun create(): T = <!RESOLUTION_TO_CLASSIFIER!>T<!>() }
