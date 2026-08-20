// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 187 -> sentence 187
 * PRIMARY LINKS: inheritance, overriding -> paragraph 187 -> sentence 187
 *                declarations, declaration-visibility -> paragraph 187 -> sentence 187
 *                inheritance, inheriting -> paragraph 187 -> sentence 187
 * NUMBER: 1
 * DESCRIPTION: type inference when override widens visibility of an open superclass member in a class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
open class Base {
    protected open fun f(): Int = 1
    fun read(): Int = f()
}

class Child : Base() {
    public override fun f(): Int = 2
}

fun case1() {
    val c = Child()
    c checkType { check<Child>() }
    checkSubtype<Base>(c)
    c.f() checkType { check<Int>() }
    c.read() checkType { check<Int>() }

    val asBase: Base = c
    asBase.read() checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
open class Service {
    internal open fun ping(): String = "base"
    fun probe(): String = ping()
}

class PublicService : Service() {
    public override fun ping(): String = "public"
}

fun case2() {
    val s = PublicService()
    s checkType { check<PublicService>() }
    checkSubtype<Service>(s)
    s.ping() checkType { check<String>() }
    s.probe() checkType { check<String>() }
}

// TESTCASE NUMBER: 3
open class Holder {
    protected open val label: String = "base"
    fun banner(): String = label
}

class PublicHolder : Holder() {
    public override val label: String = "shown"
}

fun case3() {
    val h = PublicHolder()
    h checkType { check<PublicHolder>() }
    checkSubtype<Holder>(h)
    h.label checkType { check<String>() }
    h.banner() checkType { check<String>() }
}
