/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 20 -> sentence 20
 * PRIMARY LINKS: declarations, property-declaration, delegated-property-declaration -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: generic property delegate class
 */

// TESTCASE NUMBER: 1
import kotlin.reflect.KProperty

class GenericDelegate<T>(var value: T) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>) = value
    operator fun setValue(thisRef: Any?, property: KProperty<*>, v: T) {
        value = v
    }
}

class Box {
    var x: String by GenericDelegate("hello")
}

fun test() = Box().x

fun box(): String {
    if (test() != "hello") return "NOK"
    return "OK"
}
