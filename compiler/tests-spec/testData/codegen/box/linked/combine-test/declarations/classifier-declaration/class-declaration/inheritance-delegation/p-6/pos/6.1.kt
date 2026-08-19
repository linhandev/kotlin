// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: declarations, property-declaration, delegated-property-declaration -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: mutable property with getValue and setValue delegates
 */

// TESTCASE NUMBER: 1
import kotlin.reflect.KProperty

class Delegate {
    var value = 0
    operator fun getValue(thisRef: Any?, property: KProperty<*>) = value
    operator fun setValue(thisRef: Any?, property: KProperty<*>, v: Int) {
        value = v
    }
}

class Box {
    var x: Int by Delegate()
}

fun test() = Box().apply { x = 42 }.x

fun box(): String {
    if (test() != 42) return "NOK"
    return "OK"
}
