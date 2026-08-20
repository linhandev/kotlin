// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 46 -> sentence 46
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 46 -> sentence 46
 * NUMBER: 1
 * DESCRIPTION: final class as upper bound is legal
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Final

class Wrap<T : <!FINAL_UPPER_BOUND!>Final<!>>(val t: T)

fun test(): Final = Wrap(Final()).t

fun case1() {
    checkSubtype<Final>(test())
}
