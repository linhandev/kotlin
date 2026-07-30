// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 161 -> sentence 161
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 161 -> sentence 161
 *                inheritance, inheriting -> paragraph 161 -> sentence 161
 * NUMBER: 1
 * DESCRIPTION: type inference when subclass primary constructor parameters are forwarded to superclass primary constructor while declaring own primary-constructor properties
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
open class Base(val name: String)

class Child(name: String, val age: Int) : Base(name)

fun case1() {
    val c = Child("Ann", 20)
    c checkType { check<Child>() }
    checkSubtype<Base>(c)
    c.name checkType { check<String>() }
    c.age checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
open class Point(val x: Int, val y: Int)

class Shifted(dx: Int, dy: Int, val label: String) : Point(dx + 1, dy + 2)

fun case2() {
    val s = Shifted(3, 4, "p")
    s checkType { check<Shifted>() }
    checkSubtype<Point>(s)
    s.x checkType { check<Int>() }
    s.y checkType { check<Int>() }
    s.label checkType { check<String>() }
}

// TESTCASE NUMBER: 3
open class Holder(val id: Int, val tag: String)

class Renamed(code: Int, mark: String, val extra: Boolean) : Holder(code, mark)

fun case3() {
    val r = Renamed(7, "ok", true)
    r checkType { check<Renamed>() }
    checkSubtype<Holder>(r)
    r.id checkType { check<Int>() }
    r.tag checkType { check<String>() }
    r.extra checkType { check<Boolean>() }
}
