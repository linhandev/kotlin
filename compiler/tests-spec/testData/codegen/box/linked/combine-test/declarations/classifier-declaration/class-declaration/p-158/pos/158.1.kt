// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 158 -> sentence 158
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 158 -> sentence 158
 *                declarations, declaration-visibility -> paragraph 158 -> sentence 158
 *                inheritance, overriding -> paragraph 158 -> sentence 158
 * NUMBER: 1
 * DESCRIPTION: protected superclass members are visible to subclasses (including overriding and multi-level inheritance) but not to external callers in class declaration
 */

// TESTCASE NUMBER: 1
open class Base(seed: Int) {
    protected val token: Int = seed * 2
}

class Child(seed: Int) : Base(seed) {
    fun reveal(): Int = token
}

// TESTCASE NUMBER: 2
open class Repo {
    protected open fun key(): String = "base"
    fun publicKey(): String = key()
}

class NamedRepo : Repo() {
    override fun key(): String = "named"
}

// TESTCASE NUMBER: 3
open class GrandParent {
    protected val g: Int = 1
}

open class Parent : GrandParent() {
    protected fun combine(): Int = g + 10
}

class Kid : Parent() {
    fun get(): Int = combine() + g
}

fun box(): String {
    if (Child(3).reveal() != 6) return "NOK: child-reveal"
    if (Child(0).reveal() != 0) return "NOK: child-reveal-zero"

    if (NamedRepo().publicKey() != "named") return "NOK: named-key"
    if (Repo().publicKey() != "base") return "NOK: base-key"
    if ((NamedRepo() as Repo).publicKey() != "named") return "NOK: named-as-repo"

    if (Kid().get() != 12) return "NOK: kid-get"
    return "OK"
}
