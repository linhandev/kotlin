// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 26 -> sentence 26
 *                declarations, classifier-declaration, companion-object -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: companion factory function with type parameter constructs Holder
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Holder<T>(val v: T) { companion object { fun <T> of(v: T): Holder<T> = Holder(v) } }

fun test(): Holder<Int> = Holder.of(1)

fun case1() {
    checkSubtype<Holder<Int>>(test())
}
