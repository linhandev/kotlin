// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: declarations, property-declaration, delegated-property-declaration -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: custom generic property delegate class
 */

// TESTCASE NUMBER: 1
import kotlin.reflect.KProperty

class CustomDelegate<T>(private var value: T) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>) = value
    operator fun setValue(thisRef: Any?, property: KProperty<*>, v: T) {
        value = v
    }
}

class Box {
    var x: String by CustomDelegate("hello")
}

fun test() = Box().apply { x = "world" }.x

fun box(): String {
    if (test() != "world") return "NOK"
    return "OK"
}
