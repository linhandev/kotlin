// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, object-declaration -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 11 -> sentence 11
 *                declarations, declarations-with-type-parameters -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: object implementing generic interface matches type arguments
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Box<T> {
    fun get(): T
}

object StrBox : Box<String> {
    override fun get(): String = "a"
}

fun case_1() {
    checkSubtype<String>(StrBox.get())
}
