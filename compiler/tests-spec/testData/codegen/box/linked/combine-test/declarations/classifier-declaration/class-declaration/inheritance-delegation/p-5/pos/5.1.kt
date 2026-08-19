/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: declarations, property-declaration, delegated-property-declaration -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: custom getValue property delegate
 */

// TESTCASE NUMBER: 1
import kotlin.reflect.KProperty

class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>) = 42
}

class Box {
    val x: Int by Delegate()
}

fun test() = Box().x

fun box(): String {
    if (test() != 42) return "NOK"
    return "OK"
}
