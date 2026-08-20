// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 158 -> sentence 158
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 158 -> sentence 158
 *                declarations, declaration-visibility -> paragraph 158 -> sentence 158
 *                inheritance, overriding -> paragraph 158 -> sentence 158
 * NUMBER: 1
 * DESCRIPTION: type inference for protected superclass members accessed within subclasses across inheritance and overriding in class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
open class Base(seed: Int) {
    protected val token: Int = seed * 2
}

class Child(seed: Int) : Base(seed) {
    fun reveal(): Int = token
}

fun case1() {
    val c = Child(3)
    c checkType { check<Child>() }
    checkSubtype<Base>(c)
    c.reveal() checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
open class Repo {
    protected open fun key(): String = "base"
    fun publicKey(): String = key()
}

class NamedRepo : Repo() {
    override fun key(): String = "named"
}

fun case2() {
    val r = NamedRepo()
    r checkType { check<NamedRepo>() }
    checkSubtype<Repo>(r)
    r.publicKey() checkType { check<String>() }
    val asRepo: Repo = r
    asRepo.publicKey() checkType { check<String>() }
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

fun case3() {
    val k = Kid()
    k checkType { check<Kid>() }
    checkSubtype<Parent>(k)
    checkSubtype<GrandParent>(k)
    k.get() checkType { check<Int>() }
}
