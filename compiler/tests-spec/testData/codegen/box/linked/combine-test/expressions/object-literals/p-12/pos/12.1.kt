
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, object-literals -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: generic interface object literal with explicit type argument
 */

// TESTCASE NUMBER: 1
interface Box<T> {
    fun get(): T
}

fun test(): String = object : Box<String> {
    override fun get(): String = "a"
}.get()

fun box(): String {
    if (test() != "a") return "NOK"
    return "OK"
}
