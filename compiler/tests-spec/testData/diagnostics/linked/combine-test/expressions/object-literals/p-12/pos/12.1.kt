// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, object-literals -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: generic interface object literal with explicit type argument
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Box<T> {
    fun get(): T
}

fun case_1(): String = object : Box<String> {
    override fun get(): String = "a"
}.get()

fun case_1_check() {
    checkSubtype<String>(case_1())
}
