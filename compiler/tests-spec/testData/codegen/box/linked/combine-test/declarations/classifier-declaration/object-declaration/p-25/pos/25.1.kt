// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, object-declaration -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 25 -> sentence 25
 *                declarations, declaration-visibility -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: internal object can implement a public interface in the same module
 */

// TESTCASE NUMBER: 1
interface Pub {
    fun f(): Int
}

internal object Impl : Pub {
    override fun f(): Int = 1
}

fun test(): Int = Impl.f()

fun box(): String {
    if (test() != 1) return "NOK"
    return "OK"
}
