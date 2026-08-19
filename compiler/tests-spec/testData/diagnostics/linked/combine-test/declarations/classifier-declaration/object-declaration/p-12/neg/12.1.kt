// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, object-declaration -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: object override return type must match generic interface type argument
 */

// TESTCASE NUMBER: 1
interface Box<T> {
    fun get(): T
}

object Bad : Box<String> {
    override fun get(): <!RETURN_TYPE_MISMATCH_ON_OVERRIDE!>Int<!> = 1
}

fun case_1() = Bad.get()
